package fr.unice.polytech.cod.ingredient;

public class Mix extends Ingredient {
    public enum MixState {
        MIXED , TOPPED
    }
    public Mix(MixState mixState) {
        super("notDefineds", 3, 1);

        if(mixState.equals(MixState.MIXED)){
            super.name="Mixed";
            super.price = 3;
        }
        else{
            super.name="Topped";
            super.price = 2;
        }

    }

    @Override
    public boolean equals(Object object){
        // Check for the address
        if (object == this)
            return true;

        // Check for the instance
        if (!(object instanceof Mix i))
            return false;

        // Check for the name equality
        return i.name.equals(name);
    }
}
