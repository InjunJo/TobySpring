package proxy;

public class HelloWorld implements Hello{

    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }

    @Override
    public String sayHi(String name) {
        return "Hi "+name;
    }

    @Override
    public String sayThankYou(String name) {
        return "ThankYou "+name;
    }
}
