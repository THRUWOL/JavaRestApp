package com.yarosh.rejaze.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yarosh.rejaze.DTO.CatDTO;
import com.yarosh.rejaze.entity.Cat;
import com.yarosh.rejaze.repository.CatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cats API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepository catRepository;

    private final String ADD_NEW_CAT = "/api/add";
    private final String GET_ALL_CATS = "/api/all";
    private final String GET_CAT_BY_ID = "/api/cat";
    private final String DELETE_CAT_BY_ID = "/api/cat";
    private final String UPDATE_CAT = "/api/add";

    @Operation(summary = "Add new cat")
    @PostMapping(ADD_NEW_CAT)
    public void addCat(@RequestBody CatDTO catDTO) {

        Cat cat = Cat.builder()
                .age(catDTO.getAge())
                .name(catDTO.getName())
                .weight(catDTO.getWeight())
                .build();

        log.info("New row: " + catRepository.save(cat));
    }

    @Operation(summary = "Get all cats")
    @GetMapping(GET_ALL_CATS)
    @SneakyThrows
    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    @Operation(summary = "Get cat by id")
    @GetMapping(GET_CAT_BY_ID)
    public Cat getCatById(@RequestParam int id) {
        return catRepository.findById(id).orElseThrow();
    }

    @Operation(summary = "Delete cat by id")
    @DeleteMapping(DELETE_CAT_BY_ID)
    public void deleteCatById(@RequestParam int id) {
        catRepository.deleteById(id);
    }

    @Operation(summary = "Update cat by id")
    @PutMapping(UPDATE_CAT)
    public String changeCatById(@RequestBody Cat cat) {
        if (!catRepository.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepository.save(cat).toString();
    }
}

