package org.mrk.exception;

public class NullTaskException extends RuntimeException{

    public NullTaskException(String message){
        System.err.println(message);
    }
}
