package user.exception;

public class SqlRetrievalFailureException extends RuntimeException{

    public SqlRetrievalFailureException(String massage){
        super(massage);
    }

    public SqlRetrievalFailureException(String massage,Throwable t){
        super(massage,t);
    }

}
