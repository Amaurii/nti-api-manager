package br.com.ceuma.exception;

/**
 * Created by markiing on 26/07/17.
 */
public class ConsumerException extends Exception {


    public ConsumerException(Throwable t, String message) {
        super(message,t);
    }


}
