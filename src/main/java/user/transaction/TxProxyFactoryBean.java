package user.transaction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Setter @Getter
public class TxProxyFactoryBean implements FactoryBean {

    private InvocationHandler txHandler;
    Class<?> serviceInterface;

    @Override
    public Object getObject() throws Exception {

        Object obj = Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{serviceInterface},txHandler);


        return obj;
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
