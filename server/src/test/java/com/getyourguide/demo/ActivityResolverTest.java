package com.getyourguide.demo;

import com.getyourguide.demo.model.Activity;
import com.getyourguide.demo.model.Supplier;
import com.getyourguide.demo.service.ActivityService;
import com.getyourguide.demo.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitConfig
@GraphQlTest(ActivityResolver.class)
class ActivityResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private ActivityService activityService;

    @MockBean
    private SupplierService supplierService;

    @Test
    void testActivitiesQuery() {
        Activity mockActivity = new Activity(1, "Test Activity", 50.0f, "USD", 4.8f, false, 1);
        Supplier mockSupplier = new Supplier(1, "Test Supplier", "Test Address", "12345", "Test City", "Test Country");

        // Mock service calls
        when(activityService.getActivities()).thenReturn(List.of(mockActivity));
        when(supplierService.getSupplierById(1)).thenReturn(mockSupplier);

        String query = """
                query {
                  activities {
                    id
                    title
                    price
                    supplier {
                      id
                      name
                    }
                  }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("activities[0].id").entity(Integer.class).isEqualTo(1)
                .path("activities[0].title").entity(String.class).isEqualTo("Test Activity")
                .path("activities[0].price").entity(Float.class).isEqualTo(50.0f)
                .path("activities[0].supplier.id").entity(Integer.class).isEqualTo(1)
                .path("activities[0].supplier.name").entity(String.class).isEqualTo("Test Supplier");
    }

    @Test
    void testActivityQuery() {
        Activity mockActivity = new Activity(1, "Test Activity", 50.0f, "USD", 4.8f,
                false, 1);
        Supplier mockSupplier = new Supplier(1, "Test Supplier", "Test Address",
                "12345", "Test City", "Test Country");

        // Mock service calls
        when(activityService.getActivityById(1)).thenReturn(mockActivity);
        when(supplierService.getSupplierById(1)).thenReturn(mockSupplier);

        String query = """
                query {
                    activity(id: 1) {
                        id
                        title
                        price
                        supplier {
                            id
                            name
                        }
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("activity.id").entity(Integer.class).isEqualTo(1)
                .path("activity.title").entity(String.class).isEqualTo("Test Activity")
                .path("activity.supplier.id").entity(Integer.class).isEqualTo(1)
                .path("activity.supplier.name").entity(String.class).isEqualTo("Test Supplier");
    }

    @Test
    void testActivitiesListQuery() {
        Activity mockActivity = new Activity(1, "Test Activity", 50.0f, "USD", 4.8f,
                false, 1);
        Supplier mockSupplier = new Supplier(1, "Test Supplier", "Test Address",
                "12345", "Test City", "Test Country");

        // Mock service calls
        when(activityService.getActivities()).thenReturn(List.of(mockActivity));
        when(supplierService.getSupplierById(1)).thenReturn(mockSupplier);

        String query = """
                query {
                    activitiesList(limit: 1, offset: 0, search: "Test") {
                        activities {
                            id
                            title
                            price
                            supplier {
                                id
                                name
                            }
                        }
                        totalCount
                        offset
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("activitiesList.totalCount").entity(Integer.class).isEqualTo(1)
                .path("activitiesList.offset").entity(Integer.class).isEqualTo(0)
                .path("activitiesList.activities[0].id").entity(Integer.class).isEqualTo(1)
                .path("activitiesList.activities[0].title").entity(String.class).isEqualTo("Test Activity")
                .path("activitiesList.activities[0].supplier.name").entity(String.class).isEqualTo("Test Supplier");
    }
}