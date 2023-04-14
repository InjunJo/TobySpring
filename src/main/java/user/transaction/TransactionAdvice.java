package user.transaction;

import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Setter
public class TransactionAdvice implements MethodInterceptor {

    private PlatformTransactionManager transactionManager;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try{

            Object obj = methodInvocation.proceed();

            this.transactionManager.commit(status);
            return obj;


        }catch (RuntimeException e){
            this.transactionManager.rollback(status);
            System.out.println("트랜잭션 발동");
            throw e;
        }

    }
}
