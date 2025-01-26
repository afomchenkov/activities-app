package com.getyourguide.demo.model;

public record Activity(
        Integer id,
        String title,
        Float price,
        String currency,
        Float rating,
        Boolean specialOffer,
        Integer supplierId) {
}
