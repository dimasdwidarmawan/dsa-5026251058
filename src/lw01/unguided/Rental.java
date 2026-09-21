public abstract class Rental {
    private String Id;
    private int days;

    protected Rental(String Id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Jumlah hari harus lebih dari nol.");
        }
        this.Id = Id;
        this.days = days;
    }

    public String getId() {
        return Id;
    }

    public int getDays() {
        return days;
    }

    public abstract int calculateChange();

    public int calculateChange(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Jumlah unit harus lebih dari nol.");
        }
        return units * calculateChange();
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
        return Id + " | " + label() + " | " + calculateChange();
    }

}
