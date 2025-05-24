package business.services;

import java.util.Scanner;

public class Reader {
	private Scanner scanner = new Scanner(System.in);
	
	public String ler() {
		String resultado = scanner.nextLine();
		scanner.nextInt();
		
		return resultado;
	}
	public int lerNum() {
		return scanner.nextInt();
	}
	
}