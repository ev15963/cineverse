package com.example.cineverse.process;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import com.mongodb.client.*;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig   {
    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    private static final Logger LOG = LoggerFactory.getLogger(MongoConfig.class);
    @Bean
    public MongoClient mongoClient() throws Exception {
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoClient mongoClient = mongoClient();
        MongoDatabase database = mongoClient.getDatabase("cineverse");
        MongoCollection<Document> collection = database.getCollection("cinema");
        //findAllDoc(collection);

        return new MongoTemplate(mongoClient, databaseName);
    }

    public static void findAllDoc(MongoCollection<Document> collection) {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                LOG.info("mongo doc : " + cursor.next().toJson());
            }
        } catch (NullPointerException e){
            LOG.info("Expetion : " + e.getMessage());
            e.getStackTrace();
        } finally {
            cursor.close();
        }
    }

}

