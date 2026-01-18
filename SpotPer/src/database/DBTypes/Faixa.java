package database.DBTypes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Faixa {
    public int cod;
    public String nome;
    public int pos_album;
    public String album; // nome do album; não a chave
    public String descricao;
    public String tipo_grav;
    public String tipo_comp; // descrição do tipo; não a chave

    public Faixa(ResultSet rs) throws SQLException{
        cod = rs.getInt(1);
        nome = rs.getString(2);
        pos_album = rs.getInt(3);
        album = rs.getString(4);
        descricao = rs.getString(5);
        tipo_grav = rs.getString(6);
        tipo_comp = rs.getString(7);
    }
}
