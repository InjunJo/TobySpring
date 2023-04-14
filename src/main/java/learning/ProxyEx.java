package learning;


import java.lang.reflect.Proxy;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import proxy.Hello;
import proxy.HelloTarget;
import proxy.LogHandler;
import proxy.UppercaseHandler;

public class ProxyEx {

    public static void main(String[] args) {

        Hello upperHello = (Hello) Proxy.newProxyInstance(ProxyEx.class.getClassLoader(),new Class[]{
            Hello.class},new UppercaseHandler(new HelloTarget(),""));

        Hello hello = (Hello) Proxy.newProxyInstance(ProxyEx.class.getClassLoader(),new Class[]{
            Hello.class},new LogHandler(upperHello));



        System.out.println(hello.sayHello("joinjun"));
    }

}
