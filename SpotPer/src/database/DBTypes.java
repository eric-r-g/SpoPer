package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

class Album{
    public int cod;
    public String nome;
    public String descricao;
    public String gravadora; // o nome da gravadora; não a chave
    public float preco_cmpr;
    public LocalDate data_cmpr;
    public LocalDate data_grav;

    public Album(ResultSet rs) throws SQLException{
        cod = rs.getInt(0);
        nome = rs.getString(1);
        descricao = rs.getString(2);
        gravadora = rs.getString(3);
        preco_cmpr = rs.getFloat(4);
        data_cmpr = LocalDate.parse(rs.getString(5));
        data_grav = LocalDate.parse(rs.getString(6));
    }
}

class Playlist{
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

class Faixa{
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

class Gravadora{
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

class Compositor{
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