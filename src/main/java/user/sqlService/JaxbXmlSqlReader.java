package user.sqlService;

import lombok.Getter;
import lombok.Setter;
import user.dao.UserDao;
import user.xmlBinding.SqlType;
import user.xmlBinding.Sqlmap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.List;

@Setter @Getter
public class JaxbXmlSqlReader implements SqlReader {
    private static final String DEFAULT_SQLMAP_FILE = "/META-INF/sqlmap.xml";
    private String sqlmapFile = DEFAULT_SQLMAP_FILE;


    @Override
    public void read(SqlRegistry sqlRegistry) {

        String contextPath = Sqlmap.class.getPackage().getName();

        try {
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(UserDao.class.getResourceAsStream(this.sqlmapFile));
            List<SqlType> list = sqlmap.getSql();

            for(SqlType sql : list){
                sqlRegistry.registerSql(sql.getKey(),sql.getValue());
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }


}
