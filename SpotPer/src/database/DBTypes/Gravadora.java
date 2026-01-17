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
        cod = rs.getInt(0);
        nome = rs.getString(1);
        endereco = rs.getString(2);
        telefone = rs.getString(3);
        endr_homepage = rs.getString(4);
    }
}
