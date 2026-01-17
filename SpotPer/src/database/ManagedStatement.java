package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagedStatement implements AutoCloseable{
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    public ManagedStatement(String url, String sql) throws SQLException{
        conn = DriverManager.getConnection(url);

        st = conn.createStatement();
        rs = st.executeQuery(sql);
    }

    @Override
    public void close() throws SQLException {
        rs.close();
        st.close();
        conn.close();
    }

    public ResultSet get_resultset(){
        return rs;
    }
}