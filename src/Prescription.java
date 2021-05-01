public class Prescription {
    //kullanım süresi ekle
    String medicine;
    int times;
    int measure;

    public Prescription(String medicine, int times, int measure) throws InvalidPrescriptionException {
        if(times < 1 || measure < 1){
            throw new InvalidPrescriptionException();
        }
        this.medicine = medicine;
        this.times = times;
        this.measure = measure;
    }

    public Prescription(Prescription other){
        medicine = other.medicine;
        times = other.times;
        measure = other.measure;
    }

    public String getMedicine() {
        return medicine;
    }

    public int getTimes() {
        return times;
    }

    public int getMeasure() {
        return measure;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Prescription: medicine = ").append(medicine).append(", usage = ").append(measure);

        if(measure == 1){
            s.append(" measure ");
        }
        else{
            s.append(" measures ");
        }

        if(times == 1){
            s.append(times).append(" time a day");
        }
        else{
            s.append(times).append(" times a day");
        }

        return s.toString();
    }
}
