package user.dao;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.sqlService.SqlService;
import user.domain.User;

import javax.sql.DataSource;
import java.util.List;

@Setter @Getter @Transactional
@Repository @AllArgsConstructor
public class UserDaoJdbc implements UserDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper;
    private final SqlService sqlService;


    @Override
    public void add(User user){

        jdbcTemplate.update(this.sqlService.getSql("userAdd")
                ,user.getId(),user.getName(),user.getPassword(),user.getLevel().intValue()
                ,user.getLogin(),user.getRecommend());
    }

    @Override
    public User get(String id) {

        User user = jdbcTemplate.queryForObject(this.sqlService.getSql("userGet")
                ,new Object[]{id}, userMapper);

        return user;
    }
    @Override
    public List<User> getAll(){

        return jdbcTemplate.query(this.sqlService.getSql("userGetAll"),userMapper);
    }

    @Override
    public void deleteAll() {

        jdbcTemplate.update(this.sqlService.getSql("userDeleteAll"));

    }
    @Override
    public int getCount()  {


        return jdbcTemplate.queryForObject(this.sqlService.getSql("userGetCount"), Integer.class);
    }


    @Override
    public void update(User user){

        String id = user.getId();


        jdbcTemplate.update(this.sqlService.getSql("userUpdate")
                ,user.getName(),user.getPassword(),user.getLevel().intValue()
                ,user.getLogin(),user.getRecommend(),user.getId());

    }


}
