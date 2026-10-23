package com.zookeeper.model;

public class Soigneur {
    private String nom;
    private String matricule;
    private List<Enclos> enclosGeres; // Enclos dont le soigneur s'occupe
    private List<CSoin> soinsPlanifies;
    public Soigneur(String nom, String matricule) {
        this.nom = nom;
        this.matricule = matricule;
        this.enclosGeres = new ArrayList<>();
        this.soinsPlanifies = new ArrayList<>();
    }

    public CSoin planifierSoins(Soignable animal, String description, Date datePrevue) {
        CSoin nouveauSoin = new CSoin(datePrevue, description, animal);
        soinsPlanifies.add(nouveauSoin);
        System.out.println("Soigneur " + nom + " a planifié un soin : " + description);
        return nouveauSoin;
    }
    public void planifierRepas() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n--- GESTION DES REPAS : " + this.nom.toUpperCase() + " ---");
        System.out.println("Veuillez sélectionner le type d'animal concerné par le repas :");
        System.out.println("1. Mammifère");
        System.out.println("2. Oiseau");
        System.out.println("3. Reptile");
        System.out.print("Votre choix (1-3) : ");
        
        int choix = -1;
        if (scanner.hasNextInt()) {
            choix = scanner.nextInt();
        } else {
            scanner.next(); // Vider le buffer en cas d'entrée invalide
        }

        switch (choix) {
            case 1:
                planifierRepasMammifere();
                break;
            case 2:
                planifierRepasOiseau();
                break;
            case 3:
                planifierRepasReptile();
                break;
            default:
                System.out.println("Choix invalide. Retour au menu principal.");
        }
    }

    private void planifierRepasMammifere() {
        System.out.println("\n[Planification - Mammifères]");
        System.out.println("-> Distribution de rations riches en protéines et foin frais.");
        // Logique spécifique aux mammifères (ex: calcul de ration, enregistrement de l'horaire)
    }

    private void planifierRepasOiseau() {
        System.out.println("\n[Planification - Oiseaux]");
        System.out.println("-> Distribution de graines, vers et suppléments vitaminiques.");
        // Logique spécifique aux oiseaux
    }

    private void planifierRepasReptile() {
        System.out.println("\n[Planification - Reptiles]");
        System.out.println("-> Préparation de proies adaptées et vérification de la température des enclos.");
        // Logique spécifique aux reptiles
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}