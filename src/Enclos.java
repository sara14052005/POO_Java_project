import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiPredicate;

public class Enclos {
	private String nom;
	private int capaciteMax;
	private TypeEnvironnement typeEnvironnement;

	// Set : un animal ne peut pas être deux fois dans l'enclos (ordre d'arrivée
	// conservé)
	private Set<Animal> animaux = new LinkedHashSet<Animal>();

	// BiPredicate : règle de compatibilité (premier animal présent, nouvel animal)
	// Par défaut : même classe (Oiseau avec Oiseau, etc.)
	private BiPredicate<Animal, Animal> regle = (premier, nouveau) -> premier.getClass() == nouveau.getClass();

	// Constructor
	public Enclos(String nom, int capaciteMax, TypeEnvironnement typeEnvironnement) {
		this.nom = nom;
		this.capaciteMax = capaciteMax;
		this.typeEnvironnement = typeEnvironnement;
	}

	// Getters
	public String getNom() {
		return nom;
	}

	public int getCapaciteMax() {
		return capaciteMax;
	}

	public TypeEnvironnement getTypeEnvironnement() {
		return typeEnvironnement;
	}

	public Set<Animal> getAnimaux() {
		return animaux;
	}

	// Setters
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}

	public void setTypeEnvironnement(TypeEnvironnement typeEnvironnement) {
		this.typeEnvironnement = typeEnvironnement;
	}

	public void setRegle(BiPredicate<Animal, Animal> regle) {
		this.regle = regle;
	}

	public boolean verifierCompatibilite(Animal animal) {
		if (animaux.isEmpty()) {
			return true;
		}
		Animal premier = animaux.iterator().next();
		return regle.test(premier, animal);
	}

	public boolean estPlein() {
		return animaux.size() >= capaciteMax;
	}

	public boolean ajouterAnimal(Animal animal) {
		if (animal == null || estPlein() || !verifierCompatibilite(animal)) {
			return false;
		}
		return animaux.add(animal); // false si l'animal est déjà dans le Set
	}

	public void retirerAnimal(Animal animal) {
		animaux.remove(animal);
	}

	@Override
	public String toString() {
		return "Enclos [nom=" + nom + ", capaciteMax=" + capaciteMax + ", typeEnvironnement=" + typeEnvironnement
				+ ", animaux=" + animaux.size() + "]";
	}
}