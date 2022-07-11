package project.exception;

public class DataServiceException extends Exception{
    String errorMessage;
    String causeOfError;
    boolean isUserError;

    public DataServiceException(String errorMessage, String causeOfError, boolean isUserError) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.causeOfError = causeOfError;
        this.isUserError = isUserError;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCauseOfError() {
        return causeOfError;
    }

    public boolean isUserError() {
        return isUserError;
    }
}
