package user.sqlService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import user.exception.SqlRetrievalFailureException;

@Setter @Getter @AllArgsConstructor
public class BaseSqlService implements SqlService, InitializingBean {

    private final SqlReader sqlReader;

    private final SqlRegistry sqlRegistry;

    @Override
    public void loadSql(){
        sqlReader.read(sqlRegistry);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadSql();
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {

        String sql = sqlRegistry.findSql(key);

        return sql;
    }

}
