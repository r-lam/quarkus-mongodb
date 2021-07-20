package org.itm;

import java.time.LocalDate;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "Customer")
public class Customer extends PanacheMongoEntity {
    @JsonProperty
    public String name;
    public String last;
    public String email;
    // will be persisted as a 'birth' field in MongoDB
    @BsonProperty("birth")
    public LocalDate birthDate;
    public Status status;

    // entity methods
    public static Customer findByName(String name) {
        return find("name", name).firstResult();
    }

    public static List<Customer> findAlive() {
        return list("status", Status.LIVING);
    }

    public static void deleteLoics() {
        delete("name", "Loïc");
    }
}