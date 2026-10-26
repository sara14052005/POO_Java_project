import java.util.ArrayList;

public class Zoo {
	private String nom;
	private ArrayList<Animal> animaux = new ArrayList<Animal>();
	private ArrayList<Enclos> enclos = new ArrayList<Enclos>();
	private ArrayList<Employe> employes = new ArrayList<Employe>();

	public Zoo(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void ajouterEnclos(Enclos e) {
		enclos.add(e);
	}

	public void ajouterEmploye(Employe e) {
		employes.add(e);
	}

	public void ajouterAnimal(Animal animal) {
		if (animal == null) {
			System.out.println("Animal invalide.");
		} else {
			animaux.add(animal);
			System.out.println("Animal ajouté au zoo.");
		}
	}

	public void modifierAnimal(Animal animal) {
		int position = animaux.indexOf(animal);
		if (position == -1) {
			System.out.println("Animal introuvable.");
		} else {
			animaux.set(position, animal);
		}
	}

	// Cherche le texte dans la description (toString) de chaque animal
	public ArrayList<Animal> rechercherAnimal(String critere) {
		ArrayList<Animal> resultat = new ArrayList<Animal>();
		for (Animal a : animaux) {
			if (a.toString().toLowerCase().contains(critere.toLowerCase())) {
				resultat.add(a);
			}
		}
		return resultat;
	}

	public void affecterAnimalEnclos(Animal animal, Enclos e) {
		// On cherche l'ancien enclos de l'animal (s'il en a un)
		Enclos ancien = null;
		for (Enclos autre : enclos) {
			if (autre.getAnimaux().contains(animal)) {
				ancien = autre;
			}
		}

		boolean ok = e.ajouterAnimal(animal);
		if (ok) {
			if (ancien != null) {
				ancien.retirerAnimal(animal);
			}
			System.out.println("Animal affecté à l'enclos " + e.getNom() + ".");
		} else {
			System.out.println("Impossible : enclos plein, animal incompatible ou déjà dedans.");
		}
	}

	// Même chose avec les numéros affichés dans le menu (1, 2, 3...)
	public void affecterParNumero(int numAnimal, int numEnclos) {
		if (numAnimal < 1 || numAnimal > animaux.size() || numEnclos < 1 || numEnclos > enclos.size()) {
			System.out.println("Numéro invalide.");
		} else {
			affecterAnimalEnclos(animaux.get(numAnimal - 1), enclos.get(numEnclos - 1));
		}
	}

	public ArrayList<String> genererAlertes() {
		ArrayList<String> alertes = new ArrayList<String>();

		for (Enclos e : enclos) {
			if (e.estPlein()) {
				alertes.add("Enclos plein : " + e.getNom());
			}
		}

		for (Animal a : animaux) {
			boolean trouve = false;
			for (Enclos e : enclos) {
				if (e.getAnimaux().contains(a)) {
					trouve = true;
				}
			}
			if (!trouve) {
				alertes.add("Animal sans enclos : " + a);
			}
		}
		return alertes;
	}

	// Ration (en kg) de tous les animaux x 30 jours x prix du kilo
	public double calculerCoutNourritureMensuel(double prixKilo) {
		double total = 0;
		for (Animal a : animaux) {
			total = total + a.calculerRation();
		}
		return total * 30 * prixKilo;
	}

	// ---------- Affichages (appelés par le Main) ----------

	public void afficherAnimaux() {
		for (int i = 0; i < animaux.size(); i++) {
			System.out.println((i + 1) + ". " + animaux.get(i));
		}
	}

	public void afficherEnclos() {
		for (int i = 0; i < enclos.size(); i++) {
			System.out.println((i + 1) + ". " + enclos.get(i));
		}
	}

	public void afficherRecherche(String critere) {
		ArrayList<Animal> resultat = rechercherAnimal(critere);
		System.out.println(resultat.size() + " résultat(s) :");
		for (Animal a : resultat) {
			System.out.println(" - " + a);
		}
	}

	public void afficherAlertes() {
		ArrayList<String> alertes = genererAlertes();
		if (alertes.isEmpty()) {
			System.out.println("Aucune alerte.");
		}
		for (String message : alertes) {
			System.out.println("! " + message);
		}
	}

	public void afficherCoutNourriture(double prixKilo) {
		System.out.println("Coût mensuel de nourriture : " + calculerCoutNourritureMensuel(prixKilo));
	}

	public void afficherZoo() {
		System.out.println(this);
		System.out.println("--- Enclos ---");
		for (Enclos e : enclos) {
			System.out.println(e);
			for (Animal a : e.getAnimaux()) {
				System.out.println("     * " + a);
			}
		}
		System.out.println("Animaux : " + animaux.size() + " | Employés : " + employes.size());
	}

	@Override
	public String toString() {
		return "Zoo [nom=" + nom + "]";
	}
}