package user.transaction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Setter @Getter
public class TransactionHandler implements InvocationHandler {

    private Object target;
    private String pattern;

    private PlatformTransactionManager transactionManager;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(method.getName().startsWith(pattern)){

            return invokeInTransaction(method,args);
        }else {
            return method.invoke(target,args);
        }

    }

    private Object invokeInTransaction(Method method,Object[] args) throws Throwable {

        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());


        try{

            Object obj = method.invoke(target,args);

            this.transactionManager.commit(status);
            return obj;


        }catch (InvocationTargetException e){
            this.transactionManager.rollback(status);
            System.out.println("트랜잭션 발동");
            throw e.getTargetException();
        }

    }
}
