package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import database.Database;
import database.DBTypes.Album;
import database.DBTypes.Faixa;
import database.DBTypes.Playlist;

public class EditarPlaylist {
	Scanner sc;
	int op;
	
	public EditarPlaylist(Scanner scanner) {
		sc = scanner;
	}
	
	public void janelaEditar() {
		System.out.println("\n=== Criar Playlist ===");
		try {
			ArrayList <Playlist> playlists = Database.mostrarPlaylists();
			Set <Integer> cod_playlists = new HashSet <Integer>(); 
			for(Playlist p : playlists) {
				cod_playlists.add(p.cod);
				System.out.println(p.cod + " | nome - " + p.nome + " | " + p.tempo_total);
			}
			System.out.println("Digite o cod da playlist a ser editada");
			System.out.print("> ");
			
			int cod_play = sc.nextInt();
			sc.nextLine();			
			
			if (cod_playlists.contains(cod_play)) {
				Set <Integer> faixas_playlist = new HashSet <Integer>(); 
				for(Faixa f : Database.mostrarFaixasPlaylist(cod_play)) {
					faixas_playlist.add(f.cod);
				}
				
				do {
					System.out.println("Digite a proxima operação");
					System.out.println("1 - Incluir faixa");
					System.out.println("2 - Remover faixa");
					System.out.println("0 - Voltar para o menu");
			        System.out.print("> ");
			        
			        op = sc.nextInt();
			        sc.nextLine(); 
			        
			        switch(op) {
		        		case 0 -> System.out.println("Voltando...");
		        		case 1 -> incluirFaixa(faixas_playlist, cod_play);
		        		case 2 -> removerFaixa(faixas_playlist, cod_play);
		        		default -> System.out.println("Opção inválida, digite uma operação válida.");
			        }
				} while (op != 0); 
			} else {
				System.out.println("Digite um codigo de playlist válido");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao editar playlist: " + e);
		}
	}
	
	public void incluirFaixa(Set <Integer> faixas_playlist, int cod) {
		try {
			ArrayList<Album> albuns = Database.mostrarAlbuns();
			
			for(Album a : albuns) {
				System.out.println(a.cod + " | nome - " + a.nome + " | " + a.descricao);
			}
			
			System.out.println("Escolha um album para olhar as faixas (digite o código)");
			System.out.print("> ");
			
			int ind_a = sc.nextInt();
			sc.nextLine();
			
			ArrayList <Faixa> faixas = Database.mostrarFaixasAlbum(ind_a);
			
			
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
				Database.inserirFaixaPlaylist(cod, ind_f);
				System.out.println("Faixa Adicionada com sucesso");
				faixas_playlist.add(ind_f);
			}
		} catch (SQLException e) {
			System.out.println("Erro na inclusão da faixa: " + e);
		}
	}
	
	public void removerFaixa(Set <Integer> faixas_playlist, int cod) {
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
				
				Database.removerFaixaPlaylist(cod, ind);
				System.out.println("Faixa removida com sucesso");
				faixas_playlist.remove(ind);
			} else {
				System.out.println("Digite uma faixa válida");
			}
			
		} catch (SQLException e) {
			System.out.println(" Erro na remoção da faixa: " + e);
		}
	}
	
}
