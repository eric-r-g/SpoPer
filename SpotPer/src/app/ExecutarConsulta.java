package app;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import database.Database;

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
	        	case 1 -> consultaAlbunsComPrecoMaiorMedia();
	        	case 2 -> consultaGravMaisDvorack();
	        	case 3 -> consultaCompMaisPlaylist();
	        	case 4 -> consultaPlaylistConcertoBarroco();
	        	default -> System.out.println("Opção invalida, digite uma operação valida.");
	        }
		} while (op != 0);
	}
	
	private void consultaAlbunsComPrecoMaiorMedia() {
		
	}
	
	private void consultaGravMaisDvorack() {
		
	}
	
	private void consultaCompMaisPlaylist() {
		
	}
	
	private void consultaPlaylistConcertoBarroco() {
		
	}
}
