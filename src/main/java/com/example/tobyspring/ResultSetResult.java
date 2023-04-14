package com.example.tobyspring;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetResult<T> {

    public T getResult(ResultSet rs) throws SQLException;
}
