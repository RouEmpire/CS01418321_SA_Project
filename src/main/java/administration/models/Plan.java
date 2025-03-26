package administration.models;

public class Plan {
    private int id;
    private String date;
    private String planProduct;
    private int amount;
    private int status;

    public Plan(int id, String date, String planProduct, int amount, int status) {
        this.id = id;
        this.date = date;
        this.planProduct = planProduct;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getPlanProduct() {
        return planProduct;
    }

    public int getAmount() {
        return amount;
    }

    public int getStatus() {
        return status;
    }
}
