package com.getyourguide.demo.model;

import java.util.List;

public record ActivitiesList(
        List<ActivityWithSupplier> activities,
        int totalCount,
        int offset) {
}
