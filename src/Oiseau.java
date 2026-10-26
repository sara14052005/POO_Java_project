public class Oiseau extends Animal implements Volant, Nageur {

    private boolean peutNager;
    private boolean peutVoler;

    public Oiseau(String nom, int age, double poids,
                  String regime, String statutSante) {

        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException(
                "Le nom de l'oiseau est obligatoire."
            );
        }

        if (age < 0) {
            throw new IllegalArgumentException(
                "L'âge ne peut pas être négatif."
            );
        }

        if (poids <= 0 ) {
            throw new IllegalArgumentException(
                "Le poids doit être un nombre positif."
            );
        }

        if (regime == null || regime.isBlank()) {
            throw new IllegalArgumentException(
                "Le régime alimentaire est obligatoire."
            );
        }

        this.nom = nom;
        this.age = age;
        this.poids = poids;
        this.regime = regime;
        this.statutSante = statutSante;

        this.peutNager = false;
        this.peutVoler = true;
    }


    public Oiseau(String nom, int age, double poids,
                  String regime, String statutSante,
                  boolean peutVoler, boolean peutNager) {

        this(nom, age, poids, regime, statutSante);
        this.peutVoler = peutVoler;
        this.peutNager = peutNager;
    }

    
    public double calculerHauteurVol() {
        if (peutVoler=false) {
            return 0.0;
        }

        return Math.min(1500.0, 1000.0 / this.poids);
    }

    @Override
    public void voler() {
        if (peutVoler=false) {
            System.out.println(
                nom + " ne peut pas voler."
            );
            return;
        }

        if (statutSante != null
                && statutSante.equalsIgnoreCase("Malade")) {
            System.out.println(
                nom + " ne peut pas voler : son état de santé "
                + "nécessite une vérification."
            );
            return;
        }

        System.out.println(
            nom + " vole à une hauteur estimée de "
            + String.format("%.2f", calculerHauteurVol())
            + " mètres."
        );
    }

    @Override
    public void nager() {
        if (peutNager=false) {
            System.out.println(
                nom + " n'est pas identifié comme un oiseau nageur."
            );
            return;
        }

        if (statutSante != null
                && statutSante.equalsIgnoreCase("Malade")) {
            System.out.println(
                nom + " ne doit pas nager avant une évaluation "
                + "de son état de santé."
            );
            return;
        }

        System.out.println(nom + " nage dans l'eau.");
    }

    @Override
    public void seNourrir() {
        System.out.println(
            nom + " se nourrit selon son régime : " + regime + "."
        );
    }

    @Override
    public void emettreSon() {
        System.out.println(
            nom + " émet ses vocalisations naturelles."
        );
    }

    @Override
    public double calculerRation() {
       
        double ration = poids * 0.07;

        if (regime.equalsIgnoreCase("Carnivore")) {
            ration *= 1.2;
        }

        return ration;
    }
}
