package user.mapper;

import org.springframework.jdbc.core.RowMapper;
import user.domain.Level;
import user.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB로 받은 User 데이터를 User 객체에 맵핑 해줍니다.
 */
public class UserMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setLevel(Level.valueOf(rs.getInt("level")));
        user.setLogin(rs.getInt("login"));
        user.setRecommend(rs.getInt("recommend"));

        return user;
    }
}
