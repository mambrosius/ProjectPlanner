package dtu.planner.exceptions;

public class NotFoundException extends Exception {
    private String name;

    public NotFoundException(String name) {
        super('"' + name + "\" was not found.");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
