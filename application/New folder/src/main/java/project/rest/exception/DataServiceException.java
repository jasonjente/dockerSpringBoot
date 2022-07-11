package project.rest.exception;

public class DataServiceException extends RuntimeException{
    String errorMessage;
    String causeOfError;
    boolean isUserError;

    public DataServiceException(String errorMessage, String causeOfError, boolean isUserError) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.causeOfError = causeOfError;
        this.isUserError = isUserError;
    }
    public DataServiceException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCauseOfError() {
        return causeOfError;
    }

    public void setCauseOfError(String causeOfError) {
        this.causeOfError = causeOfError;
    }

    public boolean isUserError() {
        return isUserError;
    }

    public void setUserError(boolean userError) {
        isUserError = userError;
    }


}
