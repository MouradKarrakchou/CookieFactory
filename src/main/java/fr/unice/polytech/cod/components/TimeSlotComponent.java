package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ChefAction;
import fr.unice.polytech.cod.interfaces.TimeSlotAction;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Chef;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TimeSlotComponent implements TimeSlotAction {
    ChefAction chefAction;
    @Override
    /**
     * Gets a list of available TimeSlots from all the employees of the store by Date;
     * @return
     */
    public List<Interval> timeSlotAvailable(List<Chef> chefs,int minutes, int numberOfDaysBeforeTheOrder){
        List<Interval> intervals = new ArrayList<>();
        for (Chef chef:chefs){
            for (Interval interval: chefAction.getIntervalsAvailable(chef,minutes,numberOfDaysBeforeTheOrder))
                if (!intervals.contains(interval)) intervals.add(interval);
        }
        Collections.sort(intervals);
        return(intervals);
    }
}
