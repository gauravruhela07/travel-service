package models;

public class GoldPassenger extends Passenger {

    public GoldPassenger(String name, Double balance) {
        super(name, balance);
        super.setCostFactor(0.8);
    }
}
