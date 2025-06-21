package loilt.user;

public class CreateUserErrors {

    private String userIdNotValid;
    private String passwordNotValid;
    private String confirmNotMatch;
    private String firstNameNotValid;
    private String lastNameNotValid;
    private String userIdExisted;

    public CreateUserErrors() {
    }

    public String getUserIdNotValid() {
        return userIdNotValid;
    }

    public void setUserIdNotValid(String userIdNotValid) {
        this.userIdNotValid = userIdNotValid;
    }

    public String getPasswordNotValid() {
        return passwordNotValid;
    }

    public void setPasswordNotValid(String passwordNotValid) {
        this.passwordNotValid = passwordNotValid;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getFirstNameNotValid() {
        return firstNameNotValid;
    }

    public void setFirstNameNotValid(String firstNameNotValid) {
        this.firstNameNotValid = firstNameNotValid;
    }

    public String getLastNameNotValid() {
        return lastNameNotValid;
    }

    public void setLastNameNotValid(String lastNameNotValid) {
        this.lastNameNotValid = lastNameNotValid;
    }

    public String getUserIdExisted() {
        return userIdExisted;
    }

    public void setUserIdExisted(String userIdExisted) {
        this.userIdExisted = userIdExisted;
    }
}
