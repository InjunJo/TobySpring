package user.domain;

import lombok.*;
import user.myJpa.MyEntity;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String id;

    private String name;

    private String password;

    private Level level;

    private int recommend;

    private int login;

    public void upgradeLevel(){

        Level nextLevel = level.nextLevel();

        if(nextLevel ==null){
            throw new IllegalStateException(this.level+"은 업그레이드가 불가 합니다.");
        }else this.level = nextLevel;
    }

}
