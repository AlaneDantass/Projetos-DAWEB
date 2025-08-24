package br.edu.ifpb.daweb.elenilson.projetodaweb.business.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class Reader {
    private Scanner scanner = new Scanner(System.in);

    public String ler() {
        return scanner.nextLine();
    }
    public int lerNum() {
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }
}
