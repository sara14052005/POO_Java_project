public class Mammifere extends Animal implements Nageur, Volant {

	public Mammifere(String nom, int age, double poids, String regime, String statutSante) {
		super(nom, age, poids, regime, statutSante);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void voler() {
		System.out.println(nom + " vole.");

	}

	@Override
	public void nager() {
		System.out.println(nom + " nage.");

	}

	@Override
	public void seNourrir() {
		System.out.println(nom + " (mammifère) mange " + calculerRation() + " kg.");
	}

	@Override
	public void emettreSon() {
		System.out.println(nom + " rugit / grogne !");

	}

	@Override
	public double calculerRation() {
		double taux = 0.05; // 5 % du poids
		if ("HERBIVORE".equalsIgnoreCase(regime)) {
			taux += 0.02; // 7 % si herbivore
		}
		return Math.round(poids * taux * 100.0) / 100.0; // arrondi à 2 décimales
	}

}