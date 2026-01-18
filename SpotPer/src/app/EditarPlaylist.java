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
	Database db;
	int op;
	
	public EditarPlaylist(Scanner scanner, Database banco) {
		sc = scanner;
		db = banco;
	}
	
	public void janelaEditar() {
		System.out.println("\n=== Criar Playlist ===");
		try {
			ArrayList <Playlist> playlists = db.mostrarPlaylists();
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
				for(Faixa f : db.mostrarFaixasPlaylist(cod_play)) {
					faixas_playlist.add(f.cod);
				}
				
				do {
					System.out.println("Digite a proxima operecao");
					System.out.println("1 - incluir faixa");
					System.out.println("2 - remover faixa");
					System.out.println("0 - voltar pro menu");
			        System.out.print("> ");
			        
			        op = sc.nextInt();
			        sc.nextLine(); 
			        
			        switch(op) {
		        		case 0 -> System.out.println("Voltando...");
		        		case 1 -> incluirFaixa(faixas_playlist, cod_play);
		        		case 2 -> removerFaixa(faixas_playlist, cod_play);
		        		default -> System.out.println("Opção invalida, digite uma operação valida.");
			        }
				} while (op != 0); 
			} else {
				System.out.println("Digite um codigo de playlist valido");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao editar playlist: " + e);
		}
	}
	
	public void incluirFaixa(Set <Integer> faixas_playlist, int cod) {
		try {
			ArrayList<Album> albuns = db.mostrarAlbuns();
			
			for(Album a : albuns) {
				System.out.println(a.cod + " | nome - " + a.nome + " | " + a.descricao);
			}
			
			System.out.println("Escolha um album para olhar as faixas (digite o codigo)");
			System.out.print("> ");
			
			int ind_a = sc.nextInt();
			sc.nextLine();
			
			ArrayList <Faixa> faixas = db.mostrarFaixasAlbum(ind_a);
			
			
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
				db.inserirFaixaPlaylist(ind_f, cod);
				System.out.println("Faixa Adicionada com sucesso");
				faixas_playlist.add(ind_f);
			}
		} catch (SQLException e) {
			System.out.println("Erro na inclusao de faixa: " + e);
		}
	}
	
	public void removerFaixa(Set <Integer> faixas_playlist, int cod) {
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
				
				db.removerFaixaPlaylist(ind, cod);
				System.out.println("Faixa removida com sucesso");
				faixas_playlist.remove(ind);
			} else {
				System.out.println("Digite uma faixa válida");
			}
			
		} catch (SQLException e) {
			System.out.println(" Erro na remocao de faixa: " + e);
		}
	}
	
}
