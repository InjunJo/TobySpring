package user.exception;

public class SqlUpdateFailureException extends RuntimeException{

    public SqlUpdateFailureException(String massage){
        super(massage);
    }

    public SqlUpdateFailureException(String massage,Throwable t){

        super(massage,t);
    }
}
