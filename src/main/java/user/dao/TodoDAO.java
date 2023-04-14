package user.dao;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import user.domain.TodoVO;
import user.sqlService.SqlService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Setter @Getter @Service
@RequiredArgsConstructor
public class TodoDAO {


    private final JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;

    private final RowMapper<TodoVO> rowMapper;

    private final SqlService sqlService;

    public void insert(TodoVO vo){

        jdbcTemplate.update(sqlService.getSql("todoAdd")
                ,vo.getTitle(),Date.valueOf(vo.getDueDate()),vo.isFinished(),vo.getWriter());
    }

    public void deleteAll(){
        jdbcTemplate.update(sqlService.getSql("todoDeleteAll"));
    }

    public TodoVO getTodo(int tno){

        TodoVO vo = jdbcTemplate.queryForObject(sqlService.getSql("todoGet"),rowMapper ,tno);

        return vo;

    }

    public List<TodoVO> getAllByList(){
        List<TodoVO> list = new ArrayList<>();

        list = jdbcTemplate.query(sqlService.getSql("todoGetAll"),rowMapper);

        return list;
    }x


    public void updateOne(TodoVO vo){

        jdbcTemplate.update(sqlService.getSql("todoUpdate")
                ,vo.getTitle(),Date.valueOf(vo.getDueDate()),vo.isFinished(),vo.getWriter(),vo.getTno());

    }

    public void sortTno(){

        jdbcTemplate.update("set @count=0");
        jdbcTemplate.update(" update todo set tno = @count:=@count+1");
        jdbcTemplate.update(" alter table todo auto_increment = 1");
    }


}
