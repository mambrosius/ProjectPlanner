package dtu.planner.exceptions;

public class AlreadyExistsException extends Exception {

    private String element;

    public AlreadyExistsException(String element) {
        this.element = element;
    }

    public String getMessage() {
        return element + " already exists";
    }
}
