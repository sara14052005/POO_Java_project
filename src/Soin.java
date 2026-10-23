import java.util.Date;

public class Soin {
    private Date datePrevue;
    private String description;
    private boolean effectue;
    private Soignable cible;
    public Soin(Date datePrevue, String description) {
        this.datePrevue = datePrevue;
        this.description = description;
        this.effectue = false; // Par défaut, le soin n'est pas encore effectué
    }
  public void executerSoin() {
        if (!effectue && cible != null) {
            cible.etreSoigne(); // Déclenche la méthode de l'interface Soignable
            this.effectue = true;
            System.out.println("Le soin a été effectué avec succès.");
        }
    }
    // Getters et Setters
    public Date getDatePrevue() {
        return datePrevue;
    }

    public void setDatePrevue(Date datePrevue) {
        this.datePrevue = datePrevue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEffectue() {
        return effectue;
    }

    public void setEffectue(boolean effectue) {
        this.effectue = effectue;
    }
}