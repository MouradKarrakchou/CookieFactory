package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Store store;
    private List<Item> itemList;
    private boolean isValidated;

    public Cart(Store store) {
        this.store = store;
        itemList = new ArrayList<>();
        this.isValidated = false;
    }

    public void showCart() {
        Display.showItems(itemList);
    }

    public void addToCart(Item item) {
        itemList.add(item);
    }

    public void removeToCart(Item item) {
        itemList.remove(item);
    }

    public Order createOrder() {
        return new Order(this, OrderState.PENDING);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void validateCart(){
        this.isValidated = true;
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

    private List<Ingredient> generateIngredientsNeeded(List<Item> items){
        List<Ingredient> neededIngredients = new ArrayList<>();

        // Check the list of items
        for(Item item : items){
            // Generating all needed ingredients for each item
            for(Ingredient ingredient : item.generateIngredientsNeeded()){
                // Merging all needed ingredients together
                boolean isAdded = false;

                for(Ingredient neededIngredient : neededIngredients){
                    if(neededIngredient.equals(ingredient))
                        neededIngredient.increaseQuantity(ingredient.getQuantity());
                }

                if(!isAdded)
                    neededIngredients.add(ingredient);
            }
        }

        return neededIngredients;
    }
}
