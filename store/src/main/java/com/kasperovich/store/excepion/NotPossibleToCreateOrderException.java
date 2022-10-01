package com.kasperovich.store.excepion;

public class NotPossibleToCreateOrderException extends Exception{

    private String customMessage;

    private Integer errorCode;

    private String exceptionId;


    public NotPossibleToCreateOrderException(String customMessage, Integer errorCode, String exceptionId) {
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToCreateOrderException(String message, String customMessage, Integer errorCode, String exceptionId) {
        super(message);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToCreateOrderException(String message, Throwable cause, String customMessage, Integer errorCode, String exceptionId) {
        super(message, cause);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToCreateOrderException(Throwable cause, String customMessage, Integer errorCode, String exceptionId) {
        super(cause);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToCreateOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String customMessage, Integer errorCode, String exceptionId) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToCreateOrderException() {

    }

    @Override
    public String toString() {
        return "NotPossibleToCreateOrderException{" +
                "customMessage='" + customMessage + '\'' +
                ", errorCode=" + errorCode +
                ", exceptionId='" + exceptionId + '\'' +
                '}';
    }
}
