package database.DBTypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Compositor {
    public int cod;
    public String nome;
    public String local_nasc;
    public LocalDate data_nasc;
    public LocalDate data_morte;
    public String periodo_music; // Nome do periodo; não a chave

    public Compositor(ResultSet rs) throws SQLException{
        cod = rs.getInt(0);
        nome = rs.getString(1);
        local_nasc = rs.getString(2);
        data_nasc = LocalDate.parse(rs.getString(3));
        data_morte = LocalDate.parse(rs.getString(4));
        periodo_music = rs.getString(5);
    }
}
