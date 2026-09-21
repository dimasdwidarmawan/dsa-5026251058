
public class ProjectorRental extends Rental {
    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateChange() {
        int cost = 20000;
        int day = getDays();

        if (day <= 3) {
            cost += day * 60000;
        } else {
            cost += (3 * 60000) + ((day - 3) * 45000);
        }
        return cost;
    }

    public String label() {
        return "Projector";
    }

}
