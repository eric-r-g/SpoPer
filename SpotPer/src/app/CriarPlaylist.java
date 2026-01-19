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
	int op;
	
	public CriarPlaylist(Scanner scanner) {
		sc = scanner;
	}
	
	public void janelaCriar() {
		System.out.println("\n=== Criar Playlist ===");
		System.out.println("Digite o nome da playlist");
		System.out.print("> ");
		
		String nome = sc.nextLine();
		
		Set <Integer> faixas_playlist = new HashSet <Integer>(); 
		
		
		do {
			System.out.println("Digite a proxima operação");
			System.out.println("1 - Incluir faixa");
			System.out.println("2 - Remover faixa");
			System.out.println("3 - Alterar nome");
			System.out.println("4 - Terminar criação");
			System.out.println("0 - Voltar para menu");
	        System.out.print("> ");
	        
	        op = sc.nextInt();
	        sc.nextLine(); 
	        
	        switch(op) {
	        	case 0 -> System.out.println("Voltando...");
	        	case 1 -> incluirFaixa(faixas_playlist);
	        	case 2 -> removerFaixa(faixas_playlist);
	        	case 3 -> alterarNome(nome);
	        	case 4 -> terminarPlaylist(nome, faixas_playlist);
	        	default -> System.out.println("Opção inválida, digite uma operação válida.");
	        }
		} while (op != 0 && op != 4);
	}
	
	public void incluirFaixa(Set <Integer> faixas_playlist) {
		try {
			ArrayList<Album> albuns = Database.mostrarAlbuns();
			
			for(Album a : albuns) {
				System.out.println(a.cod + " | nome - " + a.nome + " | " + a.descricao);
			}
			
			System.out.println("Escolha um album para olhar as faixas (digite o código)");
			System.out.print("> ");
			
			int ind_p = sc.nextInt();
			sc.nextLine();
			
			ArrayList <Faixa> faixas = Database.mostrarFaixasAlbum(ind_p);
			
			
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
			
			System.out.println("Escolha uma faixa não escolhida para adicionar a playlist (digite o código)");
			System.out.print("> ");
			
			int ind_f = sc.nextInt();
			sc.nextLine();
			
			if (faixas_playlist.contains(ind_f)) {
				System.out.println("Faixa já escolhida, digite um valor válido");
			} else if (!faixas_possiveis.contains(ind_f)){
				System.out.println("Faixa inexistente, digite um valor válido");
			} else {
				System.out.println("Faixa Adicionada com sucesso");
				faixas_playlist.add(ind_f);
			}
		} catch (SQLException e) {
			System.out.println("Erro na inclusão da faixa: " + e);
		}
	}
	
	
	public void removerFaixa(Set <Integer> faixas_playlist) {
		try {
			ArrayList <Faixa> faixas = Database.mostrarFaixas();
			for(Faixa f : faixas) {
				if (faixas_playlist.contains(f.cod)) {
					System.out.println(f.cod + " | nome - " + f.nome + " | " + f.descricao);
				}
			}
			
			System.out.println("Escolha uma faixa para remover da playlist (digite o código)");
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
			System.out.println("Erro na remocao da faixa: " + e);
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
				ArrayList <Playlist> playlists = Database.mostrarPlaylists();
				playlists.sort(Comparator.comparingInt(Playlist::getCod));
				int ind = playlists.get(playlists.size() - 1).cod + 1;
				
				Database.criarPlaylist(nome, ind, faixas_playlist);
				System.out.println("Playlist incluida com sucesso.");
			} catch (SQLException e) {
				System.out.println("Erro na inclusão da playlist: " + e);
			}
		}
	}
}
