package learningtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.xmlBinding.SqlType;
import user.xmlBinding.Sqlmap;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/META-INF/test-oxmContext.xml")
public class OxmTest {

    @Autowired
    private Unmarshaller unmarshaller;

    @Test
    public void test(){

        System.out.println(unmarshaller);
    }

    @Test
    public void testOXM(){
        Source source = new StreamSource(getClass().getResourceAsStream("/META-INF/sqlmap.xml"));

        try {
             Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(source);

            sqlmap.getSql().stream().forEach(i-> System.out.println(i.getValue()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
