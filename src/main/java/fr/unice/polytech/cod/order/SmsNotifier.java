package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.UpdatableObject;
import fr.unice.polytech.cod.User;

public class SmsNotifier extends UpdatableObject {
    private final User user;

    public SmsNotifier(User user) {
        super(300_000); // A sms will be sent in 5 minutes
        this.user = user;
    }

    @Override
    protected void OnTimeReached() {
        user.notify("Your cookie is ready, come get it in our store.");
    }
}
