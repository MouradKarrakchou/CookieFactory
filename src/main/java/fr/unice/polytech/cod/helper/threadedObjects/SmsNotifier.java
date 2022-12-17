package fr.unice.polytech.cod.helper.threadedObjects;

import fr.unice.polytech.cod.pojo.user.User;

public class SmsNotifier extends UpdatableObject {
    private final User user;

    public SmsNotifier(User user) {
        super(300_000); // A sms will be sent in 5 minutes
        this.user = user;
    }

    @Override
    protected void setTimeReached() {
        if(user.getFidelityAccount() != null)
            System.out.println(user.getFidelityAccount().getName() + " has been notified");
    }
}
