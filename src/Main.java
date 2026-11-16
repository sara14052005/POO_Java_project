import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("<============== ZooKeeper ==============>");
		System.out.print("Nom du zoo: ");
		Zoo zoo = new Zoo(sc.next());
		zoo.chargerDonneesTest();
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
					+ "\n--- Concepts avancés ---"
					+ "\n15. Recherche avancée (Predicate)"
					+ "\n16. Action sur tous les animaux (Consumer)"
					+ "\n17. Statistiques (Map, Set, Function, Stream)"
					+ "\n18. Enclos par environnement (EnumMap)"
					+ "\n19. Trier les animaux (Comparator)"
					+ "\n20. Changer la règle de compatibilité d'un enclos (BiPredicate)"
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
					System.out.print("Type d'environnement " + Arrays.toString(TypeEnvironnement.values()) + ": ");
					TypeEnvironnement env = TypeEnvironnement.depuisTexte(sc.next());
					if (env == null) {
						System.out.println("Type d'environnement invalide.");
					} else {
						zoo.ajouterEnclos(new Enclos(nom, capacite, env));
					}
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
					List<Animal> trouves = zoo.rechercherAnimal(zoo.getAnimaux(), sc.next());
					System.out.print("Nom de l'enclos: ");
					List<Enclos> enclosTrouves = zoo.rechercherEnclos(zoo.getEnclos(), sc.next());

					if (trouves.isEmpty()) {
						System.out.println("Aucun animal trouvé.");
					} else if (enclosTrouves.isEmpty()) {
						System.out.println("Aucun enclos trouvé.");
					} else {
						try {
							zoo.affecterAnimalEnclos(trouves.get(0), enclosTrouves.get(0));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
					break;
				}

				case 4: {
					System.out.print("Critère de recherche: ");
					List<Animal> resultat = zoo.rechercherAnimal(zoo.getAnimaux(), sc.next());
					System.out.println(resultat.size() + " résultat(s) :");
					resultat.forEach(System.out::println);
					break;
				}

				case 5: {
					System.out.print("Nom de l'animal à modifier: ");
					List<Animal> trouves = zoo.rechercherAnimal(zoo.getAnimaux(), sc.next());
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
					List<Animal> trouves = zoo.rechercherAnimal(zoo.getAnimaux(), sc.next());
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
					System.out.print("Matricule (unique): ");
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
					System.out.print("Matricule du soigneur (ex: M001): ");
					Employe emp = zoo.trouverEmploye(sc.next());
					System.out.print("Nom de l'enclos: ");
					List<Enclos> enclosTrouves = zoo.rechercherEnclos(zoo.getEnclos(), sc.next());

					if (!(emp instanceof Soigneur)) {
						System.out.println("Soigneur introuvable.");
					} else if (enclosTrouves.isEmpty()) {
						System.out.println("Aucun enclos trouvé.");
					} else {
						Soigneur s = (Soigneur) emp;
						s.assignerEnclos(enclosTrouves.get(0));
						System.out.println("Enclos gérés par " + s.getNom() + " :");
						s.getEnclosGeres().forEach(en -> System.out.println(" - " + en.getNom()));
					}
					break;
				}

				case 9: {
					System.out.print("Matricule du vétérinaire (ex: M002): ");
					Employe emp = zoo.trouverEmploye(sc.next());
					System.out.print("Nom de l'animal: ");
					List<Animal> trouves = zoo.rechercherAnimal(zoo.getAnimaux(), sc.next());

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
					List<Animal> trouves = zoo.rechercherAnimal(zoo.getAnimaux(), sc.next());
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
					List<Enclos> resultat = zoo.rechercherEnclos(zoo.getEnclos(), sc.next());

					if (resultat.isEmpty()) {
						System.out.println("Aucun enclos trouvé.");
					} else {
						resultat.forEach(c -> {
							System.out.println(c + " | plein : " + c.estPlein());
							c.getAnimaux().forEach(a -> System.out.println("   * " + a));
						});
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
					System.out.println(zoo);
					System.out.println("--- Enclos ---");
					zoo.afficherEnclos();
					System.out.println("--- Animaux ---");
					zoo.afficherAnimaux();
					System.out.println("--- Employés ---");
					zoo.afficherEmploye();
					break;

				// ===== Predicate =====
				case 15: {
					System.out.println("1. Animaux de plus de 50 kg"
							+ "\n2. Animaux malades"
							+ "\n3. Animaux carnivores"
							+ "\n4. Carnivores de plus de 50 kg (Predicate.and)");
					System.out.print("Votre choix: ");
					int option = sc.nextInt();

					Predicate<Animal> lourd = a -> a.getPoids() > 50;
					Predicate<Animal> carnivore = a -> a.getRegime().equalsIgnoreCase("carnivore");

					List<Animal> resultat = new ArrayList<Animal>();
					if (option == 1) {
						resultat = zoo.rechercherParCritere(lourd);
					} else if (option == 2) {
						resultat = zoo.rechercherParCritere(a -> a.getStatutSante().equalsIgnoreCase("Malade"));
					} else if (option == 3) {
						resultat = zoo.rechercherParCritere(carnivore);
					} else if (option == 4) {
						resultat = zoo.rechercherParCritere(lourd.and(carnivore));
					}
					System.out.println(resultat.size() + " résultat(s) :");
					resultat.forEach(System.out::println);
					break;
				}

				// ===== Consumer =====
				case 16: {
					System.out.println("1. Nourrir tous les animaux"
							+ "\n2. Tous les animaux émettent un son"
							+ "\n3. Soigner les animaux malades");
					System.out.print("Votre choix: ");
					int option = sc.nextInt();

					if (option == 1) {
						zoo.pourChaqueAnimal(Animal::seNourrir);
					} else if (option == 2) {
						zoo.pourChaqueAnimal(Animal::emettreSon);
					} else if (option == 3) {
						zoo.pourChaqueAnimal(a -> {
							if (a.getStatutSante().equalsIgnoreCase("Malade")) {
								a.etreSoigne();
							}
						});
					} else {
						System.out.println("Choix invalide.");
					}
					break;
				}

				// ===== Map, Set, Function, Stream =====
				case 17:
					zoo.afficherStatistiques();
					break;

				// ===== EnumMap =====
				case 18:
					zoo.afficherEnclosParEnvironnement();
					break;

				// ===== Comparator =====
				case 19: {
					System.out.println("1. Par nom  2. Par poids  3. Par âge  4. Par poids décroissant");
					System.out.print("Votre choix: ");
					int option = sc.nextInt();

					List<Animal> tries = new ArrayList<Animal>();
					if (option == 1) {
						tries = zoo.trierAnimaux(Comparator.comparing(Animal::getNom));
					} else if (option == 2) {
						tries = zoo.trierAnimaux(Comparator.comparingDouble(Animal::getPoids));
					} else if (option == 3) {
						tries = zoo.trierAnimaux(Comparator.comparingInt(Animal::getAge));
					} else if (option == 4) {
						tries = zoo.trierAnimaux(Comparator.comparingDouble(Animal::getPoids).reversed());
					}
					tries.forEach(System.out::println);
					break;
				}

				// ===== BiPredicate =====
				case 20: {
					System.out.print("Nom de l'enclos: ");
					List<Enclos> resultat = zoo.rechercherEnclos(zoo.getEnclos(), sc.next());
					if (resultat.isEmpty()) {
						System.out.println("Aucun enclos trouvé.");
						break;
					}
					Enclos cible = resultat.get(0);
					System.out.println("1. Même classe (Oiseau avec Oiseau)"
							+ "\n2. Même régime (carnivore avec carnivore)"
							+ "\n3. Aucune restriction");
					System.out.print("Votre choix: ");
					int option = sc.nextInt();

					if (option == 1) {
						cible.setRegle((premier, nouveau) -> premier.getClass() == nouveau.getClass());
					} else if (option == 2) {
						cible.setRegle((premier, nouveau) -> premier.getRegime().equalsIgnoreCase(nouveau.getRegime()));
					} else if (option == 3) {
						cible.setRegle((premier, nouveau) -> true);
					} else {
						System.out.println("Choix invalide.");
						break;
					}
					System.out.println("Règle changée pour l'enclos " + cible.getNom() + ".");
					break;
				}

				default:
					System.out.println("Choix invalide !");
			}
		} while (choix != 0);

		sc.close();
	}
}