package business.utils;

public class Printer {

//	Classe de impressão para facilitar caso seja necessário alterar a forma de impressão

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
