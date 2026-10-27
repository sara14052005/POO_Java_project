import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public abstract class Animal implements Soignable {
	protected String nom;
	protected int age;
	protected double poids;
	protected String regime;
	protected String statutSante;
	private List<Soin> soins = new ArrayList<>();

	public Animal(String nom, int age, double poids, String regime, String statutSante) {
		super();
		this.nom = nom;
		this.age = age;
		this.poids = poids;
		this.regime = regime;
		this.statutSante = statutSante;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public String getStatutSante() {
		return statutSante;
	}

	public void setStatutSante(String statutSante) {
		this.statutSante = statutSante;
	}

	@Override
	public void etreSoigne() {
		System.out.println(nom + " est en train d'être soigné.");
		this.statutSante = "EN_TRAITEMENT";

	}

	public abstract void seNourrir();

	public abstract void emettreSon();

	public abstract double calculerRation();

	public void ajouterSoin(Soin soin) {
		if (soin != null)
			soins.add(soin);
	}
	public List<Soin> getSoins() {
	    return soins;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [nom=" + nom + ", age=" + age + ", poids=" + poids + ", regime=" + regime + ", statutSante="
				+ statutSante + "]";
	}

}
