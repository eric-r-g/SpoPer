package app;

import java.util.Scanner;

import database.Database;

public class Menu {
	private static Scanner sc;
	private static CriarPlaylist criar;
	private static EditarPlaylist editar;
	private static ExecutarConsulta executar;
	
	
	public Menu(Scanner scanner, CriarPlaylist cp, EditarPlaylist ed, ExecutarConsulta exec) {
		sc = scanner;
		criar = cp;
		editar = ed;
		executar = exec;
	}
	
	
	public void janelaMenu() {
		int op;
		do {
			System.out.println("\n========== MENU ==========");
            System.out.println("1 - Criar playlist");
            System.out.println("2 - Manutação de playlist");
            System.out.println("3 - Executar consultas");
            System.out.println("0 - Sair");
            System.out.print("> ");
            
            op = sc.nextInt();
            sc.nextLine(); 
            
            switch(op) {
            	case 0 -> System.out.println("Saindo...");
            	case 1 -> criar.janelaCriar();
            	case 2 -> editar.janelaEditar();
            	case 3 -> executar.janelaConsulta();
            	default -> System.out.println("Opção invalida, digite uma operação valida.");
            }
            
            
		} while(op != 0);
	}
}
