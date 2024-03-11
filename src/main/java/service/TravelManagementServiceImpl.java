package service;

import exception.NotFoundException;
import models.*;

import java.util.*;
import java.util.stream.Collectors;

public class TravelManagementServiceImpl implements TravelManagementService {

    Map<UUID, TravelPackage> travelPackages;

    private static TravelManagementServiceImpl instance;

    public static TravelManagementServiceImpl getInstance() {
        if(instance==null) {
            synchronized (TravelManagementServiceImpl.class) {
                if(instance==null) {
                    instance = new TravelManagementServiceImpl();
                }
            }
        }
        return instance;
    }

    private TravelPackage findById(UUID id) {
        TravelPackage travelPackage = travelPackages.get(id);
        if(Objects.isNull(travelPackage)) {
            throw new NotFoundException("Travel Package not found with id"+id);
        }
        return travelPackage;
    }

    private TravelManagementServiceImpl() {
        travelPackages = new HashMap<>();
    }

    public UUID createTravelPackage(TravelPackage travelPackage) {
        travelPackages.put(travelPackage.getId(), travelPackage);
        return travelPackage.getId();
    }

    public void printItinerary(UUID travelPackageId) {
        System.out.println("Travel Package Itinerary");
        TravelPackage travelPackage = findById(travelPackageId);

        System.out.println("Travel Package Name: "+travelPackage.getName());
        List<Destination> destinationList = travelPackage.getDestinations().values().stream().toList();

        for(Destination destination: destinationList) {
            System.out.println("Destination Name: "+destination.getName());
            List<Activity> activityList = destination.getActivityList().values().stream().toList();
            for(Activity activity: activityList) {
                System.out.println(activity.toString());
            }
        }
    }

    public void printPassengerList(UUID travelPackageId) {
        System.out.println("Travel Package Passenger list");
        TravelPackage travelPackage = findById(travelPackageId);
        System.out.println("Travel Package Name: "+travelPackage.getName());
        System.out.println("Passenger Capacity: "+travelPackage.getPassengerCapacity());
        System.out.println("Number of passenger currently enrolled "+travelPackage.getPassengers().size());
        List<Passenger> passengers = travelPackage.getPassengers().values().stream().toList();
        System.out.println("Passenger list:-----");
        for(Passenger passenger:passengers) {
            System.out.println(passenger.toString());
        }
    }

    public void printDetailsOfPassenger(UUID travelPackageId) {
        System.out.println("Travel Package Passenger details");
        TravelPackage travelPackage = findById(travelPackageId);

        List<Passenger> passengers = travelPackage.getPassengers().values().stream().toList();
        for(Passenger passenger: passengers) {
            System.out.println(passenger.toString());
            System.out.println("Passenger Activity List ");
            List<Activity> activityList = passenger.getActivityList();

            for (Activity activity: activityList) {
                Destination destination = activity.getDestination();

                double pricePaid = passenger.getCostFactor()*activity.getCost();
                System.out.println("Activity name "+activity.getName()+" Destination "+destination.getName()+" price paid "+pricePaid);
            }
        }
    }

    public void availableActivityDetails(UUID travelPackageId) {
        System.out.println("Details of all the activities that still have spaces available, including how many spaces are available.-");

        TravelPackage travelPackage = findById(travelPackageId);
        List<Activity> availableActivities = new ArrayList<>();
        travelPackage.getDestinations().forEach((id, destination) -> {
            List<Activity> availableDestinationActivities = destination.getActivityList().values().stream().filter(
                    item -> item.getCapacity()>=item.getRegisteredPassengers().size()).toList();
            availableActivities.addAll(availableDestinationActivities);
        });

        for (Activity activity: availableActivities) {
            System.out.println(activity.toString());
        }
    }


}
