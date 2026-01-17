package app;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import database.*;

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
		sc.nextLine();
		Set <Integer> faixas = new HashSet <Integer>(); 
		
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
	        	case 1 -> incluirFaixa();
	        	case 2 -> removerFaixa();
	        	case 3 -> alterarNome();
	        	case 4 -> terminarPlaylist();
	        	default -> System.out.println("Opção invalida, digite uma operação valida.");
	        }
		} while (op != 0 || op != 4);
	}
	
	public void incluirFaixa() {
		//ToDo: funcao
	}
	
	public void removerFaixa() {
		//ToDo: funcao
	}
	
	private void alterarNome() {
		System.out.println("Digite o novo nome da playlist");
		System.out.print("> ");
		
		String nome = sc.nextLine();
		sc.nextLine();
	}
	
	public void terminarPlaylist() {
		//ToDo: funcao
	}
}
