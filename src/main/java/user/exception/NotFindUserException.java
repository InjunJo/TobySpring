package user.exception;

public class NotFindUserException extends RuntimeException{

    public NotFindUserException(){

    }

    public NotFindUserException(Throwable cause){
        super(cause);
    }
}
