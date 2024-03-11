package models;

import exception.AlreadyPresentException;
import exception.CapacityExceededException;
import lombok.Data;

import java.util.*;

@Data
public class Activity {
    private UUID id;
    private String name;
    private String description;
    private Double cost;
    private Integer capacity;
    private Integer currentCapacity;
    private Map<UUID, Passenger> registeredPassengers;
    private Destination destination;

    public void addPassenger(Passenger passenger) {
        if(!Objects.isNull(this.registeredPassengers.get(passenger.getId()))) {
            throw new AlreadyPresentException("Passenger Already Registered for the activity");
        }
        if(this.capacity>registeredPassengers.size()) {
            this.currentCapacity-=1;
            passenger.registerForActivity(this);
            this.registeredPassengers.put(passenger.getId(), passenger);
        } else {
            throw new CapacityExceededException("Passenger Capacity Exceeded");
        }
    }

    @Override
    public String toString() {
        return "Activity{" +
                " name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", capacity=" + capacity +
                ", currentCapacity=" + currentCapacity +
                '}';
    }

    public Activity(String name, String description, Double cost, Integer capacity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.currentCapacity = capacity;
        this.registeredPassengers = new HashMap<>();
    }


}