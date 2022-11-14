package fr.unice.polytech.cod;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaySlot {
    List<TimeSlot> timeSlots;



    public DaySlot(Store store){
        timeSlots= new ArrayList<>();
        initialiseTimeSlots(store);
    }

    /**
     * create the timeSlots of the chef for the morning and the afternoon
     */
    private void initialiseTimeSlots(Store store) {
        this.timeSlots.addAll(creatingTimeSlots(store.getOpenHour(),store.getCloseHour()));
    private void initialiseTimeSlots() {
        this.timeSlots.addAll(creatingTimeSlots(Chef.START_MORNING_TIME,Chef.END_MORNING_TIME));
        this.timeSlots.addAll(creatingTimeSlots(Chef.START_AFTERNOON_TIME,Chef.END_AFTERNOON_TIME));
    }


    public List<Interval> askForSlotsAvailable(int numberOfMinuteNeeded){
        List<Interval> intervals=new ArrayList<>();
        int numberOfSlotNeeded;
        if (numberOfMinuteNeeded%15==0) numberOfSlotNeeded=numberOfMinuteNeeded/15;
        else numberOfSlotNeeded=numberOfMinuteNeeded/15+1;
        //Slot available that are one after the other
        int slotAvailableCount=0;
        for(int k=0;k<timeSlots.size();k++){
            if (timeSlots.get(k).isAvailable()) slotAvailableCount++;
            if (slotAvailableCount==numberOfSlotNeeded){
                //we will see if we can create another Interval with the next Timeslot and without the first TimeSlot of this group.
                slotAvailableCount--;
                intervals.add(createInterval(k-numberOfSlotNeeded+1,numberOfSlotNeeded));
            }
        }
        return intervals;
    }
    //with position being the position of first slot of the group
    public Interval createInterval(int position,int numberOfSlot){
        List<TimeSlot> timeSlots=new ArrayList<>();
        for(int k=0;k<numberOfSlot;k++){
            timeSlots.add(this.timeSlots.get(position+k));
        }
        return new Interval(timeSlots);

    }


    /**
     * Create as many timeslots of 15 minutes as possible during the timeClockStart and the timeClockFinish time.
     * @param timeClockStart
     * @param timeClockFinish
     * @return
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

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public Optional<Order> getOrderToPrepare(TimeClock timeClock) {
        for (TimeSlot timeSlot: timeSlots){
            if (timeSlot.getStartTime().equals(timeClock))  return timeSlot.getOrder();
        }
        return Optional.empty();
    }
}
