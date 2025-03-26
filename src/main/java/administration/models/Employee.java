package administration.models;
public class Employee {
    private String id;
    private String password;

    public Employee(String id, String password) {
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
        return "Employee{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
