package com.sbev.elastic.poc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchConfiguration;

@Configuration
public class MyClientConfig extends ReactiveElasticsearchConfiguration {

    @Value("${spring.data.elasticsearch.hostport}")
    private String elastiSearchHostPort;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elastiSearchHostPort)
                //.withBasicAuth("elastic", "changeme")
                .build();
    }
}
