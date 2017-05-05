package dtu.planner.exceptions;

public class MissingInputException extends Exception {
    public String getMessage() {
        return "Missing input";
    }
}