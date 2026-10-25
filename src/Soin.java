import java.util.Date;

public class Soin {
    private Date datePrevue;
    private String description;
    private boolean effectue;

    public Soin(Date datePrevue, String description) {
        this.datePrevue = datePrevue;
        this.description = description;
        this.effectue = false;
    }

    public void validerSoin() {
        this.effectue = true;
    }

    public boolean isEffectue() {
        return effectue;
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
}