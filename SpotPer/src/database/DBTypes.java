package database;

import java.time.LocalDate;

record Album(
    int cod,
    String nome,
    String descricao,
    String gravadora, // o nome da gravadora, não a chave
    float preco_cmpr,
    LocalDate data_cmpr,
    LocalDate data_grav
) {}

record Playlist(
    int cod,
    String nome,
    LocalDate data_criacao,
    int tempo_total
 ) {}

record Faixa(
    int cod,
    String nome,
    int pos_album,
    String album, // nome do album, não a chave
    String descricao,
    String tipo_grav,
    String tipo_comp // descrição do tipo, não a chave
) {}