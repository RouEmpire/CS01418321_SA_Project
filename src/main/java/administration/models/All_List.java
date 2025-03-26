package administration.models;

import java.util.ArrayList;

public class All_List {
    private ArrayList<Products> products;
    private  ArrayList<Ingredients>ingredients;
    private ArrayList<String> nameProduct;
    private ArrayList<Integer> idProduct;
    private ArrayList<Orders> orders;
    private ArrayList<Employee> employees;
    private ArrayList<Production> productions;
    private ArrayList<Plan> plans;

    public All_List() {
        products = new ArrayList<>();
        ingredients = new ArrayList<>();
        nameProduct = new ArrayList<>();
        idProduct = new ArrayList<>();
        orders = new ArrayList<>();
        employees = new ArrayList<>();
        productions = new ArrayList<>();
        plans = new ArrayList<>();



    }

    public void addProduct(Products product){
        products.add(product);
    }
    public void addIngredient(Ingredients ingredient){
        ingredients.add(ingredient);
    }
    public void addNameProduct(String name){
        nameProduct.add(name);
    }
    public void addIdProduct(int id){
        idProduct.add(id);
    }

    public void addOrders(Orders order){
        orders.add(order);
    }

    public void addEmpolyee(Employee employee){
        employees.add(employee);
    }

    public void addProduction(Production production){
        productions.add(production);
    }

    public void addPlan(Plan plan){
        plans.add(plan);
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public ArrayList<Integer> getIdProduct() {
        return idProduct;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    public ArrayList<Orders> getOrders() {
        return orders;
    }

    public ArrayList<Products> getProducts() {

        return products;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getNameProduct() {
        return nameProduct;
    }
}
