package models;

import exception.BalanceValidationException;
import exception.InsufficientBalanceException;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Passenger {
    private UUID id;
    private String name;
    private Double balance;
    private List<Activity> activityList;
    private Double costFactor;


    public Passenger(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.activityList = new ArrayList<>();
        this.setBalance(0.0);
    }

    public Passenger(String name, Double balance) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.setBalance(balance);
        this.activityList = new ArrayList<>();
    }

    public void setBalance(Double balance) {
        if(balance<0) {
            throw new BalanceValidationException("Balance can't be negative");
        }
        this.balance = balance;
    }

    public void registerForActivity(Activity activity) {

        this.activityList.add(activity);

        Double cost = this.getCostFactor()*activity.getCost();
        if (cost <= this.getBalance()) {
            this.setBalance(this.getBalance() - cost);
        } else {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
    }

    public String toString() {
        if(this instanceof PremiumPassenger) {
            return "Name "+this.getName()+" Id "+this.getId();
        } else {
            return "Name "+this.getName()+" Id "+this.getId() +" balance "+this.getBalance();
        }
    }

}
