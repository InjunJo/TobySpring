package user.dao;

import config.AppContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import user.domain.Level;
import user.domain.User;

import java.sql.SQLException;
import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppContext.class})
@Transactional
public class UserDaoTests {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext ac;


    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public  void setUp() throws SQLException {

        user1 = User.builder().id("user1").name("유저1").password("1").level(Level.BASIC).login(1).recommend(0).build();
        user2 = User.builder().id("user2").name("유저2").password("1").level(Level.SILVER).login(55).recommend(10).build();
        user3 = User.builder().id("user3").name("유저3").password("1").level(Level.GOLD).login(100).recommend(40).build();

    }

    @Test
    public void testAddGet()  {

        System.out.println(this);
        userDao.deleteAll();
        Assertions.assertEquals(userDao.getCount(),0);
        User user = User.builder().id("user1").name("유저1").password("1").level(Level.BASIC).login(1).recommend(0).build();

        userDao.add(user);

        User user1 = userDao.get(user.getId());
        Assertions.assertEquals(userDao.getCount(),1);

        Assertions.assertEquals(user.getId(),user1.getId());

    }


    @Test
    public void testDeleteAll() {
        userDao.deleteAll();

    }

    @Test
    public void testCount() {
        int i = userDao.getCount();

        System.out.println(i);

    }



    @Test
    public void testGetCount() throws SQLException{

        userDao.deleteAll();
        Assertions.assertEquals(userDao.getCount(),0);

        userDao.add(user1);
        Assertions.assertEquals(userDao.getCount(),1);

        userDao.add(user2);
        Assertions.assertEquals(userDao.getCount(),2);

        userDao.add(user3);
        Assertions.assertEquals(userDao.getCount(),3);
    }

    @Test
    public void testGet1() throws SQLException {
        User user = userDao.get("user1");
        System.out.println(user);

        Assertions.assertEquals(user.getId(),"user1");
    }

    @Test
    public void testAdd(){
        userDao.deleteAll();
        userDao.add(user1);

    }
    @Test
    public void testUpdate()  {
        userDao.deleteAll();
        userDao.add(user1);
        userDao.add(user2);

        user1.setName("유저");
        user1.setPassword("qwerd");
        user1.setLogin(33);
        user1.setRecommend(40);
        user1.setLevel(Level.GOLD);

        userDao.update(user1);

        Assertions.assertEquals(userDao.get("user1").getName(),"유저");

    }

    @Test
    public void testGetAll(){
        List<User> list = userDao.getAll();
        list.forEach(System.out::println);

    }

    @Test
    public void testGet(){

        userDao.get("user1");
    }


}
