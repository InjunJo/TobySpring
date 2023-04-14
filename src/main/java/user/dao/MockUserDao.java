package user.dao;

import user.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MockUserDao implements UserDao{

    private Map<String,User> users;

    public MockUserDao(){
        users = new HashMap<>();
    }


    @Override
    public void add(User user) {

        String id = user.getId();

        if(users.get(id) == null){

            users.put(id, user);
        }else {
            throw new IllegalArgumentException(user.getId()+"는 이미 존재합니다.");
        }

    }

    @Override
    public User get(String id) {

        User user = users.get(id);

        if(user == null){
            throw new IllegalStateException(id+"로 존재하는 user가 없습니다.");
        }else{
            return user;
        }

    }

    @Override
    public List<User> getAll() {

        List<User> list = users.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());


        return list;
    }

    @Override
    public void deleteAll() {
        users.clear();
    }

    @Override
    public int getCount() {

        return users.size();
    }

    @Override
    public void update(User user) {

        User user1 = get(user.getId());

        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setLogin(user.getLogin());
        user1.setRecommend(user.getRecommend());
        user1.setLevel(user.getLevel());

        users.replace(user1.getId(),user1);

    }
}
