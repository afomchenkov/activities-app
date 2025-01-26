package com.getyourguide.demo.model;

public record ActivityWithSupplier(
        Integer id,
        String title,
        Float price,
        String currency,
        Float rating,
        Boolean specialOffer,
        Supplier supplier) {
}
