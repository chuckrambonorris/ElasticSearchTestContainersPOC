package com.sbev.elastic.poc;

import com.sbev.elastic.poc.data.Product;
import com.sbev.elastic.poc.data.ProductInput;
import com.sbev.elastic.poc.service.ProductService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseElasticTestContainers {

    static final Logger LOGGER = LoggerFactory.getLogger(BaseElasticTestContainers.class);
    static final Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(LOGGER);
    private static final String ELASTICSEARCH_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:8.13.2";
    static GenericContainer container = null;

    static {
        container = new GenericContainer(DockerImageName.parse(ELASTICSEARCH_IMAGE))
                .withExposedPorts(9200)
                .withEnv("xpack.security.enabled", "false")
                .withEnv("xpack.security.transport.ssl.enabled", "false")
                .withEnv("xpack.security.http.ssl.enabled", "false")
                .withEnv("discovery.type", "single-node");
        // .withPassword(ElasticsearchContainer.ELASTICSEARCH_DEFAULT_PASSWORD);
        container.start();
    }

    @Autowired
    ProductService productService;

    @BeforeAll
    static void setUp() throws IOException {
        // Uncomment if you want to see the container logs
        // container.followOutput(logConsumer);
        try (RestClient client =
                     RestClient
                             .builder(HttpHost.create(container.getHost() + ":" + container.getMappedPort(9200))).build()) {
            Response response = client.performRequest(new Request("GET", "/_cluster/health"));

            assertEquals(200, response.getStatusLine().getStatusCode());
        }
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.elasticsearch.hostport", () -> container.getHost() + ":" + container.getMappedPort(9200));
    }


    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void persistProduct(@Autowired TestRestTemplate webTestClient, @LocalServerPort int port) {
        productService.createProduct(new ProductInput("100", "iPhone 12", "Smartphone", 799.99));
        Product product = webTestClient.getForEntity("http://localhost:" + port + "/100", Product.class).getBody();
        assertNotNull(product);
        assertEquals("Smartphone", product.category());
    }
}
