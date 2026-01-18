package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import database.*;
import database.DBTypes.Album;
import database.DBTypes.Faixa;
import database.DBTypes.Playlist;

public class CriarPlaylist {
	Scanner sc;
	Database db;
	int op;
	
	public CriarPlaylist(Scanner scanner, Database banco) {
		sc = scanner;
		db = banco;
	}
	
	public void janelaCriar() {
		System.out.println("\n=== Criar Playlist ===");
		System.out.println("Digite o nome da playlist");
		System.out.print("> ");
		
		String nome = sc.nextLine();
		
		Set <Integer> faixas_playlist = new HashSet <Integer>(); 
		
		
		do {
			System.out.println("Digite a proxima operecao");
			System.out.println("1 - incluir faixa");
			System.out.println("2 - remover faixa");
			System.out.println("3 - alterar nome");
			System.out.println("4 - terminar criação");
			System.out.println("0 - voltar para menu");
	        System.out.print("> ");
	        
	        op = sc.nextInt();
	        sc.nextLine(); 
	        
	        switch(op) {
	        	case 0 -> System.out.println("Voltando...");
	        	case 1 -> incluirFaixa(faixas_playlist);
	        	case 2 -> removerFaixa(faixas_playlist);
	        	case 3 -> alterarNome(nome);
	        	case 4 -> terminarPlaylist(nome, faixas_playlist);
	        	default -> System.out.println("Opção invalida, digite uma operação valida.");
	        }
		} while (op != 0 && op != 4);
	}
	
	public void incluirFaixa(Set <Integer> faixas_playlist) {
		try {
			ArrayList<Album> albuns = db.mostrarAlbuns();
			
			for(Album a : albuns) {
				System.out.println(a.cod + " | nome - " + a.nome + " | " + a.descricao);
			}
			
			System.out.println("Escolha um album para olhar as faixas (digite o codigo)");
			System.out.print("> ");
			
			int ind_p = sc.nextInt();
			sc.nextLine();
			
			ArrayList <Faixa> faixas = db.mostrarFaixasAlbum(ind_p);
			
			
			Set <Integer> faixas_possiveis = new HashSet <Integer>();
			for(Faixa f : faixas) {
				faixas_possiveis.add(f.cod);
				String escol;
				if (faixas_playlist.contains(f.cod)) {
					escol = "[X]";
				} else {
					escol = "[ ]";
				}
				System.out.println(f.cod + " | " + escol + " | nome - " + f.nome + " | " + f.descricao);
			}
			
			System.out.println("Escolha uma faixa não escolhida para adicionar a playlist (digite o codigo)");
			System.out.print("> ");
			
			int ind_f = sc.nextInt();
			sc.nextLine();
			
			if (faixas_playlist.contains(ind_f)) {
				System.out.println("Faixa já escolhida, digite um valor valido");
			} else if (!faixas_possiveis.contains(ind_f)){
				System.out.println("Faixa inexistente, digite um valor valido");
			} else {
				System.out.println("Faixa Adicionada com sucesso");
				faixas_playlist.add(ind_f);
			}
		} catch (SQLException e) {
			System.out.println("Erro na inclusao de faixa: " + e);
		}
	}
	
	
	public void removerFaixa(Set <Integer> faixas_playlist) {
		try {
			ArrayList <Faixa> faixas = db.mostrarFaixas();
			for(Faixa f : faixas) {
				if (faixas_playlist.contains(f.cod)) {
					System.out.println(f.cod + " | nome - " + f.nome + " | " + f.descricao);
				}
			}
			
			System.out.println("Escolha uma faixa para remover da playlist (digite o codigo)");
			System.out.print("> ");
			
			int ind = sc.nextInt();
			sc.nextLine();
			
			if(faixas_playlist.contains(ind)) {
				System.out.println("Faixa removida com sucesso");
				faixas_playlist.remove(ind);
			} else {
				System.out.println("Digite uma faixa válida");
			}
			
		} catch (SQLException e) {
			System.out.println(" Erro na remocao de faixa: " + e);
		}
	}
	
	private void alterarNome(String nome) {
		System.out.println("Digite o novo nome da playlist");
		System.out.print("> ");
		
		nome = sc.nextLine();
	}
	
	public void terminarPlaylist(String nome, Set <Integer> faixas_playlist) {
		if (faixas_playlist.size() == 0) {
			System.out.println("Não é possivel criar uma playlist vazia.");
		}
		else {
			try {
				ArrayList <Playlist> playlists = db.mostrarPlaylists();
				playlists.sort(Comparator.comparingInt(Playlist::getCod));
				int ind = playlists.get(playlists.size() - 1).cod + 1;
				
				db.criarPlaylist(nome, ind, faixas_playlist);
				System.out.println("Playlist incluida com sucesso.");
			} catch (SQLException e) {
				System.out.println(" Erro na inclusão da playlist: " + e);
			}
		}
	}
}
