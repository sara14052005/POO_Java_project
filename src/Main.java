import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("******* ZooKeeper *******");
		System.out.print("Nom du zoo: ");
		Zoo zoo = new Zoo(sc.next());
		int choix;

		do {
			System.out.println("\n********** Menu **********"
					+ "\n1.  Ajouter un enclos"
					+ "\n2.  Ajouter un animal"
					+ "\n3.  Affecter un animal à un enclos"
					+ "\n4.  Rechercher un animal"
					+ "\n5.  Modifier un animal"
					+ "\n6.  Tester les actions d'un animal"
					+ "\n7.  Ajouter un employé (soigneur / vétérinaire)"
					+ "\n8.  Assigner un enclos à un soigneur"
					+ "\n9.  Vétérinaire : examiner et planifier un soin"
					+ "\n10. Voir / valider les soins d'un animal"
					+ "\n11. Voir le contenu d'un enclos"
					+ "\n12. Calculer le coût de nourriture mensuel"
					+ "\n13. Voir les alertes"
					+ "\n14. Afficher le zoo"
					+ "\n0.  Quitter");

			System.out.print("Entrer choix: ");
			choix = sc.nextInt();

			switch (choix) {
				case 0:
					System.out.println("Au revoir !");
					break;

				case 1: {
					System.out.print("Nom de l'enclos: ");
					String nom = sc.next();
					System.out.print("Capacité max: ");
					int capacite = sc.nextInt();
					System.out.print(
							"Type d'environnement (TERRESTRE, AQUATIQUE, AERIEN, SAVANE, FORET, POLAIRE, DESERTIQUE): ");
					String env = sc.next();
					zoo.ajouterEnclos(new Enclos(nom, capacite, env));
					System.out.println("Enclos ajouté.");
					break;
				}

				case 2: {
					System.out.print("1. Oiseau  2. Mammifere  3. Reptile : ");
					int type = sc.nextInt();
					if (type < 1 || type > 3) {
						System.out.println("Type invalide.");
						break;
					}
					System.out.print("Nom: ");
					String nom = sc.next();
					System.out.print("Age: ");
					int age = sc.nextInt();
					System.out.print("Poids (kg): ");
					double poids = sc.nextDouble();
					System.out.print("Regime (carnivore/herbivore/omnivore): ");
					String regime = sc.next();
					System.out.print("Statut de sante (ex: Bon): ");
					String sante = sc.next();

					Animal animal = null;
					if (type == 1) {
						System.out.print("Peut voler (true/false): ");
						boolean vole = sc.nextBoolean();
						System.out.print("Peut nager (true/false): ");
						boolean nage = sc.nextBoolean();
						animal = new Oiseau(nom, age, poids, regime, sante, vole, nage);
					} else if (type == 2) {
						animal = new Mammifere(nom, age, poids, regime, sante);
					} else {
						animal = new Reptile(nom, age, poids, regime, sante);
					}
					zoo.ajouterAnimal(animal);
					break;
				}

				case 3: {
					System.out.print("Nom de l'animal: ");
					ArrayList<Animal> trouves = zoo.rechercherAnimal(sc.next());
					System.out.print("Nom de l'enclos: ");
					Enclos enclos = zoo.rechercherEnclos(sc.next());

					if (trouves.isEmpty()) {
						System.out.println("Aucun animal trouvé.");
					} else if (enclos == null) {
						System.out.println("Aucun enclos trouvé.");
					} else {
						try {
							zoo.affecterAnimalEnclos(trouves.get(0), enclos);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
					break;
				}

				case 4: {
					System.out.print("Critère de recherche: ");
					ArrayList<Animal> resultat = zoo.rechercherAnimal(sc.next());
					System.out.println(resultat.size() + " résultat(s) :");
					for (Animal a : resultat) {
						System.out.println(" - " + a);
					}
					break;
				}

				case 5: {
					System.out.print("Nom de l'animal à modifier: ");
					ArrayList<Animal> trouves = zoo.rechercherAnimal(sc.next());
					if (trouves.isEmpty()) {
						System.out.println("Aucun animal trouvé.");
					} else {
						Animal a = trouves.get(0);
						System.out.print("Nouveau poids (kg): ");
						a.setPoids(sc.nextDouble());
						System.out.print("Nouveau statut de santé: ");
						a.setStatutSante(sc.next());
						zoo.modifierAnimal(a);
						System.out.println("Après modification : " + a);
					}
					break;
				}

				case 6: {
					System.out.print("Nom de l'animal: ");
					ArrayList<Animal> trouves = zoo.rechercherAnimal(sc.next());
					if (trouves.isEmpty()) {
						System.out.println("Aucun animal trouvé.");
					} else {
						Animal a = trouves.get(0);
						System.out.println("--- Test de : " + a);
						a.seNourrir();
						a.emettreSon();
						System.out.println("Ration : " + a.calculerRation() + " kg");
						if (a instanceof Volant) {
							((Volant) a).voler();
						}
						if (a instanceof Nageur) {
							((Nageur) a).nager();
						}
						a.etreSoigne();
						System.out.println("Statut de santé après soin : " + a.getStatutSante());
					}
					break;
				}

				case 7: {
					System.out.print("1. Soigneur  2. Veterinaire : ");
					int type = sc.nextInt();
					if (type != 1 && type != 2) {
						System.out.println("Type invalide.");
						break;
					}
					System.out.print("Nom: ");
					String nom = sc.next();
					System.out.print("Matricule: ");
					String matricule = sc.next();
					if (type == 1) {
						zoo.ajouterEmploye(new Soigneur(nom, matricule));
					} else {
						zoo.ajouterEmploye(new Veterinaire(nom, matricule));
					}
					zoo.afficherEmploye();
					break;
				}

				case 8: {
					System.out.print("Nom du soigneur: ");
					Employe emp = zoo.rechercherEmploye(sc.next());
					System.out.print("Nom de l'enclos: ");
					Enclos enclos = zoo.rechercherEnclos(sc.next());

					if (!(emp instanceof Soigneur)) {
						System.out.println("Soigneur introuvable.");
					} else if (enclos == null) {
						System.out.println("Aucun enclos trouvé.");
					} else {
						Soigneur s = (Soigneur) emp;
						s.assignerEnclos(enclos);
						System.out.println("Enclos gérés par " + s.getNom() + " :");
						for (Enclos e : s.getEnclosGeres()) {
							System.out.println(" - " + e.getNom());
						}
					}
					break;
				}

				case 9: {
					System.out.print("Nom du vétérinaire: ");
					Employe emp = zoo.rechercherEmploye(sc.next());
					System.out.print("Nom de l'animal: ");
					ArrayList<Animal> trouves = zoo.rechercherAnimal(sc.next());

					if (!(emp instanceof Veterinaire)) {
						System.out.println("Vétérinaire introuvable.");
					} else if (trouves.isEmpty()) {
						System.out.println("Aucun animal trouvé.");
					} else {
						Veterinaire v = (Veterinaire) emp;
						Animal a = trouves.get(0);
						v.examiner(a);
						System.out.print("Description du soin: ");
						sc.nextLine(); // termine la ligne précédente
						String desc = sc.nextLine();
						v.planifierSoin(a, desc);
					}
					break;
				}

				case 10: {
					System.out.print("Nom de l'animal: ");
					ArrayList<Animal> trouves = zoo.rechercherAnimal(sc.next());
					if (trouves.isEmpty()) {
						System.out.println("Aucun animal trouvé.");
					} else {
						Animal a = trouves.get(0);
						ArrayList<Soin> soins = new ArrayList<Soin>(a.getSoins());
						if (soins.isEmpty()) {
							System.out.println("Aucun soin pour cet animal.");
						} else {
							for (int i = 0; i < soins.size(); i++) {
								Soin s = soins.get(i);
								System.out.println((i + 1) + ". " + s.getDescription()
										+ " | prévu : " + s.getDatePrevue()
										+ " | effectué : " + s.isEffectue());
							}
							System.out.print("Numéro du soin à valider (0 pour aucun): ");
							int num = sc.nextInt();
							if (num >= 1 && num <= soins.size()) {
								soins.get(num - 1).validerSoin();
								System.out.println("Soin validé : " + soins.get(num - 1).isEffectue());
							}
						}
					}
					break;
				}

				case 11: {
					System.out.print("Nom de l'enclos: ");
					Enclos enclos = zoo.rechercherEnclos(sc.next());
					if (enclos == null) {
						System.out.println("Aucun enclos trouvé.");
					} else {
						System.out.println(enclos + " | plein : " + enclos.estPlein());
						for (Animal a : enclos.getAnimaux()) {
							System.out.println("   * " + a);
						}
					}
					break;
				}

				case 12: {
					System.out.print("Prix du kilo: ");
					double prix = sc.nextDouble();
					zoo.afficherCoutNourriture(prix);
					break;
				}

				case 13:
					zoo.afficherAlertes();
					break;

				case 14:
					zoo.afficherZoo();
					System.out.println("--- Enclos ---");
					zoo.afficherEnclos();
					System.out.println("--- Animaux ---");
					zoo.afficherAnimaux();
					System.out.println("--- Employés ---");
					zoo.afficherEmploye();
					break;

				default:
					System.out.println("Choix invalide !");
			}
		} while (choix != 0);

		sc.close();
	}
}