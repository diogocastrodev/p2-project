package classes.exceptions;

public class InvalidArgumentException extends Exception {

    private String argument;

    public InvalidArgumentException(String message, String argument) {
        super(message);
        this.setArgument(argument);
    }

    public InvalidArgumentException(String message) {
        super(message);
    }

    public String getArgument() {
        return this.argument;
    }

    private void setArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        String s = "Parâmetro inválido";
        String arg = this.getArgument() == null ? "" : this.getArgument();

        s += arg.isEmpty() ? "" : " (" + arg + ")";
        s += ": " + this.getMessage();
        return s;
    }


}
