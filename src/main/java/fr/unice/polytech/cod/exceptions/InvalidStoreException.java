package fr.unice.polytech.cod.exceptions;

public class InvalidStoreException extends Exception{
    public InvalidStoreException(){
        super("No store with this name has been found");
    }
}
