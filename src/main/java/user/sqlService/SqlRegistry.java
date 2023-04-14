package user.sqlService;

import user.exception.SqlRetrievalFailureException;

public interface SqlRegistry {

    public void registerSql(String key, String sql);

    public String findSql(String key) throws SqlRetrievalFailureException;
}
