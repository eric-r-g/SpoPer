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
        cod = rs.getInt(1);
        nome = rs.getString(2);
        data_criacao = LocalDate.parse(rs.getString(3));
        tempo_total = rs.getInt(4);
    }
    
    // Necessario para ordenação
    public int getCod() { return cod; }
}
