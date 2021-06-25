import java.io.Serializable;
import java.time.LocalDateTime;

public class Bed implements Serializable {
    private BedStatus bedStatus;
    private Patient patient;
    private LocalDateTime occupiedSince;

    public Bed(){
        bedStatus = BedStatus.EMPTY;
        patient = null;
    }

    public Bed(Patient p){
        this.patient = p;
        bedStatus = BedStatus.OCCUPIED;
        occupiedSince = LocalDateTime.now();
    }

    public BedStatus getBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(BedStatus bedStatus) {
        this.bedStatus = bedStatus;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        occupiedSince = LocalDateTime.now();
    }

    public LocalDateTime getOccupiedSince() {
        return occupiedSince;
    }

}
