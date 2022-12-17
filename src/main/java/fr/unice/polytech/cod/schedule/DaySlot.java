package fr.unice.polytech.cod.schedule;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.schedule.TimeSlot;
import fr.unice.polytech.cod.store.Store;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaySlot {
    List<TimeSlot> timeSlots;

    public DaySlot(Store store) {
        timeSlots = new ArrayList<>();
        initialiseTimeSlots(store);
    }

    /**
     * create the timeSlots of the chef for the morning and the afternoon
     */
    private void initialiseTimeSlots(Store store) {
        this.timeSlots.addAll(creatingTimeSlots(store.getOpenHour(),store.getCloseHour()));}



    /**
     * Create as many timeslots of 15 minutes as possible during the timeClockStart and the timeClockFinish time.
     * @param timeClockStart start
     * @param timeClockFinish end
     * @return time slots
     */
    private List<TimeSlot> creatingTimeSlots(TimeClock timeClockStart,TimeClock timeClockFinish){
        int numberOfSlot;
        numberOfSlot=timeClockStart.timeDifference(timeClockFinish)/15;
        ArrayList<TimeSlot> timeSlots=new ArrayList<>();
        TimeClock currentStartTime=timeClockStart;
        for (int k=0;k<numberOfSlot;k++){
            timeSlots.add(new TimeSlot(currentStartTime,currentStartTime.timeClock15MinuteLater()));
            currentStartTime=currentStartTime.timeClock15MinuteLater();
        }
        return timeSlots;
    }
    public Optional<Order> getOrderToPrepare(TimeClock timeClock) {
        for (TimeSlot timeSlot: timeSlots){
            if (timeSlot.getStartTime().equals(timeClock))  return timeSlot.getOrder();
        }
        return Optional.empty();
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }
}
