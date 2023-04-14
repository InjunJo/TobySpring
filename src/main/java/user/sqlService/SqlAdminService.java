package user.sqlService;

import user.exception.SqlRetrievalFailureException;

public class SqlAdminService implements SqlService{

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        return null;
    }

    @Override
    public void loadSql() {

    }
}
