package app;

import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static Menu menu = new Menu(sc);

    public static void main(String[] args) {
       menu.inicializarMenu();
    }
}
