package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.helper.UpdatableObject;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;

public class MailNotifier extends UpdatableObject {
    Store store;
    FidelityAccount fidelityAccount;

    /**
     * Instantiate a new UpdatableObject
     *
     * @param waitingTime the time (in ms) to wait before calling OnTimeReached method.
     */
    public MailNotifier(int waitingTime, Store store, FidelityAccount fidelityAccount) {
        super(waitingTime);
        this.store = store;
        this.fidelityAccount = fidelityAccount;
    }

    @Override
    protected void OnTimeReached() {
        fidelityAccount.notify("A surprise basket is available at the " + store.getName() + " store!");
    }

}
