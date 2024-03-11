package service;

import models.TravelPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface TravelManagementService {
    UUID createTravelPackage(TravelPackage travelPackage);

    void printItinerary(UUID travelPackageId);

    void printPassengerList(UUID travelPackageId);

    void printDetailsOfPassenger(UUID travelPackageId);

    void availableActivityDetails(UUID travelPackageId);
}
