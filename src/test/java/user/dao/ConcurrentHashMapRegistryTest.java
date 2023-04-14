package user.dao;

import config.AppContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import user.exception.SqlRetrievalFailureException;
import user.exception.SqlUpdateFailureException;
import user.sqlService.MyUpdatableSqlRegistry;
import user.sqlService.UpdatableSqlRegistry;

import java.util.HashMap;
import java.util.Map;


public class ConcurrentHashMapRegistryTest extends AbstractConcurrentHashMapRegistryTest{

    private UpdatableSqlRegistry sqlRegistry;

    @Override
    public UpdatableSqlRegistry createSqlRegistry() {
        return new MyUpdatableSqlRegistry();
    }
}
