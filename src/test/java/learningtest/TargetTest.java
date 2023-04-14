package learningtest;

import chap6.Target;
import chap6.TargetInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.util.Arrays;

public class TargetTest {

    @Test
    public void test() throws NoSuchMethodException {
        System.out.println(Target.class.getMethod("minus",int.class,int.class));

    }

    @Test
    public void testPointcutExpression() throws NoSuchMethodException {
        AspectJExpressionPointcut aep = new AspectJExpressionPointcut();
        aep.setExpression("execution(public int minus(int,int))");
        System.out.println(aep);
        boolean check= aep.getClassFilter().matches(Target.class);
        System.out.println(check);
        System.out.println(aep.getMethodMatcher().matches(Target.class.getMethod("minus", int.class, int.class), Target.class));
        System.out.println(Target.class.getMethod("minus", int.class, int.class));

    }
}
