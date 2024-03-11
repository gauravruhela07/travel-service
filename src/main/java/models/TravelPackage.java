package models;

import exception.CapacityExceededException;
import lombok.Data;

import java.util.*;

@Data
public class TravelPackage {
    private UUID id;
    private String name;
    private Integer passengerCapacity;
    private Map<UUID, Destination> destinations;
    private Map<UUID, Passenger> passengers;

    public TravelPackage(String name, Integer passengerCapacity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.destinations = new HashMap<>();
        this.passengers = new HashMap<>();
    }

    public void addPassenger(Passenger passenger) {
        if(passengers.size()<passengerCapacity) {
            this.passengers.put(passenger.getId(), passenger);
        } else {
            throw new CapacityExceededException("Passenger Capacity Exceeded");
        }
    }

    public void addDestination(Destination destination) {
        destinations.put(destination.getId(), destination);
    }
}
