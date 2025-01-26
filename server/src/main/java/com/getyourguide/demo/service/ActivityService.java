package com.getyourguide.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.demo.model.Activity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class ActivityService {

    private List<Activity> activities = null;

    public ActivityService() {
        this.activities = loadActivites();
    }

    private List<Activity> loadActivites() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var fileInputStream = new ClassPathResource("static/activities.json").getInputStream();
            return objectMapper.readValue(fileInputStream, new TypeReference<List<Activity>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to load activities data from JSON", e);
        }
    }

    public Activity getActivityById(Integer id) {
        return activities.stream().filter(activity -> activity.id().equals(id)).findFirst().orElse(null);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
