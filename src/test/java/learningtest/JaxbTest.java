package learningtest;

import org.junit.jupiter.api.Test;
import user.xmlBinding.Sqlmap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxbTest {

    @Test
    public void readSqlmap() throws JAXBException {
        String contextPath = Sqlmap.class.getPackage().getName();

        JAXBContext context = JAXBContext.newInstance(contextPath);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Sqlmap sqlmap =(Sqlmap) unmarshaller.unmarshal(getClass().getResourceAsStream("/META-INF/sqlmap.xml"));
        System.out.println(sqlmap);

        sqlmap.getSql().stream().forEach(i -> System.out.println(i.getKey()+"   :   "+i.getValue()));

    }
}
