package com.example.myapplication.customer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author acui
 */
@Data
@Component
public class Customer {
    @Value("${name}")
    private int age;
    private String name;


}
