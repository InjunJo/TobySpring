package user.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import user.domain.Level;
import user.domain.User;

import java.util.List;
@Transactional
public interface UserService {


    void add(User user);

    @Transactional(readOnly = true)
    User get(String id);

    @Transactional(readOnly = true)
    List<User> getAll();

    void deleteAll();

    void update(User user);

    boolean checkUpgradeLevel(User user);

    void upgradeLevel(User user) ;

    public void upgradeLevels();

    public void printUser(String id);

    public void printUsers();


}
