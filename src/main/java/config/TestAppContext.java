package config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import user.dao.UserDao;
import user.mapper.UserMapper;
import user.policy.BasicUserLevelUpgrade;
import user.policy.UserLevelUpgradePolicy;
import user.service.TestUserService;
import user.service.UserService;

import javax.sql.DataSource;


@Configuration
@ImportResource("/META-INF/test-applicationContext.xml")
@Data @Profile("test1")
@ComponentScan
public class TestAppContext {


    private final UserDao userDao;

    private final UserLevelUpgradePolicy policy;

    /*@Bean
    public UserService testUserService(){


        return new TestUserService(userDao,policy);
    }*/

}
