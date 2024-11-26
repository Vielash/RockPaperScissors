package org.example.rpsdemo;

public class Scissors extends Entity{
    public Paper HASIM;

    public Scissors() {
        super(Math.random() * MAX_VALUE, Math.random() * MAX_VALUE);
    }
}
