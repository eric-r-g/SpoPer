package database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;



public class Database{
    private static final String url = "jdbc:postgresql://localhost/?user=SpotPer&password=12345678";

    /*
        TODO:
            - Diminuir a repetição de código da criação de conexão e execução de query
            - Terminar a consulta de musicas da playlist
            - Terminar as consultas do comentário aqui embaixo vvvvv 
    */
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

        String sql = """
            SELECT a.cod, a.nome, a.descricao, g.nome, a.preco_cmpr, a.data_cmpr, a.data_grav
            FROM album a, gravadora g
            WHERE a.gravadora = g.cod;
        """;

        try(ManagedStatement ms = new ManagedStatement(url, sql)){
            ResultSet rs = ms.get_resultset();

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
                playlists.add(
                    new Playlist(
                        rs.getInt(0),
                        rs.getString(1),
                        LocalDate.parse(rs.getString(2)),
                        rs.getInt(3)
                    )
                );
            }
        }

        return playlists;
    }

    /*public ArrayList<Faixa> mostrarFaixasPlaylist(int cod){
        ArrayList<Faixa> faixas = new ArrayList<Faixa>();



        PreparedStatement st = 

        return faixas;
    }*/

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
}