package app;

import java.util.Scanner;

import database.Database;

public class EditarPlaylist {
	Scanner sc;
	Database db;
	int op;
	
	public EditarPlaylist(Scanner scanner, Database banco) {
		sc = scanner;
		db = banco;
	}
}
