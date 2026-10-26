import java.util.ArrayList;
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
					+ "\n8. Planifier Repas"
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
		        if (type < 1 || type > 3) {
		            System.out.println("Type invalide.");
		            return;
		        }
				System.out.print("Nom: ");
				String Nom = sc.next();
				System.out.print("Age: ");
				int age = sc.nextInt();
				System.out.print("Poids (kg): ");
				double poid = sc.nextDouble();
				System.out.print("Regime (carnivore/herbivore/omnivore): ");
				String regime = sc.next();
				System.out.print("Statut de sante (ex: Bon): ");
				String sante = sc.next();
				Animal animal = null;
				
				//Nom,age,poid,regime,sante
				if (type == 1) {
					animal = new Oiseau(Nom,age,poid,regime,sante);
				} else if (type == 2) {
					animal = new Mammifere();
				} else if (type == 3) {
					animal = new Reptile();
				}
				zoo.ajouterAnimal(animal);
				break;
			case 3:
				
				System.out.print("Animal à affecter (nom ou critère): ");
				String critereAnimal = sc.nextLine();
				System.out.print("Nom de l'enclos: ");
				String nomEnclos = sc.nextLine();

				
				ArrayList<Animal> trouves = zoo.rechercherAnimal(critereAnimal);
				Enclos enclosTrouve = zoo.rechercherEnclos(nomEnclos);

				
				if (trouves.isEmpty()) {
					System.out.println("Aucun animal trouvé.");
				} else if (enclosTrouve == null) {
					System.out.println("Aucun enclos trouvé.");
				} else {
					try {
						zoo.affecterAnimalEnclos(trouves.get(0), enclosTrouve);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case 4:
				System.out.print("Critère de recherche: ");
				String critere = sc.nextLine();
				zoo.rechercherAnimal(critere);
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