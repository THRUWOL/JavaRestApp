package com.yarosh.rejaze.repository;

import com.yarosh.rejaze.entity.Dog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DogRepository extends MongoRepository<Dog, UUID> {

    Dog findByName(String name);


}
