package com.kasperovich.store.excepion;

public class NotPossibleToDeleteProductException extends RuntimeException{
    private String customMessage;

    private Integer errorCode;

    private String exceptionId;

    public NotPossibleToDeleteProductException(String customMessage, Integer errorCode, String exceptionId) {
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToDeleteProductException(String message, String customMessage, Integer errorCode, String exceptionId) {
        super(message);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToDeleteProductException(String message, Throwable cause, String customMessage, Integer errorCode, String exceptionId) {
        super(message, cause);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToDeleteProductException(Throwable cause, String customMessage, Integer errorCode, String exceptionId) {
        super(cause);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToDeleteProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String customMessage, Integer errorCode, String exceptionId) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public NotPossibleToDeleteProductException() {

    }

    @Override
    public String toString() {
        return "NoSuchEntityException{" +
                "customMessage='" + customMessage + '\'' +
                ", errorCode=" + errorCode +
                ", exceptionId='" + exceptionId + '\'' +
                "} " + super.toString();
    }
}
