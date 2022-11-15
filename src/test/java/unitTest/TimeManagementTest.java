package unitTest;

import fr.unice.polytech.cod.*;
import fr.unice.polytech.cod.data.CookieBook;
import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.schedule.TimeSlot;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.InvalidStoreException;
import fr.unice.polytech.cod.store.StoreManager;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimeManagementTest {
    private User user;
    private final IngredientCatalog ingredientCatalog = IngredientCatalog.instance;
    private final StoreLocation storeLocation = StoreLocation.intance;


    @BeforeEach
    public void init() throws InvalidStoreException {
        this.user = new User(new Cart(),storeLocation);
        this.user.selectStore("Antibes");
        for (int i = 0; i <100; i++)
            user.getStore().getStock().addStockList(ingredientCatalog.getIngredientList());
        user.getStore().getListChef().clear();


    }

    @Test
    public void IntervalTest30Min(){
        Cookie testCookie5Min = new Cookie("testCookie5Min", null, null, new ArrayList<>(), null, null,5 );
        Cookie testCookie10Min = new Cookie("testCookie10Min", null, null, new ArrayList<>(), null, null,10 );

        int startingHour = 10;
        int finishingHour = 11;
        int duration = 30;
        int numberOfHour = 1;
        int intervalDuration = 30;
        this.user.getCart().getItemList().add(new Item(testCookie5Min, 1));
        this.user.getCart().getItemList().add(new Item(testCookie10Min, 1));
        createChef(startingHour, finishingHour);
        createChef(startingHour, finishingHour);
        createChef(startingHour, finishingHour);

        List<Interval> availableIntervals = user.getAvailableIntervals(user.getCart().getDuration());
        for (Interval interval:availableIntervals){
            Assertions.assertTrue(interval.getStartTime().compareTo(new TimeClock(startingHour,0))>=0&&interval.getEndTime().compareTo(new TimeClock(finishingHour,0))<=0);
            Assertions.assertEquals(duration, interval.getStartTime().timeDifference(interval.getEndTime()));
        }
        int numberOfMinute=numberOfHour*60;
        if (intervalDuration%15!=0)
            numberOfMinute-=(intervalDuration/15+1)*15;
        else
            numberOfMinute-=intervalDuration;
        int numberOfInterval=numberOfMinute/15+1;
        Assertions.assertEquals(numberOfInterval, availableIntervals.size());
    }

    @Test
    public void IntervalTest40Min(){
        Cookie testCookie10Min = new Cookie("testCookie10Min", null, null, new ArrayList<>(), null, null,10 );
        int startingHour = 14;
        int finishingHour = 17;
        int durationCompute = 45;

        int numberOfHour = 3;
        int intervalDuration = 45;
        this.user.getCart().getItemList().add(new Item(testCookie10Min, 1));
        this.user.getCart().getItemList().add(new Item(testCookie10Min, 1));
        createChef(startingHour, finishingHour);


        List<Interval> availableIntervals = user.getAvailableIntervals(user.getCart().getDuration());
        for (Interval interval:availableIntervals){
            Assertions.assertTrue(interval.getStartTime().compareTo(new TimeClock(startingHour,0))>=0&&interval.getEndTime().compareTo(new TimeClock(finishingHour,0))<=0);
            Assertions.assertEquals(durationCompute, interval.getStartTime().timeDifference(interval.getEndTime()));
        }
        int numberOfMinute=numberOfHour*60;
        if (intervalDuration%15!=0)
            numberOfMinute-=(intervalDuration/15+1)*15;
        else
            numberOfMinute-=intervalDuration;
        int numberOfInterval=numberOfMinute/15+1;
        Assertions.assertEquals(numberOfInterval, availableIntervals.size());
    }

    @Test
    public void IntervalTest(){
        Cookie testCookie10Min = new Cookie("testCookie10Min", null, null, new ArrayList<>(), null, null,10 );
        int startingHour = 8;
        int finishingHour = 10;
        int durationCompute = 45;

        int numberOfHour = 3;
        int intervalDuration = 45;
        this.user.getCart().getItemList().add(new Item(testCookie10Min, 1));
        this.user.getCart().getItemList().add(new Item(testCookie10Min, 1));
        createChef(startingHour, finishingHour);
        createChef(10, 12);

        List<Interval> availableIntervals = user.getAvailableIntervals(user.getCart().getDuration());

        for (Interval interval:availableIntervals){
            Assertions.assertTrue(interval.getStartTime().compareTo(new TimeClock(8,0))>=0&&interval.getEndTime().compareTo(new TimeClock(12,0))<=0);
            Assertions.assertEquals(durationCompute, interval.getStartTime().timeDifference(interval.getEndTime()));
        }
        for (Interval interval:availableIntervals){
            boolean startBeforeEndOfFirstshift=interval.getStartTime().compareTo(new TimeClock(10,0))<0;
            boolean endAfterFirstshift=interval.getEndTime().compareTo(new TimeClock(10,0))>0;
            //xor
            assertTrue(!(startBeforeEndOfFirstshift && endAfterFirstshift));
        }
    }
    
    public Chef createChef(int start, int end){
        Chef chef=new Chef(user.getStore());
        user.getStore().addChef(chef);
        List<TimeSlot> timeSlots=chef.getSchedule().getDaySlot().getTimeSlots();
        for (TimeSlot timeSlot:timeSlots){
            if(!(timeSlot.getStartTime().compareTo(new TimeClock(start,0))>=0&&timeSlot.getEndTime().compareTo(new TimeClock(end,0))<=0))
                timeSlot.setReserved(true);
        }
        return chef;
    }
}
