package com.spring5.demo.reactive_web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Data
public class Customer {
    @Id
    private Long id;
    private final String firstName;
    private final String lastName;

}
