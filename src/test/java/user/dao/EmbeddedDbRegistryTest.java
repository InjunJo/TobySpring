package user.dao;

import config.AppContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import user.sqlService.EmbeddedDbSqlRegistry;
import user.sqlService.UpdatableSqlRegistry;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/META-INF/test-applicationContext.xml")
public class EmbeddedDbRegistryTest extends AbstractConcurrentHashMapRegistryTest{

    @Autowired(required = false)
    private EmbeddedDatabase embeddedDb;

    @Test
    public void test(){
        Assertions.assertNotNull(embeddedDb);
        System.out.println(embeddedDb);
    }

    @Override
    public UpdatableSqlRegistry createSqlRegistry() {
        embeddedDb = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/META-INF/embeddedDb/sqlRegistrySchema.sql").build();

        EmbeddedDbSqlRegistry registry = new EmbeddedDbSqlRegistry();


        return registry;
    }

    @AfterEach
    public void tearDown(){
        embeddedDb.shutdown();
    }


}
