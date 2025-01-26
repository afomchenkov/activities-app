package com.getyourguide.demo.model;

public record Supplier(
        Integer id,
        String name,
        String address,
        String zip,
        String city,
        String country) {
}
