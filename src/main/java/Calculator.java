import java.io.*;

public class Calculator {

    private FileReader fr;

    private BufferedReader br;

    public Integer sum(String filePath){
        Integer result = null;

        result = calcuTemplate(filePath, new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value){
                value += Integer.valueOf(line);
                return value;
            }
        },0);

        return result;
    }

    public Integer multiply(String filePath){

        Integer result = null;

        result = calcuTemplate(filePath, new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value){
                value = value * Integer.valueOf(line);
                return value;
            }
        },1);

        return result;
    }

    public String concatenate(String filePath){
        String result = null;

        result = calcuTemplate(filePath, new LineCallback<String>() {
            @Override
            public String doSomethingWithLine(String line, String value){

                value += line;

                return value;
            }
        },"");

        return result;

    }

    public <T> T calcuTemplate(String filePath, LineCallback lineCallback, T init){

        T result = null;
        String line = null;
        T value = init;

        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);

            while((line = br.readLine()) != null){
                value = (T)lineCallback.doSomethingWithLine(line,value);
            }

            result = value;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;

    }

}
