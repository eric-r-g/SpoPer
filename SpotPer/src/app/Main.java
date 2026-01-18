package app;

import java.util.Scanner;
import database.*;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static Database db = new Database();
	private static Menu menu;
	private static CriarPlaylist cp;
	private static EditarPlaylist ed;
	private static ExecutarConsulta ec;
	

    public static void main(String[] args) {
    	cp = new CriarPlaylist(sc, db);
    	ed = new EditarPlaylist(sc, db);
    	ec = new ExecutarConsulta(sc, db);
    	menu = new Menu(sc, cp, ed, ec);
    	menu.janelaMenu();
    }
}
