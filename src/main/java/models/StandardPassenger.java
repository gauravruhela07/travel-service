package models;

public class StandardPassenger extends Passenger{
    public StandardPassenger(String name, Double balance) {
        super(name, balance);
        super.setCostFactor(1.0);
    }

}
