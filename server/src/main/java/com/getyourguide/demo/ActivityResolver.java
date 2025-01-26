package com.getyourguide.demo;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import com.getyourguide.demo.model.ActivitiesList;
import com.getyourguide.demo.model.Activity;
import com.getyourguide.demo.model.ActivityWithSupplier;
import com.getyourguide.demo.model.Supplier;
import com.getyourguide.demo.service.ActivityService;
import com.getyourguide.demo.service.SupplierService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SchemaMapping
class ActivityResolver {
    private final SupplierService supplierService;
    private final ActivityService activityService;

    public ActivityResolver(ActivityService activityService, SupplierService supplierService) {
        this.activityService = activityService;
        this.supplierService = supplierService;
    }

    @QueryMapping
    public ActivityWithSupplier activity(@Argument Integer id) {
        Activity activity = activityService.getActivityById(id);
        return activity != null ? mapToActivityWithSupplier(activity) : null;
    }

    @QueryMapping
    public List<ActivityWithSupplier> activities() {
        return activityService.getActivities().stream()
                .map(this::mapToActivityWithSupplier)
                .toList();
    }

    @QueryMapping
    public ActivitiesList activitiesList(
            @Argument int limit,
            @Argument int offset,
            @Argument String search) {
        List<ActivityWithSupplier> activitiesWithSuppliers = new ArrayList<>();
        List<Activity> activities = activityService.getActivities();

        for (Activity activity : activities) {
            Supplier supplier = supplierService.getSupplierById(activity.supplierId());
            if (supplier == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Supplier with ID " + activity.supplierId() + " not found");
            }

            // Filter by search term if provided
            if (search != null && !search.isEmpty()) {
                if (activity.title().toLowerCase().contains(search.toLowerCase())) {
                    activitiesWithSuppliers.add(mapToActivityWithSupplier(activity));
                }
            } else {
                activitiesWithSuppliers.add(mapToActivityWithSupplier(activity));
            }
        }

        // Total count before pagination
        int totalCount = activitiesWithSuppliers.size();

        // Paginate results
        List<ActivityWithSupplier> paginatedActivities = activitiesWithSuppliers.stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());

        // Return the paginated result
        return new ActivitiesList(paginatedActivities, totalCount, offset);
    }

    private ActivityWithSupplier mapToActivityWithSupplier(Activity activity) {
        Supplier supplier = supplierService.getSupplierById(activity.supplierId());
        return new ActivityWithSupplier(
                activity.id(),
                activity.title(),
                activity.price(),
                activity.currency(),
                activity.rating(),
                activity.specialOffer(),
                supplier);
    }
}
