package models;

public class PremiumPassenger extends Passenger{

    public PremiumPassenger(String name) {
        super(name);
        super.setCostFactor(0.0);
    }

}
