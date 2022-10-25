package fr.unice.polytech.cod.ingredient;

public class NotEnoughQuantityException extends Exception {
    public NotEnoughQuantityException(){
        super();
    }

    public NotEnoughQuantityException(String errorMessage){
        super(errorMessage);
    }
}
