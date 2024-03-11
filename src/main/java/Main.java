import models.*;
import service.TravelManagementService;
import service.TravelManagementServiceImpl;
import test.UnitTests;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        TravelManagementService travelManagementService = TravelManagementServiceImpl.getInstance();

        // prepare data
        TravelPackage travelPackage = new TravelPackage("UK Visit", 4);

        // prepare passenger data
        StandardPassenger passenger1 = new StandardPassenger("Gaurav", 20.0);
        GoldPassenger passenger2 = new GoldPassenger("Raj", 30.0);
        StandardPassenger passenger3 = new StandardPassenger("Kamal", 20.0);
        PremiumPassenger passenger4 = new PremiumPassenger("Arjun");

        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);
        travelPackage.addPassenger(passenger4);

        Destination destination1 = new Destination("London Bridge");

        // set activity data
        Activity activity1 = new Activity("Ropeway", "Ropeway activity", 10.0, 2);
        activity1.addPassenger(passenger1);

        Activity activity2 = new Activity("Skiing", "Skiing on icy terrain", 30.0, 2);
        activity2.addPassenger(passenger2);

        destination1.addActivity(activity1);
        destination1.addActivity(activity2);


        Destination destination2 = new Destination("Oxford University");
        Activity activity3 = new Activity("Rock Climbing", "Rock Climbing of hills", 5.0, 3);
        activity3.addPassenger(passenger3);

        Activity activity4 = new Activity("Swimming", "Swimming at sea", 12.0, 4);
        activity4.addPassenger(passenger4);

        destination2.addActivity(activity3);
        destination2.addActivity(activity4);


        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);


        travelManagementService.createTravelPackage(travelPackage);

        travelManagementService.printItinerary(travelPackage.getId());

        travelManagementService.printPassengerList(travelPackage.getId());

        travelManagementService.printDetailsOfPassenger(travelPackage.getId());

        travelManagementService.availableActivityDetails(travelPackage.getId());
    }
}