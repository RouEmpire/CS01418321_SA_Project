package administration.models;

public class Orders {
    private int id;
    private String cusName;
    private String cusNumber;
    private String proName;
    private int amount;
    private int orderStatus;

    public Orders(int id, String cusName, String cusNumber, String proName, int amount, int orderStatus) {
        this.id = id;
        this.cusName = cusName;
        this.cusNumber = cusNumber;
        this.proName = proName;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public String getCusName() {
        return cusName;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public String getProName() {
        return proName;
    }

    public int getAmount() {
        return amount;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusNumber='" + cusNumber + '\'' +
                ", proName='" + proName + '\'' +
                ", amount=" + amount +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
