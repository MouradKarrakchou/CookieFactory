package fr.unice.polytech.cod;

public class Discount {
   String name;
    int value;

    public Discount(String name, int value) {
        this.name = name;

        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
