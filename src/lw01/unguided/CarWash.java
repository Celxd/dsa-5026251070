package lw01.unguided;

public class CarWash extends WashService {
    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        if (getDays() <= 3) {
            return getDays() * 35000;
        } else {
            return 3 * 3500 + (getDays() - 3) * 25000 + 15000;
        }
    }

    @Override
    public String label() {
        return "Car";
    }
}
