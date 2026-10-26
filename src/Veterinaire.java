import java.util.Date;

public class Veterinaire extends Employe {

    public Veterinaire(String nom, String matricule) {
        super(nom, matricule);
    }

    public void examiner(Animal animal) {
        System.out.println("Le vétérinaire " + nom + " examine l'animal : " + animal.getNom());
    }

    public void planifierSoin(Animal animal, String desc) {
        Soin nouveauSoin = new Soin(new Date(), desc);
        animal.ajouterSoin(nouveauSoin);
        System.out.println("Soin planifié pour " + animal.getNom() + " par le Dr. " + nom);
    }
}