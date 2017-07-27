package br.com.ceuma.exception;

/**
 * Created by marcus on 26/07/17.
 */
public class BrokenAPIException extends Exception {

    public BrokenAPIException(Throwable t, String message) {
        super(message,t);
    }
}
