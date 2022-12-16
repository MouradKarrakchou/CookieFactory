package fr.unice.polytech.cod.helper.threadedObjects;

import fr.unice.polytech.cod.helper.threadedObjects.UpdatableObject;
import fr.unice.polytech.cod.store.Store;
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
    public void OnTimeReached() {
        if(!store.getSurpriseBaskets().isEmpty())
            fidelityAccount.notify("A surprise basket is available at the " + store.getName() + " store!");
        setWaitingTime(7*24*60*60*1000);
        startTimer();
    }

}
