package com.fly.es.app;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.RandomUtil.randomChinese;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private static final String INDEX = "test-products";
    private final ObjectMapper objectMapper; // Jackson ObjectMapper 用于序列化和反序列化

    public static RestHighLevelClient getClient() {
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("10.19.201.212", 9200, "http"));
        return new RestHighLevelClient(clientBuilder);
    }

    // 创建或更新产品
    public void saveProduct(Integer count) throws Exception {
        List<Map<String, Object>> insertDocs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setId(IdUtil.objectId());
            product.setName(RandomUtil.randomString(10));
            product.setDescription(RandomUtil.randomString(20));
            product.setPrice(RandomUtil.randomFloat());
            insertDocs.add(BeanUtil.beanToMap(product));
        }
        performBulkOperations(INDEX, insertDocs, CollUtil.newArrayList(), CollUtil.newArrayList());
    }

    public List<Product> getProduct(String name) throws Exception {
        SearchRequest searchRequest = new SearchRequest(INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "*" + name + "*");
        BoolQueryBuilder bool = QueryBuilders.boolQuery().must(queryBuilder);
        searchSourceBuilder.query(bool);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = getClient().search(searchRequest, RequestOptions.DEFAULT);
        List<Product> products = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {
            String sourceAsString = hit.getSourceAsString();
            Product product = objectMapper.readValue(sourceAsString, Product.class);
            products.add(product);
        }
        return products;
    }

    public void updateProduct(String name) throws Exception {
        List<Product> products = getProduct(name);
        if(!products.isEmpty()) {
            Product product = products.get(0);
            product.setDescription("ABCDEFGHI");
            UpdateRequest updateRequest = new UpdateRequest(INDEX, product.getId());
            updateRequest.doc(objectMapper.writeValueAsString(product), XContentType.JSON);
            getClient().update(updateRequest, RequestOptions.DEFAULT);
            log.info("update product:{}", product);
        }
    }

    public void performBulkOperations(String indexName,
                                      List<Map<String, Object>> insertDocs,
                                      List<Map<String, Object>> updateDocs,
                                      List<String> deleteIds) throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        // Insert documents
        for (Map<String, Object> doc : insertDocs) {
            String id = doc.get("id").toString(); // Ensure each doc has an "id" field
            bulkRequest.add(
                    new IndexRequest(indexName)
                            .id(id)
                            .source(objectMapper.writeValueAsString(doc), XContentType.JSON)
            );
        }

        // Update documents
        for (Map<String, Object> doc : updateDocs) {
            String id = doc.get("id").toString();
            bulkRequest.add(
                    new UpdateRequest(indexName, id)
                            .doc(objectMapper.writeValueAsString(doc), XContentType.JSON)
            );
        }

        // Delete documents
        for (String id : deleteIds) {
            bulkRequest.add(
                    new DeleteRequest(indexName, id)
            );
        }
        BulkResponse bulk = getClient().bulk(bulkRequest, RequestOptions.DEFAULT);
        if(bulk.hasFailures()) {
            log.error("Bulk operation failed");
        }
    }

}
