package interview.exercise.exception;

public class InvalidRegexException extends RuntimeException{

    public InvalidRegexException(String message) {
        super(message);
    }
}
