package app;

import java.util.Scanner;
import database.*;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static Database db = new Database();
	private static Menu menu;
	private static CriarPlaylist cp;
	

    public static void main(String[] args) {
    	cp = new CriarPlaylist(sc, db);
    	menu = new Menu(sc, cp);
    	menu.janelaMenu();
    }
}
