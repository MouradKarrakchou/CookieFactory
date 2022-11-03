package fr.unice.polytech.cod;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DaySlot {
    List<TimeSlot> timeSlots;
    Date date;
    public DaySlot(Date date){
        this.date=date;
        timeSlots= new ArrayList<>();
        timeSlots.add(new TimeSlot(new Time(8,0,0),new Time(12,0,0)));
        timeSlots.add(new TimeSlot(new Time(13,0,0),new Time(17,0,0)));
    }

    /**
     * return the list of TimeSlot available for the day
     * @return
     */
    public List<TimeSlot> emptyTimeSlots(){
        List<TimeSlot> timeSlotAvailables= new ArrayList();
        for (TimeSlot timeSlot: timeSlots){
            Optional<TimeSlot> optionalTimeSlot=timeSlot.timeSlotAvailable();
            optionalTimeSlot.ifPresent(timeSlotAvailables::add);
        }
        return timeSlotAvailables;
    }
}
