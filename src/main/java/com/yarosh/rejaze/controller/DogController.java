package com.yarosh.rejaze.controller;

import com.yarosh.rejaze.entity.Dog;
import com.yarosh.rejaze.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogRepository dogRepository;

    private static final String GET_ALL_DOGS = "/all";

    @GetMapping(GET_ALL_DOGS)
    public List<Dog> getAllDogsFromDB() {
        return dogRepository.findAll();
    }

    @GetMapping
    public ResponseEntity<Dog> getDogByName(@RequestParam String name){

        if(dogRepository.findByName(name) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dogRepository.findByName(name));
    }


    @PostMapping
    public void addDog(@RequestBody Dog dog){
        dog.setId(UUID.randomUUID());
        dogRepository.save(dog);
    }

}
