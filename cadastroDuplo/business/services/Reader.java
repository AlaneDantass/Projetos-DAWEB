package business.services;

import java.util.Scanner;

public class Reader {
    private Scanner scanner;

    public Reader() {
        this.scanner = new Scanner(System.in);
    }
    public String ler() {
        return scanner.nextLine();
    }

    public int lerNum() {
        int resultadoInt = scanner.nextInt();
        scanner.nextLine(); // ESSENCIAL: Consome o '\n' deixado pelo nextInt()
        return resultadoInt; // Adiciona o return que estava faltando
    }

}