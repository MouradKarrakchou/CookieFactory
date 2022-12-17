package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.food.ingredient.Cooking;
import fr.unice.polytech.cod.food.ingredient.Mix;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.schedule.TimeSlot;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TimeManagementTest {
    //ATTENTION NE PAS FAIRE CETTE CLASSE
    private User user;
    private final IngredientCatalog ingredientCatalog = IngredientCatalog.instance;

    @Autowired
    UserAction userAction;
    @Autowired
    ScheduleActions scheduleActions;
    @Autowired
    StockModifier stockModifier;
    @Autowired
    StoreModifier storeModifier;
    @Autowired
    UserRequest userRequest;


    @BeforeEach
    public void init() throws InvalidStoreException {
        this.user = new User();
        userAction.selectStore("Antibes", this.user.getCart());
        for (int i = 0; i < 100; i++)
            stockModifier.addIngredients(user.getCart().getStore().getStock(), ingredientCatalog.getIngredientList());
        user.getCart().getStore().getListChef().clear();
    }

    @Test
    public void IntervalTest30Min() {
        int startingHour = 10;
        int finishingHour = 11;
        int duration = 30;
        int numberOfHour = 1;
        int intervalDuration = 30;
        Cookie testCookie = new Cookie("testCookie", null, null, new ArrayList<>(), null, null, intervalDuration-15);
        this.user.getCart().getItemSet().add(new Item(testCookie, 1));
        createChef(startingHour, finishingHour);
        createChef(startingHour, finishingHour);
        createChef(startingHour, finishingHour);

        List<Interval> availableIntervals = userRequest.getAvailableIntervals(user.getCart().store, user.getCart(), 0);
        for (Interval interval : availableIntervals) {
            Assertions.assertTrue(interval.getStartTime().compareTo(new TimeClock(startingHour, 0)) >= 0 && interval.getEndTime().compareTo(new TimeClock(finishingHour, 0)) <= 0);
            Assertions.assertEquals(duration, interval.getStartTime().timeDifference(interval.getEndTime()));
        }
        int numberOfMinute = numberOfHour * 60;
        if (intervalDuration % 15 != 0)
            numberOfMinute -= (intervalDuration / 15 + 1) * 15;
        else
            numberOfMinute -= intervalDuration;
        int numberOfInterval = numberOfMinute / 15 + 1;
        Assertions.assertEquals(numberOfInterval, availableIntervals.size());
    }

    @Test
    public void IntervalTest40Min() {
        int startingHour = 14;
        int finishingHour = 17;
        int durationCompute = 45;

        int numberOfHour = 3;
        int intervalDuration = 40;
        Cookie testCookie = new Cookie("testCookie", null, null, new ArrayList<>(), null, null, intervalDuration-15);
        this.user.getCart().getItemSet().add(new Item(testCookie, 1));

        createChef(startingHour, finishingHour);


        List<Interval> availableIntervals = userRequest.getAvailableIntervals(user.getCart().store, user.getCart(), 0);
        for (Interval interval : availableIntervals) {
            Assertions.assertTrue(interval.getStartTime().compareTo(new TimeClock(startingHour, 0)) >= 0 && interval.getEndTime().compareTo(new TimeClock(finishingHour, 0)) <= 0);
            Assertions.assertEquals(durationCompute, interval.getStartTime().timeDifference(interval.getEndTime()));
        }
        int numberOfMinute = numberOfHour * 60;
        if (intervalDuration % 15 != 0)
            numberOfMinute -= (intervalDuration / 15 + 1) * 15;
        else
            numberOfMinute -= intervalDuration;
        int numberOfInterval = numberOfMinute / 15 + 1;
        Assertions.assertEquals(numberOfInterval, availableIntervals.size());
    }

    @Test
    public void IntervalTest() {
        int startingHour = 8;
        int finishingHour = 10;
        int durationCompute = 45;
        Cookie testCookie = new Cookie("testCookie", null, null, new ArrayList<>(), null, null, durationCompute-15);


        this.user.getCart().getItemSet().add(new Item(testCookie, 1));
        createChef(startingHour, finishingHour);
        createChef(10, 12);

        List<Interval> availableIntervals = userRequest.getAvailableIntervals(user.getCart().store, user.getCart(), 0);

        for (Interval interval : availableIntervals) {
            Assertions.assertTrue(interval.getStartTime().compareTo(new TimeClock(8, 0)) >= 0 && interval.getEndTime().compareTo(new TimeClock(12, 0)) <= 0);
            Assertions.assertEquals(durationCompute, interval.getStartTime().timeDifference(interval.getEndTime()));
        }
        for (Interval interval : availableIntervals) {
            boolean startBeforeEndOfFirstshift = interval.getStartTime().compareTo(new TimeClock(10, 0)) < 0;
            boolean endAfterFirstshift = interval.getEndTime().compareTo(new TimeClock(10, 0)) > 0;
            //xor
            assertTrue(!(startBeforeEndOfFirstshift && endAfterFirstshift));
        }
    }

    public Chef createChef(int start, int end) {
        Chef chef = new Chef(user.getCart().getStore());
        storeModifier.addChef(user.getCart().getStore(), chef);

        List<TimeSlot> timeSlots = scheduleActions.getDaySlot(chef.getSchedule(), 0).getTimeSlots();
        for (TimeSlot timeSlot : timeSlots) {
            if (!(timeSlot.getStartTime().compareTo(new TimeClock(start, 0)) >= 0 && timeSlot.getEndTime().compareTo(new TimeClock(end, 0)) <= 0))
                timeSlot.setReserved(true);
        }
        return chef;
    }
}
