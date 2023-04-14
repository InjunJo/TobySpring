package user.policy;

import user.domain.Level;
import user.domain.User;

public class BasicUserLevelUpgrade implements UserLevelUpgradePolicy {

    private static final int MIN_LOGIN_FOR_SILVER = 40;
    private static final int MIN_RECOMMEND_FOR_GOLD = 30;
    private static final int MIN_LOGIN_FOR_BRONZE = 20;

    @Override
    public boolean checkUpgradeLevel(User user) {
        Level currentlevel = user.getLevel();

        switch (currentlevel){
            case BASIC:  return (user.getLogin() >= MIN_LOGIN_FOR_BRONZE);
            case BRONZE: return (user.getLogin() >= MIN_LOGIN_FOR_SILVER);
            case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD: return false;
            default: throw new IllegalArgumentException("unknown Level : "+currentlevel);
        }
    }
}
