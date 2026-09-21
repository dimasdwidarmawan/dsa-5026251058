public class LaptopRental extends Rental {
    public LaptopRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateChange() {
        return getDays() * 40000 + 10000;
    }

    @Override
    public String label() {
        return "Laptop";
    }
}
