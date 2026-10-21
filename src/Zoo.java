import java.util.List;

public class Zoo {
	private  String nom;

	public Zoo(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Zoo [nom=" + nom + "]";
	}
	
	public void ajouterAnimal(Animal animal) {
		
	}
	
	public void modifierAnimal(Animal animal) {
		
	}
	public void rechercherAnimal(Animal animal) {
		
	}
	
	public void affecterAnimalEnclos(Animal animal,Enclos enclos) {
		
	}
	
	public List<String> genererAlertes() {
		return null;
		
	}
}
