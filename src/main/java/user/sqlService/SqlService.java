package user.sqlService;

import user.exception.SqlRetrievalFailureException;

public interface SqlService {

     String getSql(String key) throws SqlRetrievalFailureException;

     void loadSql();
}
