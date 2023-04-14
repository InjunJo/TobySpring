package com.example.tobyspring;

import user.domain.User;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void execute(StatementStrategy strategy){

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = strategy.makePreStmt(conn);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn != null){

                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(ps != null){

                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }


    }
    public void executeSql(String sql){

        execute(new StatementStrategy() {
            @Override
            public PreparedStatement makePreStmt(Connection conn) throws SQLException {
                PreparedStatement preStmt = conn.prepareStatement(sql);
                return preStmt;
            }
        });

    }

    public void executeAdd(String sql,User user){
        execute(new StatementStrategy() {
            @Override
            public PreparedStatement makePreStmt(Connection conn) throws SQLException {
                PreparedStatement preStmt = conn.prepareStatement(sql);
                preStmt.setString(1,user.getId());
                preStmt.setString(2,user.getName());
                preStmt.setString(3,user.getPassword());

                return preStmt;
            }
        });

    }

    public int executeGetCount(){
        int count = executeQuery(new StatementStrategy() {
            @Override
            public PreparedStatement makePreStmt(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("select count(*) from users");
                return ps;
            }
        });
        return count;

    }

    public User executeGet(String id){
        User user = new User();

        Map<String,String> map = new HashMap<>();

        map = executeQuery(new StatementStrategy() {
            @Override
            public PreparedStatement makePreStmt(Connection conn) throws SQLException {
                PreparedStatement preStmt = conn.prepareStatement("select * from users where id=?");
                preStmt.setString(1,id);

                return preStmt;
            }
        },User.class);

        user.setId(map.get("id"));
        user.setPassword(map.get("password"));
        user.setName(map.get("name"));


        return user;
    }


    public int executeQuery(StatementStrategy strategy){
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;

        int counter = 0;


        try {
            conn = dataSource.getConnection();

            preStmt = strategy.makePreStmt(conn);

            resultSet = preStmt.executeQuery();


            while(resultSet.next()){

                counter = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn !=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }}
            if(preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(resultSet !=null){

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return counter;
    }

    public <T> T executeQuery1(StatementStrategy strategy, ResultSetResult<T> resultSetResult){
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;

        T t = null;

        try {
            conn = dataSource.getConnection();

            preStmt = strategy.makePreStmt(conn);

            resultSet = preStmt.executeQuery();

            t = resultSetResult.getResult(resultSet);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn !=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }}
            if(preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(resultSet !=null){

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return t;
    }


    public Map executeQuery(StatementStrategy strategy, Class clazz){
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet resultSet = null;

        Map<String,Object> map = new HashMap<>();

        Field[] fields = clazz.getDeclaredFields();


        try {
            conn = dataSource.getConnection();

            preStmt = strategy.makePreStmt(conn);

            resultSet = preStmt.executeQuery();


            while(resultSet.next()){

                for(Field f : fields){

                    map.put(f.getName(), resultSet.getObject(f.getName()));
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn !=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }}
            if(preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(resultSet !=null){

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return map;
    }

}
