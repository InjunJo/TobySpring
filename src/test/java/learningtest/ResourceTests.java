package learningtest;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.xml.transform.Source;
import java.io.*;
import java.net.URI;

public class ResourceTests {

    @Test
    public void test() throws Exception{
        DefaultResourceLoader loader = new DefaultResourceLoader();

        Resource resource = loader.getResource("http://www.epril.com/resources/sqlmap.xml");
        InputStream is = resource.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        System.out.println(bis.read());


    }
}
