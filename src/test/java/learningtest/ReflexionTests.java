package learningtest;

import chap6.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import proxy.HelloTarget;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflexionTests {

    @Test
    public void invokeMethod() throws Exception{
        String name = "spring";

        Method method = name.getClass().getMethod("length");

        Object obj= method.invoke(name);

        Method[] methods = String.class.getMethods();

        System.out.println(obj.getClass().getTypeName());

        Arrays.stream(methods).forEach(System.out::println);

    }

    @Test
    public void testMessage() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = Message.class.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);

        /*Constructor[] constructors= Message.class.getDeclaredConstructors();

        Arrays.stream(constructors).forEach(System.out::println);*/

        Message message= (Message) constructor.newInstance("hi");


        System.out.println("constructor : "+constructor);
        System.out.println(message.getText());

        /*constructor.setAccessible(true);

        Message message = (Message) constructor.newInstance();


        System.out.println(message.getText());*/


        /*constructor.setAccessible(true);

        Message message = (Message) constructor.newInstance("hi");

        System.out.println(message);*/

    }

    @Test
    public void testGetInterface(){

        Class clazz = HelloTarget.class;

        Arrays.stream(clazz.getInterfaces()).forEach(System.out::println);

    }
}
