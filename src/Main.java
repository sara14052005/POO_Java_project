import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int i;
		
		do {
			System.out.println("********** Menu **********"
					+ "\n1."
					+ "\n2."
					+ "\n3."
					+ "\n4."
					+ "\n0.quitter");
			
			System.out.println("Entrer choix: ");
			i = s. nextInt();
			
			switch(i) {
				case 0 : break;
				case 1 : 
						break;
				case 2 : 
						break;
				case 3 : 
						break;
				case 4 : 
						break;
				default : System.out.println("Choix Invalide!!!!");
			}
			
		}while(i != 0);
	}
}