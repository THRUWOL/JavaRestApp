package com.yarosh.rejaze.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yarosh.rejaze.entity.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final ObjectMapper objectMapper;

    @GetMapping("/api/main")
    public String mainLisntener(){
        return "Hello world";
    }

    @GetMapping("/api/cat")
    public String giveCat(){
        Cat cat = new Cat("Barsik", 5, 10);
        String jsonData = null;

        try{
            jsonData = objectMapper.writeValueAsString(cat);
        }catch (JsonProcessingException e){
            System.out.println("Error with cat;");
        }

        return jsonData;
    }

    @PostMapping("/api/special")
    public String giveSpecialCat(@RequestParam String name, @RequestParam int age, @RequestParam int weight){
        Cat cat = new Cat(name, age, weight);
        String jsonData = null;

        try{
            jsonData = objectMapper.writeValueAsString(cat);
        }catch (JsonProcessingException e){
            System.out.println("Error with cat;");
        }

        return jsonData;
    }
}
