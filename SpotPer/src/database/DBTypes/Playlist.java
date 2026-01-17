package database.DBTypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Playlist {
    public int cod;
    public String nome;
    public LocalDate data_criacao;
    public int tempo_total;

    public Playlist(ResultSet rs) throws SQLException{
        cod = rs.getInt(0);
        nome = rs.getString(1);
        data_criacao = LocalDate.parse(rs.getString(2));
        tempo_total = rs.getInt(3);
    }
}
