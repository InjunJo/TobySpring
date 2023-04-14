package com.example.tobyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {

    public PreparedStatement makePreStmt(Connection conn) throws SQLException;

}
