
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.w3c.dom.Document;
import user.domain.User;
import user.xmlBinding.Sqlmap;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Memo {

    public static void main(String[] args) throws Exception {

        double x = 0;

        for(int i=0; i<100; i++){

            x += 0.1;
        }

        System.out.println(x);

        System.out.println(0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1);

        Object o = 0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1;

        Double.class.cast(o);

        System.out.println(Double.class.cast(o).getClass().getTypeName());

        Map<String,String> map = new HashMap<>();

        Collections.synchronizedMap(map);


    }
}
