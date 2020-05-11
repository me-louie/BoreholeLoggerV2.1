package exceptions;

public class InvalidQueryException extends Exception{

    public InvalidQueryException(String msg){
        super(msg);
    }
}
