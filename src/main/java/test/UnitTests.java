package test;

import exception.AlreadyPresentException;
import exception.BalanceValidationException;
import exception.CapacityExceededException;
import exception.InsufficientBalanceException;
import models.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import service.TravelManagementServiceImpl;

import java.util.Random;

public class UnitTests {

    private TravelManagementServiceImpl travelManagementService = TravelManagementServiceImpl.getInstance();


    @Test
    @DisplayName(value = "testPassengerCapacity_On_AddPassengerToTravelPackage")
    public void testPassengerCapacity_On_AddPassengerToTravelPackage() {
        // prepare data
        TravelPackage travelPackage = new TravelPackage("UK Visit", 3);

        // prepare passenger data
        StandardPassenger passenger1 = new StandardPassenger("Gaurav", 20.0);
        GoldPassenger passenger2 = new GoldPassenger("Raj", 30.0);
        StandardPassenger passenger3 = new StandardPassenger("Kamal", 20.0);
        PremiumPassenger passenger4 = new PremiumPassenger("Arjun");

        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);


        assertThrows(CapacityExceededException.class, () -> {
            travelPackage.addPassenger(passenger4);
        });
    }

    @Test
    @DisplayName(value = "testSamePassengerSignUp_On_AddPassengerToActivity")
    public void testSamePassengerSignUp_On_AddPassengerToActivity() {
        StandardPassenger passenger1 = new StandardPassenger("Gaurav", 20.0);
        GoldPassenger passenger2 = new GoldPassenger("Raj", 30.0);

        Activity activity1 = new Activity("Ropeway", "Ropeway activity", 10.0, 2);
        activity1.addPassenger(passenger1);
        activity1.addPassenger(passenger2);

        assertThrows(AlreadyPresentException.class, () -> {
            activity1.addPassenger(passenger2);
        });
    }

    @Test
    @DisplayName(value = "testPassengerCapacity_On_AddPassengerToActivity")
    public void testPassengerCapacity_On_AddPassengerToActivity() {
        StandardPassenger passenger1 = new StandardPassenger("Gaurav", 20.0);
        GoldPassenger passenger2 = new GoldPassenger("Raj", 30.0);
        StandardPassenger passenger3 = new StandardPassenger("Kamal", 20.0);
        PremiumPassenger passenger4 = new PremiumPassenger("Arjun");

        Activity activity1 = new Activity("Ropeway", "Ropeway activity", 10.0, 2);
        activity1.addPassenger(passenger1);
        activity1.addPassenger(passenger2);

        assertThrows(CapacityExceededException.class, () -> {
            activity1.addPassenger(passenger3);
        });
    }

    @Test
    @DisplayName(value = "testPassengerBalanceDeduction_On_ActivityRegistration")
    public void testPassengerBalanceDeduction_On_ActivityRegistration() {
        StandardPassenger passenger1 = new StandardPassenger("Gaurav", 20.0);
        Double initialBalance = passenger1.getBalance();

        Activity activity1 = new Activity("Ropeway", "Ropeway activity", 10.0, 2);
        activity1.addPassenger(passenger1);

        Double expectedBalanceAfterDeduction = initialBalance - activity1.getCost();
        assertEquals(passenger1.getBalance(), expectedBalanceAfterDeduction);
    }

    @Test
    @DisplayName(value = "testPassengerBalanceDeduction_On_ActivityRegistration")
    public void testGoldPassengerBalanceDeduction_On_ActivityRegistration() {
        GoldPassenger passenger1 = new GoldPassenger("Gaurav", 20.0);
        Double initialBalance = passenger1.getBalance();

        Activity activity1 = new Activity("Ropeway", "Ropeway activity", 10.0, 2);
        activity1.addPassenger(passenger1);

        Double expectedBalanceAfterDeduction = initialBalance - activity1.getCost()*0.8;
        assertEquals(passenger1.getBalance(), expectedBalanceAfterDeduction);
    }

    @Test
    @DisplayName(value = "")
    public void testInvalidTravelPackageId() {
        StandardPassenger passenger1 = new StandardPassenger("Gaurav", 5.0);

        Activity activity1 = new Activity("Ropeway", "Ropeway activity", 10.0, 2);
        assertThrows(InsufficientBalanceException.class, () -> activity1.addPassenger(passenger1));
    }

}
