package br.edu.ifpb.daweb.elenilson.projetodaweb.business.services;

public class Printer {
    public static void print(String message) {
        System.out.println(message);
    }

    public static void print(int number) {
        System.out.println(number);
    }

    public static void print(Object obj) {
        System.out.println(obj);
    }

}