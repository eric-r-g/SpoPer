package app;

import java.util.Scanner;

import database.Database;

public class ExecutarConsulta {
	Scanner sc;
	Database db;
	int op;
	
	public ExecutarConsulta(Scanner scanner, Database banco) {
		sc = scanner;
		db = banco;
	}
}
