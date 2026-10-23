/**
 * 
 */
public class Enclos {
	private String nom;
	private int capaciteMax;
	private String typeEnvironnement;
	
	public Enclos(String nom, int capaciteMax, String typeEnvironnement) {
		super();
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

	@Override
	public String toString() {
		return "Enclos [nom=" + nom + ", capaciteMax=" + capaciteMax + ", typeEnvironnement=" + typeEnvironnement + "]";
	}
	
	/*
	verifierCompatibilite (animal: Animal): boolean 
	estPlein() boolean
	ajouterAnimal (animal: Animal) 
	retirerAnimal(animal: Animal)
	*/
	public boolean verifierCompatibilite(Animal animal) {
		return false;
		
	}
	
	public boolean estPlein() {
		if (capaciteMax == 0) {
			return false;
		} else {
			return true;
		}
		
		
	}
	
	public void ajouterAnimal(Animal animal) {
		
	}
	
	public void retirerAnimal(Animal animal) {
		
	}
}