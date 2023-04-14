package proxy;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
@Setter @Getter
public class UppercaseHandler implements InvocationHandler {

    private Object target;
    private String pattern;

    public UppercaseHandler(Object target) {
        this.target = target;
    }

    public UppercaseHandler(Object target, String pattern) {
        this.target = target;
        this.pattern = pattern;
    }

    public UppercaseHandler() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object obj = method.invoke(target,args);

        if(obj instanceof String && method.getName().contains(pattern)){
            return ((String) obj).toUpperCase();
        }else return obj;

    }

}
