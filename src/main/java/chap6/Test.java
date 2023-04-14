package chap6;

import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;

import java.util.Set;

@Log4j2
public class Test {

    public static void main(String[] args) {
        Reflections reflections = new Reflections("");

        Set set = reflections.getTypesAnnotatedWith(Mytest.class);
        Class[] classes= Mytest.class.getDeclaredClasses();

        System.out.println(set.size());
    }
}
