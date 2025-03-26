package administration.models;

public class Production {
    private String id;
    private String password;

    public Production(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Production{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
