package fr.unice.polytech.cod.store;

public class InvalidStoreExepection extends Exception{
    public InvalidStoreExepection(){
        super("No store with this name has been found");
    }
}
