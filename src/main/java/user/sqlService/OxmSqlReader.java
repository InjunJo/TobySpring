package user.sqlService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;
import user.xmlBinding.SqlType;
import user.xmlBinding.Sqlmap;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

@Setter @Getter  @RequiredArgsConstructor
public class OxmSqlReader implements SqlReader {
    private static final String DEFAULT_SQLMAP_FILE = "/META-INF/sqlmap.xml";

    private static final Resource DEFAULT_SQLMAP_RESOURCE = new ClassPathResource(DEFAULT_SQLMAP_FILE);
    private String sqlmapFile = DEFAULT_SQLMAP_FILE;

    private final Unmarshaller unmarshaller;

    private Resource sqlmap = DEFAULT_SQLMAP_RESOURCE;


    @Override
    public void read(SqlRegistry sqlRegistry) {

        try {

            Source source = new StreamSource(sqlmap.getInputStream());

            Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(source);


            for(SqlType sql : sqlmap.getSql()){
                sqlRegistry.registerSql(sql.getKey(),sql.getValue());
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(this.sqlmapFile+"을 가져올 수 없습니다.",e);
        }


    }


}
