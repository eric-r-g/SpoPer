package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import database.Database;
import database.DBTypes.Album;
import database.DBTypes.Compositor;
import database.DBTypes.Gravadora;
import database.DBTypes.Playlist;

public class ExecutarConsulta {
	Scanner sc;
	Database db;
	int op;
	
	public ExecutarConsulta(Scanner scanner, Database banco) {
		sc = scanner;
		db = banco;
	}
	
	public void janelaConsulta() {
		System.out.println("\n=== Executar Consulta ===");
		
		do {
			System.out.println("Digite a proxima consulta");
			System.out.println("1 - albuns com preco de compra maior que a media");
			System.out.println("2 - gravadora com maior numero de playlist com dvorack");
			System.out.println("3 - compositor com maior numero de faixas nas playlists");
			System.out.println("4 - playlist com todas as faixas concerto e barroco");
			System.out.println("0 - voltar para menu");
	        System.out.print("> ");
	        
	        op = sc.nextInt();
	        sc.nextLine(); 
	        
	        switch(op) {
	        	case 0 -> System.out.println("Voltando...");
	        	case 1 -> consultaAlbunsComPrecoMaiorMedia();
	        	case 2 -> consultaGravMaisDvorack();
	        	case 3 -> consultaCompMaisPlaylist();
	        	case 4 -> consultaPlaylistConcertoBarroco();
	        	default -> System.out.println("Opção invalida, digite uma operação valida.");
	        }
		} while (op != 0);
	}
	
	private void consultaAlbunsComPrecoMaiorMedia() {
		try {
			ArrayList<Album> albuns = db.mostrarAlbunsPrecoMaiorMedia();
			
			System.out.println("-- Resultado da consulta- -");
			for (Album a : albuns) {
				System.out.println(a.cod + " | nome - " + a.nome + " | " + a.preco_cmpr);
			}
			System.out.println("--------------------------");
		} catch (SQLException e) {
			System.out.println("Erro na execução da consulta: " + e);
		}
	}
	
	private void consultaGravMaisDvorack() {
		try {
			Gravadora g = db.mostrarGravMaisDvorak();
		
			System.out.println("-- Resultado da consulta --");
			System.out.println(g.cod + " | nome - " + g.nome + " | " + g.endereco);
			System.out.println("--------------------------");
		} catch (SQLException e) {
			System.out.println("Erro na execução da consulta: " + e);
		}
	}
	
	private void consultaCompMaisPlaylist() {
		try {
			Compositor c = db.mostrarCompMaisPlaylists();
		
			System.out.println("-- Resultado da consulta --");
			System.out.println(c.cod + " | nome - " +  c.nome + " | " + c.periodo_music);
			System.out.println("--------------------------");
		} catch (SQLException e) {
			System.out.println("Erro na execução da consulta: " + e);
		}
	}
	
	private void consultaPlaylistConcertoBarroco() {
		try {
			ArrayList <Playlist> playlists = db.mostrarPlaylistConcertoBarroco();
			System.out.println("-- Resultado da consulta --");
			for (Playlist p : playlists) {
				System.out.println(p.cod + " | nome - " + p.nome + " | " + p.tempo_total);
			}
			System.out.println("--------------------------");
		} catch (SQLException e) {
			System.out.println("Erro na execução da consulta: " + e);
		}
	}
}
