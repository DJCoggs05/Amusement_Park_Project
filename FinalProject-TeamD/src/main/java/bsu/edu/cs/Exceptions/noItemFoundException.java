package bsu.edu.cs.Exceptions;

public class noItemFoundException extends Exception {

    public noItemFoundException(){
        super("Could not find the item you were looking for!");
    }
}
