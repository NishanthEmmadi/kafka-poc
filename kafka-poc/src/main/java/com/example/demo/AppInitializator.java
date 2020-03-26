package com.example.demo;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
class AppInitializator {
 
    private static final Logger log = LoggerFactory.getLogger(AppInitializator.class);
 
    @PostConstruct
    private void init() throws IOException {
        log.info("AppInitializator initialization logic ...");


        Properties properties = fetchProperties();

        AdminClient adminClient = AdminClient.create(properties);
        NewTopic newTopic = new NewTopic("users", 1, (short)1); //new NewTopic(topicName, numPartitions, replicationFactor)

        List<NewTopic> newTopics = new ArrayList<NewTopic>();
        newTopics.add(newTopic);

        adminClient.createTopics(newTopics);
        adminClient.close();
    }

    public static Properties fetchProperties(){
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:kafka.properties");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }
 
}