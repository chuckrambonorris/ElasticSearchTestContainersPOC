//Generate a repository interface for the Product entity
//The repository interface extends the ElasticsearchRepository interface and is parameterized with the Product entity and the type of the entity's identifier (String).
package com.sbev.elastic.poc.dao;

import com.sbev.elastic.poc.data.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {

}
