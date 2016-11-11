package com.CGrepActors;

/***
 * created with a message containing (a) a String with the name of the file
 * to scan (or null for the standard input)
 */
public class Configure {

    private String message = null;
    // default constructor to simulate sending null
    public Configure(){}

    // normal constructor to used to assign the message to this object.
    public Configure(String input){
        this.message = input;
    }

    public String getMessage() {
        return message;
    }

}
