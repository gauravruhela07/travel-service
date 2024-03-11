package models;

import lombok.Data;

import java.util.*;

@Data
public class Destination {
    private UUID id;
    private String name;
    private Map<UUID, Activity> activityList;

    public Destination(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.activityList = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Destination{" +
                ", name='" + name + '\'' +
                ", activityList=" + activityList +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addActivity(Activity activity) {
        activity.setDestination(this);
        activityList.put(activity.getId(), activity);
    }

    public Map<UUID, Activity> getActivityList() {
        return activityList;
    }

}
