package user.sqlService;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import user.exception.SqlRetrievalFailureException;
import user.exception.SqlUpdateFailureException;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.Map;
import java.util.Set;

@Data
public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry {


    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public void registerSql(String key, String sql) {
        jdbcTemplate.update("insert into sqlmap (key_,sql_) values (?,?)",key,sql);
    }

    @Override
    public String findSql(String key) throws SqlRetrievalFailureException {


        try{
            String sql = jdbcTemplate.queryForObject("select sql_ from sqlmap where key_=?",String.class,key);

            return sql;
        }catch (EmptyResultDataAccessException e){
            throw new SqlRetrievalFailureException(key+"에 해당하는 SQL문이 없습니다.",e);
        }

    }

    @Override
    public void updateSql(String key, String sql) throws SqlUpdateFailureException {

        int affected = jdbcTemplate.update("update sqlmap set sql_ = ? where key_ = ?",sql,key);

        if(affected != 1){

            throw new SqlUpdateFailureException(key+"에 해당하는 SQL을 찾을 수 없습니다.");
        }

    }

    @Override @Transactional
    public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException {


        Set<Map.Entry<String,String >> entrySet = sqlmap.entrySet();

        for(Map.Entry<String,String> entry : entrySet){

            updateSql(entry.getKey(),entry.getValue());

        }


    }
}
