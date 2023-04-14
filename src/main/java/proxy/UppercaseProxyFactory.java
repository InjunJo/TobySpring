package proxy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

@Setter @Getter
public class UppercaseProxyFactory implements FactoryBean {

    private Object target;

    private String pattern;

    private UppercaseHandler handler;

    Class<?> targetInterface;

    @Override
    public Object getObject() throws Exception {

        handler.setTarget(target);
        handler.setPattern(pattern);

        Object obj = Proxy.newProxyInstance(getClass().getClassLoader()
                , new Class[]{targetInterface},handler);

        return obj;
    }

    @Override
    public Class<?> getObjectType() {
        return targetInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
