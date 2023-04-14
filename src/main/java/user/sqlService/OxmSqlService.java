package user.sqlService;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import user.exception.SqlRetrievalFailureException;

@Data @Setter
public class OxmSqlService implements SqlService{

    private final Unmarshaller unmarshaller;

    private SqlReader sqlReader;

    private final SqlRegistry sqlRegistry;

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {

        return sqlRegistry.findSql(key);
    }

    /*@Override
    public void afterPropertiesSet() throws Exception {
        loadSql();

    }*/

    @Override
    public void loadSql() {
        sqlReader.read(sqlRegistry);
    }
}
