package user.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.domain.Level;
import user.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class MockUserDaoTest {

    private MockUserDao mockUserDao;

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    @BeforeEach
    public void setUp(){
        mockUserDao = new MockUserDao();
        user1 = User.builder().id("user1").name("유저1").password("1").level(Level.BASIC).login(1).recommend(0).build();
        user2 = User.builder().id("user2").name("유저2").password("1").level(Level.SILVER).login(55).recommend(10).build();
        user3 = User.builder().id("user3").name("유저3").password("1").level(Level.BASIC).login(100).recommend(40).build();
        user4 = User.builder().id("user4").name("유저4").password("1").level(Level.BRONZE).login(40).recommend(40).build();

        add();
    }

    @Test
    void testMapToList() {
        Map<String,String> users = new HashMap<>();

        users.put("1","일");
        users.put("2","이");

        List<String> list = users.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

    }

    @Test
    void add() {
        deleteAll();
        mockUserDao.add(user1);
        mockUserDao.add(user2);
        mockUserDao.add(user3);
        mockUserDao.add(user4);

        /*Assertions.assertEquals(mockUserDao.getUser(user1.getId()),user1);
        Assertions.assertEquals(mockUserDao.getUser(user2.getId()),user2);
        Assertions.assertEquals(mockUserDao.getUser(user3.getId()),user3);
        Assertions.assertEquals(mockUserDao.getUser(user4.getId()),user4);
        getAll();*/

    }

    @Test
    void getUser() {
       User user =  mockUserDao.get("user1");
       Assertions.assertEquals(user.getId(),"user1");
    }

    @Test
    void getAll() {
        List<User> list = mockUserDao.getAll();
        for(User u : list){
            System.out.println(u);
        }
    }

    @Test
    void deleteAll() {
        mockUserDao.deleteAll();
    }

    @Test
    void getCount() {
       int count = mockUserDao.getCount();
       Assertions.assertEquals(count,4);

    }

    @Test
    void update() {
        User user = User.builder().id("user4").name("유저4@@")
                .password("1").level(Level.BRONZE).login(40).recommend(40).build();

        mockUserDao.update(user);

        Assertions.assertEquals(mockUserDao.get(user.getId()).getName(),"유저4@@");

    }
}