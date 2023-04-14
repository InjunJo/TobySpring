package chap6;


import lombok.Builder;
import lombok.Data;

@Mytest @Builder @Data
public class BeanTest {

    private String str = "hi";

    public BeanTest(){}

    public BeanTest(String str) {
        this.str = str;
    }

    public void method() throws RuntimeException{

    }

    public void get2(){}
}
