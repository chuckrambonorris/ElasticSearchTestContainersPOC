package com.sbev.elastic.poc.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products")
public record Product(
        @Id
        String id,
        @Field(name = "name", type = FieldType.Text)
        String name,
        @Field(name = "category", type = FieldType.Keyword)
        String category,
        @Field(name = "price", type = FieldType.Double)
        double price
) {
}
