import java.io.IOException;

public interface LineCallback<T> {

    public T doSomethingWithLine(String line, T value);
}
