package com.nhnacademy.shoppingmall.product.exception;

public class DomainNullPointerException extends NullPointerException{
    public DomainNullPointerException(Object o){super(String.format("Object value is null : %s", o.getClass().getName()));}
}
