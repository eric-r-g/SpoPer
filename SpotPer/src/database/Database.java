package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import database.DBTypes.*;

public class Database{
    private static final String url = "jdbc:postgresql://localhost/?user=SpotPer&password=12345678";

    public static ArrayList<Album> mostrarAlbuns() throws SQLException{
        ArrayList<Album> albuns = new ArrayList<Album>();

        String sql = """
            SELECT a.cod, a.nome, a.descricao, g.nome, a.preco_cmpr, a.data_cmpr, a.data_grav
            FROM album a, gravadora g
            WHERE a.gravadora = g.cod;
        """;

        try(ManagedStatement ms = new ManagedStatement(url, sql)){
            ResultSet rs = ms.get_resultset();

            while(rs.next()){
                albuns.add(new Album(rs));
            }
        }
        
        return albuns;
    }

    public static ArrayList<Playlist> mostrarPlaylists() throws SQLException{
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        String sql = """
            SELECT *
            FROM playlist;
        """;

        try(ManagedStatement ms = new ManagedStatement(url, sql)){
            ResultSet rs = ms.get_resultset();

            while(rs.next()){
                playlists.add(new Playlist(rs));
            }
        }

        return playlists;
    }

    public ArrayList<Faixa> mostrarFaixasPlaylist(int cod) throws SQLException{
        ArrayList<Faixa> faixas = new ArrayList<Faixa>();

        // Fazer consultas diferentes pra esses atributos separados seria melhor será?
        String sql = """
            SELECT f.cod, f.nome, f.pos_album, a.nome, f.descricao, f.tipo_grav, tc.descricao_comp
            FROM faixa f, playlist_contem pc, meio_fisico m, album a, tipo_composicao tc
            WHERE f.cod = pc.faixa AND pc.playlist = ? AND f.disco = m.cod AND m.album = a.cod AND f.tipo_comp = tc.cod;
        """;

        try(ManagedPreparedStatement mps = new ManagedPreparedStatement(url, sql)){
            PreparedStatement pst = mps.get_preparedstatement();

            pst.setInt(0, cod);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                faixas.add(new Faixa(rs));
            }
        }

        return faixas;
    }

    public static void criarPlaylist(String nome, int cod, Set<Integer> faixas) throws SQLException{
        String sql = """
            INSERT INTO playlist VALUES (?, ?, DATE ?, ?);
        """;

        try(ManagedPreparedStatement mps = new ManagedPreparedStatement(url, sql)){
            PreparedStatement pst = mps.get_preparedstatement();

            pst.setInt(0, cod);
            pst.setString(1, nome);
            pst.setString(2, LocalDate.now().toString());
            // Tempo total tá aleatorizado baseado na quantidade de faixas
            pst.setInt(3, new Random().nextInt(10)*faixas.size());

            pst.executeUpdate();
            pst.close();
        }

        sql = """
            INSERT INTO playlist_contem VALUES (?, ?, NULL, 0);
        """;

        try(ManagedPreparedStatement mps = new ManagedPreparedStatement(url, sql)){
            PreparedStatement pst = mps.get_preparedstatement();

            for(Integer faixa : faixas){
                pst.setInt(0, cod);
                pst.setInt(1, faixa);

                pst.executeUpdate();
            }
        }
    }

    // TODO: Atualizar tempo total
    public static void inserirFaixaPlaylist(int playlist, int faixa) throws SQLException{
        String sql = """
            INSERT INTO playlist_contem VALUES (?, ?, NULL, 0);
        """;

        try(ManagedPreparedStatement mps = new ManagedPreparedStatement(url, sql)){
            PreparedStatement pst = mps.get_preparedstatement();

            pst.setInt(0, playlist);
            pst.setInt(1, faixa);

            pst.executeUpdate();
        }
    }

    // TODO: Atualizar tempo total
    public static void removerFaixaPlaylist(int playlist, int faixa) throws SQLException{
        String sql = """
            REMOVE FROM playlist_contem p WHERE p.playlist = ? AND p.faixa = ?;
        """;

        try(ManagedPreparedStatement mps = new ManagedPreparedStatement(url, sql)){
            PreparedStatement pst = mps.get_preparedstatement();
        
            pst.setInt(0, playlist);
            pst.setInt(1, faixa);

            pst.executeUpdate();
        }
    }

    public static ArrayList<Album> mostrarAlbunsPrecoMaiorMedia() throws SQLException{
        ArrayList<Album> albuns = new ArrayList<Album>();

        String sql = "SELECT * FROM mostrarAlbunsPrecoMaiorMedia;";
        try(ManagedStatement ms = new ManagedStatement(url, sql)){
            ResultSet rs = ms.get_resultset();

            while(rs.next()){
                albuns.add(new Album(rs));
            }
        }

        return albuns;
    }

    public Gravadora mostrarGravMaisDvorak() throws SQLException{
        String sql = "SELECT * FROM mostrarGravMaisDvorak";
        try(ManagedStatement ms = new ManagedStatement(url, sql)){
            ResultSet rs = ms.get_resultset();

            if(rs.next()) new Gravadora(rs);
            return null;
        }
    }

    public Compositor mostrarCompMaisPlaylists() throws SQLException{
        String sql = "SELECT * FROM mostrarCompMaisPlaylists";
        try(ManagedStatement ms = new ManagedStatement(url, sql)){
            ResultSet rs = ms.get_resultset();

            if(rs.next()) new Compositor(rs);
            return null;
        }
    }

    public ArrayList<Playlist> mostrarPlaylistConcertoBarroco() throws SQLException{
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        String sql = "SELECT * FROM mostrarPlaylistConcertoBarroco";
        try(ManagedStatement ms = new ManagedStatement(url, sql)){
            ResultSet rs = ms.get_resultset();

            while(rs.next()){
                playlists.add(new Playlist(rs));
            }
        }

        return playlists;
    }
}