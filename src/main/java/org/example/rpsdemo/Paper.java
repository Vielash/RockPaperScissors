package org.example.rpsdemo;

public class Paper extends Entity{
   public Rock HASIM;

    public Paper () {
        super(Math.random() * MAX_VALUE, Math.random() * MAX_VALUE);
    }


}
