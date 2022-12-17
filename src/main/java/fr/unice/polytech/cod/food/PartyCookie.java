package fr.unice.polytech.cod.food;

import fr.unice.polytech.cod.pojo.ingredient.*;
import fr.unice.polytech.cod.pojo.store.Store;

import java.util.HashMap;
import java.util.Map;

public class PartyCookie extends Cookie{

    public enum CookieSize {
        L , XL, XXL
    }
    public enum Event {
        Anniversary, Wedding, Retirement
    }

    protected CookieSize size;
    private String theme;
    private Event event;
    protected HashMap<Ingredient, Boolean> customIngrdedients;


    public PartyCookie(Cookie cookie, CookieSize size, String theme, Event event, HashMap<Ingredient, Boolean> customIngrdedients){
        super(cookie);
        this.size = size;
        this.theme = theme;
        this.event = event;
        this.customIngrdedients = customIngrdedients;
    }

    public void setCooking(Cooking c ){
        this.cooking = c;
    }
    public void setMix(Mix m){
        this.mix = m;
    }
    public void setFlavour(Flavour f){
        this.flavour = f;
    }
    public void setDough(Dough d){
        this.dough = d;
    }


    public CookieSize getSize() {
        return size;
    }

    public void setSize(CookieSize size) {
        this.size = size;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public HashMap<Ingredient, Boolean> getCustomIngrdedients() {
        return customIngrdedients;
    }

    public void setCustomIngrdedients(HashMap<Ingredient, Boolean> customIngrdedients) {
        this.customIngrdedients = customIngrdedients;
    }

    @Override
    public String toString() {
        return "SpecialCookie{" +
                "size=" + size +
                ", name='" + name + '\'' +
                ", dough=" + dough +
                ", flavour=" + flavour +
                ", mix=" + mix +
                ", cooking=" + cooking +
                ", toppingList=" + toppingList +
                '}';
    }
}
