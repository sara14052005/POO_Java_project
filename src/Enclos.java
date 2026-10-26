import java.util.ArrayList;

public class Enclos {
	private String nom;
	private int capaciteMax;
	private String typeEnvironnement;
	private ArrayList<Animal> animaux = new ArrayList<Animal>();

	public Enclos(String nom, int capaciteMax, String typeEnvironnement) {
		this.nom = nom;
		this.capaciteMax = capaciteMax;
		this.typeEnvironnement = typeEnvironnement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapaciteMax() {
		return capaciteMax;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}

	public String getTypeEnvironnement() {
		return typeEnvironnement;
	}

	public void setTypeEnvironnement(String typeEnvironnement) {
		this.typeEnvironnement = typeEnvironnement;
	}

	public ArrayList<Animal> getAnimaux() {
		return animaux;
	}

	
	public boolean verifierCompatibilite(Animal animal) {
		if (animaux.isEmpty()) {
			return true;
		}
		return animaux.get(0).getClass() == animal.getClass();
	}

	
	public boolean estPlein() {
		return animaux.size() >= capaciteMax;
	}

	
	public boolean ajouterAnimal(Animal animal) {
		if (animal == null || animaux.contains(animal) || estPlein() || !verifierCompatibilite(animal)) {
			return false;
		}
		animaux.add(animal);
		return true;
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