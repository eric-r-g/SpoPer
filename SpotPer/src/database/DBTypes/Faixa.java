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
        cod = rs.getInt(0);
        nome = rs.getString(1);
        pos_album = rs.getInt(2);
        album = rs.getString(3);
        descricao = rs.getString(4);
        tipo_grav = rs.getString(5);
        tipo_comp = rs.getString(6);
    }
}
