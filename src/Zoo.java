import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Zoo {
	private String nom;
	private Set<Animal> animaux = new LinkedHashSet<Animal>(); // Set : pas de doublon
	private List<Enclos> enclos = new ArrayList<Enclos>();
	private Map<String, Employe> employes = new LinkedHashMap<String, Employe>(); // matricule -> employé

	public Zoo(String nom) {
		this.nom = nom;
	}

	// Getters
	public String getNom() {
		return nom;
	}

	public Set<Animal> getAnimaux() {
		return animaux;
	}

	public List<Enclos> getEnclos() {
		return enclos;
	}

	public Collection<Employe> getEmployes() {
		return employes.values();
	}

	// Setter
	public void setNom(String nom) {
		this.nom = nom;
	}

	// ======= Ajouter / modifier =======
	public void ajouterAnimal(Animal animal) {
		if (animal == null) {
			System.out.println("Animal invalide.");
		} else if (animaux.add(animal)) {
			System.out.println("Animal ajouté au zoo.");
		} else {
			System.out.println("Cet animal existe déjà (même nom et même type).");
		}
	}

	public void modifierAnimal(Animal animal) {
		if (animaux.remove(animal)) { // equals se base sur le nom
			animaux.add(animal);
		} else {
			System.out.println("Animal introuvable.");
		}
	}

	public void ajouterEnclos(Enclos e) {
		if (e == null) {
			System.out.println("Enclos invalide.");
		} else if (enclos.stream().anyMatch(x -> x.getNom().equalsIgnoreCase(e.getNom()))) {
			System.out.println("Un enclos avec ce nom existe déjà.");
		} else {
			enclos.add(e);
			System.out.println("Enclos ajouté.");
		}
	}

	public void ajouterEmploye(Employe e) {
		// putIfAbsent retourne null si le matricule n'existait pas encore
		if (e != null && employes.putIfAbsent(e.getMatricule().toUpperCase(), e) == null) {
			System.out.println("Employé ajouté.");
		} else {
			System.out.println("Employé invalide ou matricule déjà utilisé.");
		}
	}

	// Map : accès direct à un employé grâce à son matricule
	public Employe trouverEmploye(String matricule) {
		return employes.get(matricule.toUpperCase());
	}

	// <====== Stream : recherches ======>
	public List<Animal> rechercherAnimal(Collection<Animal> liste, String critere) {
		return liste.stream()
				.filter(a -> a.toString().toLowerCase().contains(critere.toLowerCase()))
				.toList();
	}

	public List<Enclos> rechercherEnclos(List<Enclos> liste, String nom) {
		return liste.stream()
				.filter(e -> e.getNom().toLowerCase().contains(nom.toLowerCase()))
				.toList();
	}

	public List<Employe> rechercherEmploye(Collection<Employe> liste, String nom) {
		return liste.stream()
				.filter(e -> e.getNom().toLowerCase().contains(nom.toLowerCase()))
				.toList();
	}

	// <====== Interfaces fonctionnelles prédéfinies ======>

	// Predicate : garde les animaux qui respectent le critère
	public List<Animal> rechercherParCritere(Predicate<Animal> critere) {
		return animaux.stream().filter(critere).toList();
	}

	// Consumer : fait une action sur chaque animal
	public void pourChaqueAnimal(Consumer<Animal> action) {
		animaux.forEach(action);
	}

	// Function : transforme chaque animal en autre chose (nom, ration...)
	public <R> List<R> extraire(Function<Animal, R> transformation) {
		return animaux.stream().map(transformation).toList();
	}

	// Comparator : trie les animaux
	public List<Animal> trierAnimaux(Comparator<Animal> ordre) {
		return animaux.stream().sorted(ordre).toList();
	}

	// <====== Map + Stream ======>

	// Function + Map : regroupe les animaux selon une clé (type, régime...)
	public <K> Map<K, List<Animal>> grouperAnimaux(Function<Animal, K> cle) {
		return animaux.stream()
				.collect(Collectors.groupingBy(cle, LinkedHashMap::new, Collectors.toList()));
	}

	// Map : type d'animal -> ration journalière totale
	public Map<String, Double> rationParType() {
		return animaux.stream()
				.collect(Collectors.groupingBy(
						a -> a.getClass().getSimpleName(),
						TreeMap::new,
						Collectors.summingDouble(Animal::calculerRation)));
	}

	// Set : régimes présents dans le zoo, sans doublon et triés
	public Set<String> regimesPresents() {
		return animaux.stream()
				.map(a -> a.getRegime().toLowerCase())
				.collect(Collectors.toCollection(TreeSet::new));
	}

	// EnumMap : environnement -> enclos
	public Map<TypeEnvironnement, List<Enclos>> enclosParEnvironnement() {
		return enclos.stream()
				.collect(Collectors.groupingBy(
						Enclos::getTypeEnvironnement,
						() -> new EnumMap<TypeEnvironnement, List<Enclos>>(TypeEnvironnement.class),
						Collectors.toList()));
	}

	// ======= Affectation, alertes, coût =======
	public void affecterAnimalEnclos(Animal animal, Enclos e) throws Exception {
		Enclos ancien = enclos.stream()
				.filter(autre -> autre.getAnimaux().contains(animal))
				.findFirst()
				.orElse(null);

		boolean ok = e.ajouterAnimal(animal);
		if (ok) {
			if (ancien != null) {
				ancien.retirerAnimal(animal);
			}
			System.out.println("Animal affecté à l'enclos " + e.getNom() + ".");
		} else {
			throw new Exception("Impossible : enclos plein, animal incompatible ou déjà dedans.");
		}
	}

	public List<String> genererAlertes() {
		List<String> alertes = new ArrayList<String>();

		enclos.stream()
				.filter(Enclos::estPlein)
				.forEach(e -> alertes.add("Enclos plein : " + e.getNom()));

		animaux.stream()
				.filter(a -> enclos.stream().noneMatch(e -> e.getAnimaux().contains(a)))
				.forEach(a -> alertes.add("Animal sans enclos : " + a.getNom()));

		animaux.stream()
				.filter(a -> a.getStatutSante().equalsIgnoreCase("Malade"))
				.forEach(a -> alertes.add("Animal malade : " + a.getNom()));

		return alertes;
	}

	public double calculerCoutNourritureMensuel(double prixKilo) {
		return animaux.stream().mapToDouble(Animal::calculerRation).sum() * 30 * prixKilo;
	}

	// ======= Afficher =======
	public void afficherAnimaux() {
		int i = 1;
		for (Animal a : animaux) {
			System.out.println(i + ". " + a);
			i++;
		}
	}

	public void afficherEnclos() {
		for (int i = 0; i < enclos.size(); i++) {
			System.out.println((i + 1) + ". " + enclos.get(i));
		}
	}

	public void afficherEmploye() {
		int i = 1;
		for (Employe e : employes.values()) {
			System.out.println(i + ". " + e.getClass().getSimpleName() + " " + e.getNom()
					+ " (" + e.getMatricule() + ")");
			i++;
		}
	}

	public void afficherAlertes() {
		List<String> alertes = genererAlertes();
		if (alertes.isEmpty()) {
			System.out.println("Aucune alerte.");
		}
		alertes.forEach(message -> System.out.println("! " + message));
	}

	public void afficherCoutNourriture(double prixKilo) {
		System.out.println("Coût mensuel de nourriture : " + calculerCoutNourritureMensuel(prixKilo));
	}

	public void afficherStatistiques() {
		System.out.println("--- Nombre d'animaux par type ---");
		grouperAnimaux(a -> a.getClass().getSimpleName())
				.forEach((type, liste) -> System.out.println(type + " : " + liste.size()));

		System.out.println("--- Nombre d'animaux par régime ---");
		grouperAnimaux(a -> a.getRegime().toLowerCase())
				.forEach((regime, liste) -> System.out.println(regime + " : " + liste.size()));

		System.out.println("--- Ration journalière totale par type (kg) ---");
		for (Map.Entry<String, Double> entree : rationParType().entrySet()) {
			System.out.println(entree.getKey() + " : " + String.format("%.2f", entree.getValue()));
		}

		System.out.println("--- Régimes présents (Set) ---");
		System.out.println(regimesPresents());

		System.out.println("--- Noms des animaux ---");
		System.out.println(extraire(Animal::getNom));
	}

	public void afficherEnclosParEnvironnement() {
		enclosParEnvironnement().forEach((env, liste) -> {
			System.out.println(env + " :");
			liste.forEach(e -> System.out.println("   - " + e.getNom()));
		});
	}

	@Override
	public String toString() {
		return "=== " + this.nom + " ===" + "\n Animaux : " + animaux.size() + " | Enclos : " + enclos.size()
				+ " | Employés : " + employes.size();
	}

	// Données de test
	public void chargerDonneesTest() {
		// Enclos
		Enclos voliere = new Enclos("Voliere", 2, TypeEnvironnement.AERIEN);
		Enclos aviaire = new Enclos("Aviaire", 3, TypeEnvironnement.AERIEN);
		Enclos savane = new Enclos("Savane", 2, TypeEnvironnement.SAVANE);
		Enclos bassin = new Enclos("Bassin", 2, TypeEnvironnement.AQUATIQUE);
		enclos.add(voliere);
		enclos.add(aviaire);
		enclos.add(savane);
		enclos.add(bassin);

		// Animaux (nom, age, poids, regime, statutSante)
		Animal coco = new Oiseau("Coco", 3, 1.5, "Omnivore", "Bon", true, false);
		Animal kiwi = new Oiseau("Kiwi", 2, 1.2, "Herbivore", "Bon", true, false);
		Animal pipo = new Oiseau("Pipo", 4, 2.0, "Carnivore", "Malade", true, true);
		Animal leo = new Mammifere("Leo", 5, 190, "Carnivore", "Bon");
		Animal zara = new Mammifere("Zara", 6, 300, "Herbivore", "Bon");
		Animal sssam = new Reptile("Sssam", 7, 12, "Carnivore", "Bon");
		animaux.add(coco);
		animaux.add(kiwi);
		animaux.add(pipo);
		animaux.add(leo);
		animaux.add(zara);
		animaux.add(sssam);

		// Affectations (pipo et zara restent sans enclos pour tester les alertes)
		try {
			affecterAnimalEnclos(coco, voliere);
			affecterAnimalEnclos(kiwi, voliere);
			affecterAnimalEnclos(leo, savane);
			affecterAnimalEnclos(sssam, bassin);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Employés (clé de la Map = matricule)
		Soigneur ali = new Soigneur("Ali", "M001");
		Veterinaire sara = new Veterinaire("Sara", "M002");
		employes.put("M001", ali);
		employes.put("M002", sara);
		ali.assignerEnclos(voliere);

		// Un soin déjà planifié pour Coco
		sara.planifierSoin(coco, "Vaccin");
	}
}