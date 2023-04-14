package user.dao;

import config.AppContext;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import user.service.UserService;
import user.domain.Level;
import user.domain.User;
import user.exception.TestUserServiceException;

import java.lang.reflect.Field;
import java.util.Arrays;

import static java.lang.Class.forName;

@ExtendWith(SpringExtension.class)
@Log4j2
@ContextConfiguration(classes = AppContext.class)
@ActiveProfiles("test") @Transactional
public class UserServiceTests {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserService userService;
    @Autowired
    private UserService testUserService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private DefaultListableBeanFactory bf;
    @Autowired
    private Environment env;



    private Field[] notNullArr;

    private User user1;
    private User user2;
    private User user3;

    private User user4;

    @BeforeEach
    public  void setUp() throws  NoSuchFieldException, IllegalAccessException {

        user1 = User.builder().id("user1").name("유저1").password("1").level(Level.BASIC).login(1).recommend(0).build();
        user2 = User.builder().id("user2").name("유저2").password("1").level(Level.BASIC).login(55).recommend(10).build();
        user3 = User.builder().id("user3").name("유저3").password("1").level(Level.BASIC).login(100).recommend(40).build();
        user4 = User.builder().id("user4").name("유저4").password("1").level(Level.BASIC).login(40).recommend(40).build();



    }


    @Test
    public void testAdvisorProxyCreator(){
        UserService service = context.getBean("userService",UserService.class);
        log.info(service.getClass().getTypeName());

    }

    @Test
    public void transactionSync(){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setReadOnly(true);

        TransactionStatus txStatus= transactionManager.getTransaction(definition);

        try{
            userService.deleteAll();
            userService.add(user1);
            userService.add(user2);
        }finally {
            transactionManager.commit(txStatus);
        }








    }

    @Test
    public void testNotNull() throws NoSuchFieldException, IllegalAccessException {

        notNullArr = this.getClass().getDeclaredFields();


        for(Field f : notNullArr){

            Assertions.assertNotNull(f.get(this));
        }

    }


    @Test
    public void test(){
        System.out.println(userService.getClass());
        System.out.println(userDao.getClass());
    }

    @Test @Rollback(value = false)
    public void testUpdateLevel(){
        userDao.deleteAll();

        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        userDao.add(user4);

        userService.upgradeLevels();
    }

    @Test
    public void testAdd(){

        userDao.deleteAll();
        user1 = User.builder().id("user4").name("유저4").password("1").login(1).recommend(0).build();
        user2 = User.builder().id("user5").name("유저5").password("1").login(1).level(Level.GOLD).recommend(0).build();

        userService.add(user1);
        userService.add(user2);

        user1 = userDao.get(user1.getId());
        user2 = userDao.get(user2.getId());

        Assertions.assertEquals(user1.getLevel(),Level.BASIC);
        Assertions.assertEquals(user2.getLevel(),Level.GOLD);
    }

    @Test
    public void testGet(){
        userDao.deleteAll();
        userService.add(user2);
        userService.printUser("user2");
    }

    @Test
    public void testPrintAll(){

        userService.printUsers();

    }


    @Test @Transactional(propagation = Propagation.NEVER)
    public void testUpgradeAllorNothing() throws Exception {
        userDao.deleteAll();

        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        userDao.add(user4);


        try{

           this.testUserService.upgradeLevels();

        }catch (Exception e){
            System.out.println("발동");
        }
        System.out.println("basic이여야 함 : "+userDao.get("user2").getLevel());

        Assertions.assertEquals(userDao.get("user2").getLevel(),user2.getLevel());
        Assertions.assertEquals(userDao.get("user4").getLevel(),user4.getLevel());


    }

    @Test
    public void testProxyTransactionBean() throws Exception {
        userDao.deleteAll();

        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        userDao.add(user4);

        try{

            userService.upgradeLevels();

        }catch (TestUserServiceException e){

            try{
                throw new TestUserServiceException("트랜잭션 테스트중");
            }catch (TestUserServiceException e1){
                System.out.println(e1.getMessage());
            }

        }

        System.out.println(userDao.get("user2").getLevel());

        Assertions.assertNotEquals(userDao.get("user2").getLevel(),user2.getLevel());
        Assertions.assertNotEquals(userDao.get("user4").getLevel(),user4.getLevel());
    }

    @Test
    public void testReadOnlyTransactionAttribute(){
        /*testUserService.getAll();

        testUserService.get("user1");*/

        userService.get("user1");


    }


    /*public static class TestUserServiceImpl extends UserServiceImpl {
        private String id = "user3";

        public void setId(String id){
            this.id = id;
        }
        @Override
        public void upgradeLevel(User user) {

            if(user.getId().equals(id)) throw new TestUserServiceException();

            super.upgradeLevel(user);
        }

        @Override
        public void upgradeLevels()  {
            super.upgradeLevels();
        }

        @Override
        public List<User> getAll() {

            for(User u: super.getAll()){
                log.info(u);
                super.update(u);
            }

            return null;
        }

        @Override
        public User get(String id) {

            User user=super.get(id);
            super.update(user);

            return super.get(id);
        }
    }*/

    @Test
    public void testType(){

        log.info(testUserService.getClass().getTypeName());
    }

    @Test
    public void testBeans(){

        Arrays.stream(bf.getBeanDefinitionNames()).forEach(System.out::println);
        /*Arrays.stream(bf.getBeanNamesForAnnotation(Component.class)).forEach(System.out::println);*/

    }
    @Test
    public void testEnv() throws ClassNotFoundException {
        System.out.println(env.getProperty("db.url"));
        System.out.println(Class.forName(env.getProperty("db.driverClass")));


    }

    @Test
    public void testTestProperty(){

        Field[] fields= testUserService.getClass().getDeclaredFields();
        Arrays.stream(testUserService.getClass().getMethods()).forEach(i-> System.out.println(i.getName()));

        /*for(Field f : fields){
            System.out.println(f.getName());
        }*/

    }


}
