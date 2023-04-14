package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogHandler implements InvocationHandler {

    private final Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("LogHandler...........");

        return method.invoke(target,args);
    }
}
