package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagedPreparedStatement implements AutoCloseable{
    private Connection conn;
    private PreparedStatement pst;

    public ManagedPreparedStatement(String url, String sql) throws SQLException{
        conn = DriverManager.getConnection(url);
        pst = conn.prepareStatement(sql);
    }

    @Override
    public void close() throws SQLException {
        pst.close();
        conn.close();
    }

    public PreparedStatement get_preparedstatement(){
        return pst;
    }
    
}
