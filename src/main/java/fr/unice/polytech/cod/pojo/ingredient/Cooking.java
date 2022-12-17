package fr.unice.polytech.cod.pojo.ingredient;

public class Cooking extends Ingredient {
    int preparationTime;
    public enum CookingState {
        CRUNCHY  , CHEWY
    }
    public Cooking(CookingState cookingState) {
        super("notDefineds", 3, 1);
        if(cookingState.equals(CookingState.CRUNCHY)){
            super.name="Crunchy";
            super.price = 1;
            this.preparationTime = 15;
        }
        else{
            super.name="Chewy";
            super.price = 2;
            this.preparationTime = 10;
        }
    }

    public int getPreparationTime() {
        return this.preparationTime;
    }

    @Override
    public boolean equals(Object object){
        // Check for the address
        if (object == this)
            return true;

        // Check for the instance
        if (!(object instanceof Cooking d))
            return false;

        // Check for the name equality
        return d.name.equals(name);
    }

}
