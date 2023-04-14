package user.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import user.dao.UserDao;
import user.domain.User;
import user.exception.TestUserServiceException;
import user.policy.UserLevelUpgradePolicy;

import java.util.List;

@Log4j2 @Transactional @Component
public class TestUserService extends UserServiceImpl{

    private static String id = "user3";

    @Autowired
    public TestUserService(UserDao userDao, UserLevelUpgradePolicy policy) {
        super(userDao, policy);
    }


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
}
