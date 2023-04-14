package config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import user.dao.TodoDAO;
import user.dao.UserDao;
import user.dao.UserDaoJdbc;
import user.mapper.TodoMapper;
import user.mapper.UserMapper;
import user.policy.BasicUserLevelUpgrade;
import user.policy.UserLevelUpgradePolicy;
import user.service.TestUserService;
import user.service.UserService;
import user.sqlService.*;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration @ComponentScan(basePackages = {"user","chap6"})
@Import({TestAppContext.class})
@EnableSqlService
@PropertySource("/META-INF/properties/database.properties")
@AllArgsConstructor @EnableTransactionManagement
public class AppContext {

    private final Environment env;


    @Bean
    public UserMapper userMapper(){
        return new UserMapper();
    }

    @Bean
    public DataSource dataSource(){

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();


        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(env.getProperty("db.driverClass")));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setUrl(env.getProperty("db.url"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());

        return txManager;
    }




    @Bean
    public UserLevelUpgradePolicy userLevelUpgradePolicy(){

        return new BasicUserLevelUpgrade();
    }

    @Bean
    public TodoMapper todoMapper(){

        return new TodoMapper();
    }

}
