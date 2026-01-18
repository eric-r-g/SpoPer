package database.DBTypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Album {
    public int cod;
    public String nome;
    public String descricao;
    public String gravadora; // o nome da gravadora; não a chave
    public float preco_cmpr;
    public LocalDate data_cmpr;
    public LocalDate data_grav;

    public Album(ResultSet rs) throws SQLException{
        cod = rs.getInt(1);
        nome = rs.getString(2);
        descricao = rs.getString(3);
        gravadora = rs.getString(4);
        preco_cmpr = rs.getFloat(5);
        data_cmpr = LocalDate.parse(rs.getString(6));
        data_grav = LocalDate.parse(rs.getString(7));
    }
}
