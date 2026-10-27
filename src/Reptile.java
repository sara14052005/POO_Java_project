public class Reptile extends Animal implements Nageur {

	public Reptile(String nom, int age, double poids, String regime, String statutSante) {
		super(nom, age, poids, regime, statutSante);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nager() {
		System.out.println(nom + " nage.");

	}

	@Override
	public void seNourrir() {
		System.out.println(nom + " (reptile) avale " + calculerRation() + " kg.");

	}

	@Override
	public void emettreSon() {
		System.out.println(nom + " siffle : Sssss !");

	}

	@Override
	public double calculerRation() {
		// TODO Auto-generated method stub
		return Math.round(poids * 0.02 * 100.0) / 100.0;
	}

}