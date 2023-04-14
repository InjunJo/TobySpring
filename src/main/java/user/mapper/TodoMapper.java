package user.mapper;

import org.springframework.jdbc.core.RowMapper;
import user.domain.TodoVO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoMapper implements RowMapper<TodoVO> {

    @Override
    public TodoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TodoVO vo = TodoVO.builder().tno(rs.getInt("tno")).title(rs.getString("title"))
                .finished(rs.getBoolean("finished")).writer(rs.getString("writer")).build();

        return vo;
    }
}
