package learningtest;

import lombok.extern.log4j.Log4j2;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import proxy.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/META-INF/test-applicationContext.xml")
public class HelloTests {


    @Autowired
    private Hello hello;


    @Autowired
    private ApplicationContext context;

    @Test
    public void test(){
        Hello hello1 = context.getBean("hello",Hello.class);
        log.info(hello1.getClass().getTypeName());

    }



    @Test
    public void testProxy(){

        System.out.println(hello.sayHello("joinjun"));
        System.out.println(hello.sayThankYou("joinjun"));


    }



    @Test
    public void testMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String str = "hello";

        Method method = str.getClass().getMethod("length");

        System.out.println(method.invoke(str));

    }

    @Test
    public void testContext(){

        System.out.println(hello.sayHi("joinjun"));
        System.out.println(hello.sayHello("joinjun"));

        System.out.println("hi".equalsIgnoreCase("HI"));
    }

    @Test
    public void proxyFactoryBean(){
        /*ProxyFactoryBean pfBean  = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());
        pfBean.addAdvice(new LowercaseAdvice());


        Hello hello1 = (Hello) pfBean.getObject();*/

        System.out.println(hello.sayHello("joinjun"));
        System.out.println(hello.sayHi("joinjun"));
        /*System.out.println(helloToby.sayHi("joinjun"));
        System.out.println(helloWorld.sayHello("joinjun"));*/
    }

    @Test
    public void classNamePointcutAdvisor(){
        /*NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut(){

            @Override
            public ClassFilter getClassFilter() {

                return new ClassFilter() {
                    @Override
                    public boolean matches(Class<?> aClass) {
                        return aClass.getSimpleName().startsWith("HelloT");
                    }
                };
            }
        };

        pointcut.setMappedName("sayH*");*/

        Hello hello1 = new HelloTarget();
        Hello hello2 = new HelloWorld();

        System.out.println(hello.sayHello("joinjun"));

        /*checkAdviced(new HelloTarget(),true);
        checkAdviced(new HelloToby(),true);
        checkAdviced(new HelloWorld(),false);*/
    }

    public void checkAdviced(Object target,boolean adviced){

        /*ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.addAdvisor(new DefaultPointcutAdvisor(pointcut,new UppercaseAdvice()));

        pfb.setTarget(target);

        Hello hello1 = (Hello) pfb.getObject();*/


        if(adviced){
            Assertions.assertEquals(hello.sayHello("joinjun"),"HELLO JOINJUN");
            Assertions.assertEquals(hello.sayHi("joinjun"),"HI JOINJUN");
        }else {
            Assertions.assertNotEquals(hello.sayHello("joinjun"),"HELLO JOINJUN");
            Assertions.assertNotEquals(hello.sayHi("joinjun"),"HI JOINJUN");
        }


    }




    static class UppercaseAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {

            Object obj = methodInvocation.proceed();

            return ((String)obj).toUpperCase();
        }
    }

    static class LowercaseAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            Object obj = methodInvocation.proceed();

            return ((String)obj).toLowerCase();
        }
    }

    @Test
    public void pointcutAdvisor(){
        ProxyFactoryBean pfb = new ProxyFactoryBean();

        pfb.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");


        pfb.addAdvisor(new DefaultPointcutAdvisor(pointcut,new UppercaseAdvice()));

        Hello hello1 = (Hello) pfb.getObject();


        System.out.println(hello1.sayHi("joinjun"));
        System.out.println(hello1.sayThankYou("joinjun"));



    }

}
