package app;

import java.util.Scanner;

public class Menu {
	private static Scanner sc = new Scanner(System.in);
	private int op;
	
	public Menu(Scanner scanner) {
		sc = scanner;
	}
	
	public void inicializarMenu() {
		do {
			System.out.println("\n=== MENU ===");
            System.out.println("1 - Criar playlist");
            System.out.println("2 - Manutação de playlist");
            System.out.println("3 - Executar consultas");
            System.out.println("0 - Sair");
            System.out.print("> ");
            
            op = sc.nextInt();
            sc.nextLine(); 
            
            if (op == 0) {
            	System.out.println("Saindo...");
            }
            
            
		} while(op != 0);
	}
}
