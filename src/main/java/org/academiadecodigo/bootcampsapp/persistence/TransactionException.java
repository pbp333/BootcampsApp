package org.academiadecodigo.bootcampsapp.persistence;

/**
 * Created by codecadet on 04/12/17.
 */
public class TransactionException extends Exception{

    Exception exception;

    public TransactionException(Exception exception){
        this.exception = exception;
    }
}
