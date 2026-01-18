package database.DBTypes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Gravadora {
    public int cod;
    public String nome;
    public String endereco;
    public String telefone;
    public String endr_homepage;

    public Gravadora(ResultSet rs) throws SQLException{
        cod = rs.getInt(1);
        nome = rs.getString(2);
        endereco = rs.getString(3);
        telefone = rs.getString(4);
        endr_homepage = rs.getString(5);
    }
}
