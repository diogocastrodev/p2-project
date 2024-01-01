package classes.exceptions;

public class InvalidOptionException extends Exception {

        public InvalidOptionException() {
            super("");
        }

        public InvalidOptionException(String message) {
            super(message);
        }

        @Override
        public String toString() {
            return "Opção inválida! " + this.getMessage();
        }
}
