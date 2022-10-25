package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cart {
    private Store store;
    private List<Item> itemList;
    private boolean isValidated;

    public Cart(Store store) {
        this.store = store;
        this.itemList = new ArrayList<>();
        this.isValidated = false;
    }

    public void showCart() {
        Display.showItems(itemList);
    }

    public boolean addToCart(Item item) {
        if (!store.hasEnoughIngredients(item.getIngredientsNeeded()))
            return false;

        itemList.add(item);
        return true;
    }

    public void removeToCart(Item item) {
        itemList.remove(item);
    }



    public List<Item> getItemList() {
        return itemList;
    }

    public boolean validateCart(User user){
        Set<Ingredient> ingredientsNeeded = generateIngredientsNeeded(this.itemList);
        if(!store.hasEnoughIngredients(ingredientsNeeded))
            return false;

        this.isValidated = true;
        Order order = new Order(this, user);
        user.addOrder(order);
        store.addOrder(order, ingredientsNeeded);

        return true;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean b) {
        this.isValidated = b;
    }

    public boolean isEmpty() {
        return this.itemList.isEmpty();
    }

    private Set<Ingredient> generateIngredientsNeeded(List<Item> items){
        Set<Ingredient> neededIngredients = new HashSet<>();
        // Check the list of items
        for(Item item : items){
            // Generating all needed ingredients for each item
            for(Ingredient ingredient : item.getIngredientsNeeded()){
                // Merging all needed ingredients together
                boolean isAdded = false;
                for(Ingredient neededIngredient : neededIngredients){
                    if(neededIngredient.equals(ingredient)){
                        neededIngredient.increaseQuantity(ingredient.getQuantity());
                        isAdded = true;
                    }
                }
                if(!isAdded)
                    neededIngredients.add(ingredient);
            }
        }
        return neededIngredients;
    }

    public Store getStore() {
        return this.store;
    }
}
