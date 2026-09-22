package lw01.unguided;

public abstract class WashService implements Billable {
    private String id;
    private int days;

    protected WashService(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days can't be negative or zero!");
        }

        this.id = id;
        this.days = days;
    }

    public String getId() {
        return this.id;
    }

    public int getDays() {
        return this.days;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Units can't be negative or zero!");
        }

        return units * calculateCharge();
    }

    public String label() {
        return "Service";
    }

    public String summary() {
        return this.id + " | " + label() + " | " + calculateCharge();
    }

    public String summary(int units) {
        return this.id + " | " + label() + " | " + (calculateCharge() * units);
    }

}
