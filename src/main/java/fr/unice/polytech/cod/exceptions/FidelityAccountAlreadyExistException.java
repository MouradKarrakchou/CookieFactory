package fr.unice.polytech.cod.exceptions;

public class FidelityAccountAlreadyExistException extends Exception{
    public FidelityAccountAlreadyExistException(){
        super("The user already as a fidelity account");
    }
}
