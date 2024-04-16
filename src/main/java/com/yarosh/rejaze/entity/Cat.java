package com.yarosh.rejaze.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    private String name;
    private int age;
    private int weight;
}
