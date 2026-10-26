import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Zoo zoo = new Zoo("Zoo de Safi");
		int choix;

		do {
			System.out.println("\n********** Menu **********"
					+ "\n1. Ajouter un enclos"
					+ "\n2. Ajouter un animal"
					+ "\n3. Affecter un animal à un enclos"
					+ "\n4. Rechercher un animal"
					+ "\n5. Calculer le coût de nourriture mensuel"
					+ "\n6. Voir les alertes"
					+ "\n7. Afficher le zoo"
					+ "\n0. Quitter");

			System.out.print("Entrer choix: ");
			choix = sc.nextInt();
			sc.nextLine();

			switch (choix) {
			case 0:
				System.out.println("Au revoir !");
				break;
			case 1:
				System.out.print("Nom de l'enclos: ");
				String nom = sc.nextLine();
				System.out.print("Capacité max: ");
				int capacite = sc.nextInt();
				sc.nextLine();
				System.out.print("Type d'environnement(TERRESTRE, "
						+ "AQUATIQUE, "
						+ "AERIEN, "
						+ "SAVANE, "
						+ "FORET, "
						+ "POLAIRE, "
						+ "DESERTIQUE): ");
					    
				String env = sc.nextLine();
				zoo.ajouterEnclos(new Enclos(nom, capacite, env));
				System.out.println("Enclos ajouté.");
				break;
			case 2:
				System.out.print("1. Oiseau  2. Mammifere  3. Reptile : ");
				int type = sc.nextInt();
				sc.nextLine();
				Animal animal = null;
				// À modifier si les autres ajoutent des paramètres aux constructeurs
				if (type == 1) {
					animal = new Oiseau();
				} else if (type == 2) {
					animal = new Mammifere();
				} else if (type == 3) {
					animal = new Reptile();
				}
				zoo.ajouterAnimal(animal);
				break;
			case 3:
				zoo.afficherAnimaux();
				System.out.print("Numéro de l'animal: ");
				int numAnimal = sc.nextInt();
				zoo.afficherEnclos();
				System.out.print("Numéro de l'enclos: ");
				int numEnclos = sc.nextInt();
				sc.nextLine();
				zoo.affecterParNumero(numAnimal, numEnclos);
				break;
			case 4:
				System.out.print("Critère de recherche: ");
				String critere = sc.nextLine();
				zoo.afficherRecherche(critere);
				break;
			case 5:
				System.out.print("Prix du kilo: ");
				double prix = sc.nextDouble();
				sc.nextLine();
				zoo.afficherCoutNourriture(prix);
				break;
			case 6:
				zoo.afficherAlertes();
				break;
			case 7:
				zoo.afficherZoo();
				break;
			default:
				System.out.println("Choix invalide !");
			}
		} while (choix != 0);

		sc.close();
	}
}