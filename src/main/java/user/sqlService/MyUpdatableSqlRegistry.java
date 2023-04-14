package user.sqlService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import user.exception.SqlRetrievalFailureException;
import user.exception.SqlUpdateFailureException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Setter @Getter @RequiredArgsConstructor
public class MyUpdatableSqlRegistry implements UpdatableSqlRegistry{

    private Map<String,String> sqlmap = new ConcurrentHashMap<>();

    @Override
    public void registerSql(String key, String sql) {

        sqlmap.put(key,sql);

    }

    @Override
    public String findSql(String key) throws SqlRetrievalFailureException {

        String sql = sqlmap.get(key);

        if(sql == null){

            throw new SqlRetrievalFailureException(key+"에 해당하는 SQL문을 찾을 수 없습니다.");

        }else return sql;
    }

    @Override
    public void updateSql(String key, String sql) {


        if(sqlmap.get(key) == null){

            throw new SqlUpdateFailureException(key+"는 현재 존재하지 않습니다.");

        }else sqlmap.put(key,sql);
    }

    @Override
    public void updateSql(Map<String, String> updateMap) {

        Set<Map.Entry<String,String>> entrySet = updateMap.entrySet();

        for(Map.Entry<String,String> entry : entrySet){

            updateSql(entry.getKey(), entry.getValue());

        }

    }
}
