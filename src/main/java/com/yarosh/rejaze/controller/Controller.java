package com.yarosh.rejaze.controller;

import com.yarosh.rejaze.kafka.KafkaProducer;
import com.yarosh.rejaze.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final KafkaProducer kafkaProducer;

    private final CatRepository catRepository;

    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        var cat = catRepository.findById(id);

        kafkaProducer.sendMessage(cat.toString());

        return "Success";
    }
}
