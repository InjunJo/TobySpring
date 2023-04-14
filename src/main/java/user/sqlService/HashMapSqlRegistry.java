package user.sqlService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import user.exception.SqlRetrievalFailureException;

import java.util.HashMap;
import java.util.Map;

@Setter @Getter
public class HashMapSqlRegistry implements SqlRegistry{

    private Map<String,String> sqlMap;

    @Override
    public void registerSql(String key, String sql) {

        if(sqlMap == null){

            sqlMap = new HashMap<>();
        }

        sqlMap.put(key,sql);
    }

    @Override
    public String findSql(String key) throws SqlRetrievalFailureException{

        String sql = sqlMap.get(key);

        if(sql == null){
            throw new SqlRetrievalFailureException(key+"에 해당하는 SQL문이 없습니다");
        }else return sql;

    }

    public void updateSql(){


    }
}
