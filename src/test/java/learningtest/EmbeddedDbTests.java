package learningtest;

import config.AppContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppContext.class)
public class EmbeddedDbTests {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate template;



    @BeforeEach
    public void setUp(){
        /*db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/META-INF/embeddedDb/sqlRegistrySchema.sql").addScript("/META-INF/data.sql").build();*/


    }
    @Test
    public void test(){

        Assertions.assertNotNull(dataSource);
        System.out.println(dataSource.getClass().getTypeName());
        /*Assertions.assertNotNull(template);*/

        /*System.out.println(db);
        System.out.println(template);*/

    }

    @Test
    public void testEmbeddedDb(){
        List<Map<String, Object>> list= template.queryForList("select * from sqlmap");

        System.out.println(list);

    }


    @Test
    public void testDbQuery(){
        int count = template.queryForObject("select count(*) from sqlmap",Integer.class);

        Assertions.assertEquals(count,2);

        List<Map<String,Object>> list = template.queryForList("select * from sqlmap order by key_");


        Assertions.assertEquals(list.get(0).get("key_"),"key1");
        Assertions.assertEquals(list.get(0).get("sql_"),"sql1");

        Assertions.assertEquals(list.get(1).get("key_"),"key2");
        Assertions.assertEquals(list.get(1).get("sql_"),"sql2");
    }

    @Test
    public void testInsert(){
        template.update("insert into sqlmap (key_,sql_) values (?,?)","key3","sql3");

        int count= template.queryForObject("select count(*) from sqlmap",Integer.class);

        Map<String,Object> map = template.queryForMap("select * from sqlmap where key_ =?","key3");

        Assertions.assertEquals(count,3);

        System.out.println(map);

    }

    @Test
    public void testQueryObject(){
        /*String str = template.queryForObject("select * from sqlmap where key_= ?",String.class,"key1");*/

        System.out.println(template.queryForObject("select Sql_ from sqlmap where key_= ?",String.class,"key1"));


    }

}
