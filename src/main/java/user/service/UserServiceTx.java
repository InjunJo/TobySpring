package user.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import user.domain.User;

import java.util.List;

@Setter @Getter
public class UserServiceTx implements UserService{

    private UserService userService;

    private PlatformTransactionManager transactionManager;

    @Override
    public void add(User user) {
        userService.add(user);
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public boolean checkUpgradeLevel(User user) {
        return userService.checkUpgradeLevel(user);
    }

    @Override
    public void upgradeLevel(User user) {
        userService.upgradeLevel(user);
    }

    @Override
    public void upgradeLevels() {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try{

            userService.upgradeLevels();

            this.transactionManager.commit(status);

        }catch (Exception e){
            this.transactionManager.rollback(status);
            throw e;

        }
    }

    @Override
    public void printUser(String id) {
        userService.printUser(id);
    }

    @Override
    public void printUsers() {
        userService.printUsers();
    }
}
