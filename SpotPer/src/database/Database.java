package database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

record Album(
    int cod,
    String nome,
    String descricao,
    String gravadora, // o nome da gravadora, não a chave
    float preco_cmpr,
    LocalDate data_cmpr,
    LocalDate data_grav
) {}

class Playlist{
    public int cod;
    public String nome;
    public LocalDate data_criacao;
    public int tempo_total;
    private HashSet<Integer> faixas; // Dá pra criar uma classe faixas tbm

    public Playlist(int cod, String nome, LocalDate data_criacao, int tempo_total, HashSet<Integer> faixas){
        this.cod = cod;
        this.nome = nome;
        this.data_criacao = data_criacao;
        this.tempo_total = tempo_total;
        this.faixas = faixas;
    }

    public void adicionarFaixa(int faixa) throws SQLException{
        Database.inserirFaixaPlaylist(cod, faixa);

        faixas.add(faixa);
    }

    public void removerFaixa(int faixa) throws SQLException{
        Database.removerFaixaPlaylist(cod, faixa);

        faixas.remove(faixa);
    }
}

public class Database{
    private static final String url = "jdbc:postgresql://localhost/?user=SpotPer&password=12345678";

    /*
    // Todas essas já tão feitas, só puxar a query do script
    Listar os álbuns com preço de compra maior que a média de preços de
    compra de todos os álbuns.
    Listar nome da gravadora com maior número de playlists que possuem
    pelo uma faixa composta pelo compositor Dvorack.
    Listar nome do compositor com maior número de faixas nas playlists
    existentes.
    Listar playlists, cujas faixas (todas) têm tipo de composição “Concerto” e
    período “Barroco”.
    */

    public static ArrayList<Album> mostrarAlbuns() throws SQLException{
        ArrayList<Album> albuns = new ArrayList<Album>();

        Connection conn = DriverManager.getConnection(url);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("""
                SELECT a.cod, a.nome, a.descricao, g.nome, a.preco_cmpr, a.data_cmpr, a.data_grav
                FROM album a, gravadora g
                WHERE a.gravadora = g.cod;
        """);

        while(rs.next()){
            albuns.add(
                new Album(
                    rs.getInt(0),
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getFloat(4),
                    LocalDate.parse(rs.getString(5)),
                    LocalDate.parse(rs.getString(6))
                )
            );
        }

        rs.close();
        st.close();
        conn.close();
        
        return albuns;
    }

    public static ArrayList<Playlist> mostrarPlaylists() throws SQLException{
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        Connection conn = DriverManager.getConnection(url);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("""
                SELECT *
                FROM playlist;
        """);

        while(rs.next()){
            // Com certeza tem forma mais eficiente de fazer isso
            HashSet<Integer> faixas = new HashSet<Integer>();

            PreparedStatement pst = conn.prepareStatement("""
                SELECT f.cod
                FROM playlist_contem pc, faixa f
                WHERE pc.playlist = ? AND pc.faixa = f        
            """);
            pst.setInt(0, rs.getInt(0));
            ResultSet prs = pst.executeQuery();

            while(prs.next()){
                faixas.add(prs.getInt(0));
            }

            playlists.add(
                new Playlist(
                    rs.getInt(0),
                    rs.getString(1),
                    LocalDate.parse(rs.getString(2)),
                    rs.getInt(3),
                    faixas
                )
            );
        }

        rs.close();
        st.close();
        conn.close();

        return playlists;
    }

    public static void criarPlaylist(String nome, int cod, Set<Integer> faixas) throws SQLException{
        Connection conn = DriverManager.getConnection(url);
        
        PreparedStatement stPlay = conn.prepareStatement("""
            INSERT INTO playlist VALUES (?, ?, DATE ?, ?);
        """);

        stPlay.setInt(0, cod);
        stPlay.setString(1, nome);
        stPlay.setString(2, LocalDate.now().toString());
        // Tempo total tá aleatorizado baseado na quantidade de faixas
        stPlay.setInt(3, new Random().nextInt(10)*faixas.size());

        stPlay.executeUpdate();
        stPlay.close();

        PreparedStatement stFaixa = conn.prepareStatement("""
            INSERT INTO playlist_contem VALUES (?, ?, NULL, 0);
        """);

        for(Integer faixa : faixas){
            stFaixa.setInt(0, cod);
            stFaixa.setInt(1, faixa);

            stFaixa.executeUpdate();
        }

        stFaixa.close();

        conn.close();
    }

    public static void inserirFaixaPlaylist(int playlist, int faixa) throws SQLException{
        Connection conn = DriverManager.getConnection(url);

        PreparedStatement st = conn.prepareStatement("""
            INSERT INTO playlist_contem VALUES (?, ?, NULL, 0);
        """);

        st.setInt(0, playlist);
        st.setInt(1, faixa);

        st.executeUpdate();
        st.close();

        conn.close();
    }

    public static void removerFaixaPlaylist(int playlist, int faixa) throws SQLException{
        Connection conn = DriverManager.getConnection(url);

        PreparedStatement st = conn.prepareStatement("""
            REMOVE FROM playlist_contem p WHERE p.playlist = ? AND p.faixa = ?;
        """);

        st.setInt(0, playlist);
        st.setInt(1, faixa);

        st.executeUpdate();
        st.close();

        conn.close();
    }
}