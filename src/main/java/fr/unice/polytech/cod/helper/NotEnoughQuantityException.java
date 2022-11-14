package fr.unice.polytech.cod.helper;

public class NotEnoughQuantityException extends Exception {
    public NotEnoughQuantityException(){
        super();
    }

    public NotEnoughQuantityException(String errorMessage){
        super(errorMessage);
    }
}
