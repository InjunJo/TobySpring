package user.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.exception.SqlRetrievalFailureException;
import user.exception.SqlUpdateFailureException;
import user.sqlService.EmbeddedDbSqlRegistry;
import user.sqlService.UpdatableSqlRegistry;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/META-INF/test-applicationContext.xml")
public class EmbeddedDbRegistryTest2 {
    @Autowired
    private UpdatableSqlRegistry sqlRegistry;

    /*@BeforeEach
    public void setUp(){
        sqlRegistry = new EmbeddedDbSqlRegistry();

    }*/

    @Test
    public void test(){

        System.out.println(sqlRegistry);

    }

    @Test
    public void testTransaction(){

        Map<String,String> map = new HashMap<>();
        map.put("key1","modified1");
        map.put("unknown!@#$","modified2");

        try{

            sqlRegistry.updateSql(map);

        }catch (Exception e){
            System.out.println("예외 발생");
        }

        sqlRegistry.findSql("key1");

        System.out.println(sqlRegistry.findSql("key1"));
        System.out.println(sqlRegistry.findSql("key2"));
        System.out.println(sqlRegistry.findSql("key3"));



    }


    @Test
    public void testFind(){

        checkFindResult("sql1","sql2","sql3");

    }

    @Test
    public void testUnknownKey(){
        Assertions.assertThrows(SqlRetrievalFailureException.class
                , ()->sqlRegistry.findSql("sql9999!@#$"));
    }

    @Test
    public void testUnknownKeyUpdate(){

        Assertions.assertThrows(SqlUpdateFailureException.class
                , () -> sqlRegistry.updateSql("key4","modified2"));

    }

    @Test
    public void testUpdateSingle2(){

        sqlRegistry.updateSql("key2","modified2");

        Assertions.assertEquals(sqlRegistry.findSql("key2"),"modified2");

    }

    @Test
    public void testUpdateMulti(){
        Map<String,String> map = new HashMap<>();
        map.put("key1","modified1");
        map.put("key2","modified2");

        sqlRegistry.updateSql(map);

        checkFindResult("modified1","modified2","sql3");
    }

    @Test
    public void testUpdateMultiUnknown(){

        Map<String,String> map = new HashMap<>();
        map.put("key1","modified1");
        map.put("unknown!@#$","modified2");

        Assertions.assertThrows(SqlUpdateFailureException.class
                ,() -> sqlRegistry.updateSql(map));
    }

    @Test
    public void testPutAbsent(){
        Map<String,String> map = new HashMap<>();

        map.put("key1","modified1");
        map.put("key2","modified2");

        System.out.println(map.putIfAbsent("key3","absent"));

        Assertions.assertEquals(map.get("key3"),"absent");

    }



    public void checkFindResult(String expected1,String expected2,String expected3){

        Assertions.assertEquals(sqlRegistry.findSql("key1"),expected1);
        Assertions.assertEquals(sqlRegistry.findSql("key2"),expected2);
        Assertions.assertEquals(sqlRegistry.findSql("key3"),expected3);
    }


}
