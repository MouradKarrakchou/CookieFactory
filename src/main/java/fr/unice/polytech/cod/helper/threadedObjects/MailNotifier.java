package fr.unice.polytech.cod.helper.threadedObjects;

import fr.unice.polytech.cod.components.FidelityAccountManager;
import fr.unice.polytech.cod.components.StoreManager;
import fr.unice.polytech.cod.interfaces.IFidelityAccountManager;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.FidelityAccount;


public class MailNotifier extends UpdatableObject {
    private Store store;
    private FidelityAccount fidelityAccount;

    private StoreModifier storeModifier;

    private IFidelityAccountManager IFidelityAccountManager;

    /**
     * Instantiate a new UpdatableObject
     *
     * @param waitingTime the time (in ms) to wait before calling OnTimeReached method.
     */
    public MailNotifier(int waitingTime, Store store, FidelityAccount fidelityAccount) {
        super(waitingTime);
        this.store = store;
        this.fidelityAccount = fidelityAccount;

        IFidelityAccountManager = FidelityAccountManager.getBean(IFidelityAccountManager.class);
        storeModifier = StoreManager.getBean(StoreModifier.class);

    }

    @Override
    public void OnTimeReached() {
        storeModifier.updateSurpriseBasket(store);
        if(store.getSurpriseBaskets().isEmpty())
            IFidelityAccountManager.notify(fidelityAccount, "A surprise basket is available at the " + store.getName() + " store!");
        setWaitingTime(7*24*60*60*1000);
        startTimer();
    }
}
