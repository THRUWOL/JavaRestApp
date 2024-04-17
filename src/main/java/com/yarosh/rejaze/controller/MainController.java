package com.yarosh.rejaze.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yarosh.rejaze.entity.Cat;
import com.yarosh.rejaze.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepository catRepository;

    private final ObjectMapper objectMapper;

    private final String ADD_NEW_CAT = "/api/add";
    private final String GET_ALL_CATS = "/api/all";
    private final String GET_CAT_BY_ID = "/api/cat";
    private final String DELETE_CAT_BY_ID = "/api/cat";
    private final String UPDATE_CAT = "/api/add";

    @PostMapping(ADD_NEW_CAT)
    public void addCat(@RequestBody Cat cat){
        log.info("New row: " + catRepository.save(cat));
    }

    @GetMapping(GET_ALL_CATS)
    @SneakyThrows
    public List<Cat> getAll(){
        return catRepository.findAll();
    }

    @GetMapping(GET_CAT_BY_ID)
    public Cat getCatById(@RequestParam int id){
        return catRepository.findById(id).orElseThrow();
    }

    @DeleteMapping(DELETE_CAT_BY_ID)
    public void deleteCatById(@RequestParam int id){
        catRepository.deleteById(id);
    }

    @PutMapping(UPDATE_CAT)
    public String changeCatById(@RequestBody Cat cat) {
        if (!catRepository.existsById(cat.getId())) {
            return "No such row";
        }
        return  catRepository.save(cat).toString();
    }
}

