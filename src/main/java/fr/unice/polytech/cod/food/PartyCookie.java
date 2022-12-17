package fr.unice.polytech.cod.food;

import fr.unice.polytech.cod.food.ingredient.*;
import fr.unice.polytech.cod.pojo.Stock;
import fr.unice.polytech.cod.store.Store;

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
        customize(customIngrdedients);

    }

    public PartyCookie(Cookie cookie) {
        super(cookie);
    }

    public void addTopping(Topping t){
        this.toppingList.add(t);
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

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getPrice(Store store) {
        double priceHT = super.getPrice(store);
        return priceHT = getMultiplicator()*priceHT + priceHT*25/100;
    }

    public int getMultiplicator(){
        if(size == CookieSize.L) return 4;
        if(size == CookieSize.XL) return 5;
        if(size == CookieSize.XXL) return 6;
        return -1;
    }

    public CookieSize getSize() {
        return size;
    }


    private void customize(HashMap<Ingredient, Boolean> customIngrdedients){
        for (Map.Entry<Ingredient, Boolean> set : customIngrdedients.entrySet()) {
            if(set.getValue()){
                if(set.getKey() instanceof Topping)
                    addTopping((Topping) set.getKey());
                else if(set.getKey() instanceof Dough)
                    setDough((Dough) set.getKey());
                else if(set.getKey() instanceof Flavour)
                    setFlavour((Flavour) set.getKey());
                else if(set.getKey() instanceof Cooking)
                    setCooking((Cooking) set.getKey());
                else if(set.getKey() instanceof Mix)
                    setMix((Mix) set.getKey());
            }
            else
            if(set.getKey() instanceof Topping)
                removeTopping((Topping) set.getKey());


            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                    + set.getValue());
        }
    }

    private void removeTopping(Topping t) {
        if(this.getIngredients().contains(t)){
            this.getIngredientsList().remove(t);
        }
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
