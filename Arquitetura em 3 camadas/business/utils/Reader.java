package business.utils;

import java.util.Scanner;

/*Classe de leitura de dados para facilitar caso seja necessário alterar a forma de leitura
*/
public class Reader {

	private static Scanner scanner = new Scanner(System.in);

	public static String ler() {
		return scanner.nextLine();
	}

	public static int lerNum() {
		int num = scanner.nextInt();
		scanner.nextLine(); // limpa o buffer
		return num;
	}
}