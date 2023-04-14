package chap6;


import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;

@Transactional
public class Target implements TargetInterface{

    @Override
    public void hello() {

    }

    @Override
    public void hello(String a) {

    }

    @Override
    public int minus(int a, int b) throws RuntimeException {
        return 0;
    }

    @Override
    public int plus(int a, int b) {
        return 0;
    }

    @Override
    public void method() {

    }
}
