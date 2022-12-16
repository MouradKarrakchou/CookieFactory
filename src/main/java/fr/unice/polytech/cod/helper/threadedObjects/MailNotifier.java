package fr.unice.polytech.cod.helper.threadedObjects;

import fr.unice.polytech.cod.interfaces.FidelityAccountManager;
import fr.unice.polytech.cod.interfaces.StoreAccessor;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailNotifier extends UpdatableObject {
    private Store store;
    private FidelityAccount fidelityAccount;

    @Autowired
    private StoreModifier storeModifier;

    @Autowired
    private FidelityAccountManager fidelityAccountManager;

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
        storeModifier.updateSurpriseBasket(store);
        if(store.getSurpriseBaskets().isEmpty())
            fidelityAccountManager.notify(fidelityAccount, "A surprise basket is available at the " + store.getName() + " store!");
        setWaitingTime(7*24*60*60*1000);
        startTimer();
    }
}
