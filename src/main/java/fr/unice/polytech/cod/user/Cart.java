package fr.unice.polytech.cod.user;

import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.store.Store;

import java.time.Instant;
import java.util.*;

public class Cart {
    public Store store;
    public final Set<Item> itemSet;
    public Interval interval;
    private int canceled;
    private Instant lastTimeCanceled;
    private Instant endPenaltyTime;
    private boolean penalty;

    public Cart() {
        this.itemSet = new HashSet<>();
        this.canceled = 0;
        this.penalty = false;
    }

    public Store getStore() {
        return this.store;
    }

    public Interval getInterval() {
        return this.interval;
    }

    public void setInterval(Interval intervals) {
        this.interval = intervals;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    public Instant getLastTimeCanceled() {
        return lastTimeCanceled;
    }

    public void setLastTimeCanceled(Instant lastTimeCanceled) {
        this.lastTimeCanceled = lastTimeCanceled;
    }

    public Instant getEndPenaltyTime() {
        return endPenaltyTime;
    }

    public void setEndPenaltyTime(Instant endPenaltyTime) {
        this.endPenaltyTime = endPenaltyTime;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

    public boolean isPenalty() {
        return penalty;
    }

}