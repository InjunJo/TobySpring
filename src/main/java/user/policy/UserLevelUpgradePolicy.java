package user.policy;

import user.domain.User;

public interface UserLevelUpgradePolicy {

    public boolean checkUpgradeLevel(User user);

}
