package com.waste.myfood.application.output;

public interface ExceptionFormatterIntPort {
    public void returnResponseErrorEntityExists(String message);
    public void returnResponseErrorEntityNotFound(String message);
    public void returnResponseBusinessRuleViolated(String message);
    public void returnResponseBadFormat(String message);
    public void returnNoData(String message);
}
