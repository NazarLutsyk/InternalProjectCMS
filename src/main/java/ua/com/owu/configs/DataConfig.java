package ua.com.owu.configs;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.MongoParser;

//@Configuration
//public class DataConfig extends AbstractMongoConfiguration {
//
//
//    @Override
//    protected String getDatabaseName() {
//        return "cms";
//    }
//
//    @Override
//    public Mongo mongo() throws Exception {
//        return new MongoClient("127.0.0.1", 27017);
//    }
//
//    @Override
//    protected Collection<String> getMappingBasePackages() {
//        List<String> packs = new ArrayList<>();
//        packs.add("ua.com.owu.enity");
//        return packs;
//    }
//}
@Configuration
public class DataConfig {
    MongoClientURI mongoClientURI = new MongoClientURI("url");

    protected String getDatabaseName() {
        return "cms";
    }

    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        return mongoClient;
    }
    @Bean
    public Morphia morphia() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("ua.com.owu.entity");
        return morphia;
    }

    @Bean
    public Datastore datastore (){
        return morphia().createDatastore(mongoClient(), mongoClientURI.getDatabase());
    }

//    @PostConstruct
//    public void clear() {
//        mongoClient().getDatabase("cms").drop();
//    }


}
