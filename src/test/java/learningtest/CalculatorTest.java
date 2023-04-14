package learningtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    /*private Calculator cal;
    private String filePath;

    @BeforeEach
    public void setUp(){
        cal = new Calculator();
        *//*filePath = "src/main/resources/numbers.txt";*//*
        this.filePath = this.getClass().getSqlmap("numbers.txt").getPath();
    }

    @Test
    void sum() {

        int sum = cal.sum(filePath);
        System.out.println(sum);
        Assertions.assertEquals(cal.sum(filePath),(1+2+3+4));
    }

    @Test
    void testMultiply(){
        System.out.println(cal.multiply(filePath));
        Assertions.assertEquals(cal.multiply(filePath),(1*2*3*4));

    }

    @Test
    void testConcatenate(){
        String s = cal.concatenate(filePath);
        System.out.println(s);

    }*/
}