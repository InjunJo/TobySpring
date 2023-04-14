package user.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import user.policy.UserLevelUpgradePolicy;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;
import java.util.List;

@Getter @Setter @AllArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final UserLevelUpgradePolicy policy;


    @Override
    public void add(User user){

        if(user.getLevel() == null){
            user.setLevel(Level.BASIC);

        }

        userDao.add(user);
    }

    @Override
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {

        return userDao.getAll();
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public boolean checkUpgradeLevel(User user){

        return policy.checkUpgradeLevel(user);
    }

    @Override
    public void upgradeLevel(User user){

        if(checkUpgradeLevel(user)){
            user.upgradeLevel();
            userDao.update(user);
        }

    }

    @Override
    public void upgradeLevels()  {

        List<User> list = userDao.getAll();

        boolean changed = false;

        for(User u :list){

            upgradeLevel(u);
        }

    }

    @Override
    public void printUser(String id){
        User user = userDao.get(id);
        System.out.println(user);

    }
    @Override
    public void printUsers(){
        List<User> list = userDao.getAll();

        for (User u : list){
            printUser(u.getId());
        }
    }



}
