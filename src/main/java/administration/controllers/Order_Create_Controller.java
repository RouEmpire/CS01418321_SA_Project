package administration.controllers;

import administration.models.All_List;
import administration.models.Orders;
import administration.models.Products;
import administration.services.Datasource;
import administration.services.LoginDataSource;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Order_Create_Controller {
    @FXML
    private ComboBox<String> products1, products2, products3, products4, products5;
    @FXML
    private TextField Cname, Cnumber, id, amount1, amount2, amount3, amount4, amount5,totalText;
    @FXML
    private PasswordField password;
    @FXML
    private Label error,complete,loginError,error2;
    private int a1,a2,a3,a4,a5;
    All_List all_list = new All_List();
    All_List log1 = new All_List();
    All_List log2 = new All_List();
    Datasource datasource;



    public void initialize() {

        datasource = new LoginDataSource("data", "LoginEmployee.csv");
        log1= datasource.getFileData1();

        datasource = new LoginDataSource("data", "LoginProduction.csv");
        log2= datasource.getFileData2();






        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean", "root", "9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from products");
            all_list.addNameProduct("-");
            while (resultSet.next()) {

                int v1 = resultSet.getInt("product_id");
                String v2 = resultSet.getString("product_name");
                int v3 = resultSet.getInt("product_amount");
                int v4=resultSet.getInt("product_price");
                Products products = new Products(v1,v2,v3,v4);
                all_list.addProduct(products);
                all_list.addNameProduct(v2);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        comboBox1();
        products1.getSelectionModel().selectFirst();
        products2.getSelectionModel().selectFirst();
        products3.getSelectionModel().selectFirst();
        products4.getSelectionModel().selectFirst();
        products5.getSelectionModel().selectFirst();
        amount1.setDisable(true);
        amount2.setDisable(true);
        amount3.setDisable(true);
        amount4.setDisable(true);
        amount5.setDisable(true);
        totalText.setDisable(true);
        amount1.setStyle("-fx-opacity: 1.0;");
        amount2.setStyle("-fx-opacity: 1.0;");
        amount3.setStyle("-fx-opacity: 1.0;");
        amount4.setStyle("-fx-opacity: 1.0;");
        amount5.setStyle("-fx-opacity: 1.0;");
        totalText.setStyle("-fx-opacity: 1.0;");
        amount1.setText("0");
        amount2.setText("0");
        amount3.setText("0");
        amount4.setText("0");
        amount5.setText("0");
        totalText.setText("0");



    }

    public void calTotal(){
        int total=0;
        for(int i =0;i<all_list.getProducts().size();i++){
            if(products1.getValue().equals("-")){
            }else if(products1.getValue().equals(all_list.getProducts().get(i).getName())){
                total += a1*all_list.getProducts().get(i).getPrice();

            }
            if(products2.getValue().equals("-")){
            }else if(products2.getValue().equals(all_list.getProducts().get(i).getName())){
                total += a2*all_list.getProducts().get(i).getPrice();

            }
            if(products3.getValue().equals("-")){
            }else if(products3.getValue().equals(all_list.getProducts().get(i).getName())){
                total += a3*all_list.getProducts().get(i).getPrice();

            }
            if(products4.getValue().equals("-")){
            }else if(products4.getValue().equals(all_list.getProducts().get(i).getName())){
                total += a4*all_list.getProducts().get(i).getPrice();

            }
            if(products5.getValue().equals("-")){
            }else if(products5.getValue().equals(all_list.getProducts().get(i).getName())){
                total += a5*all_list.getProducts().get(i).getPrice();
            }
        }


        totalText.setText(Integer.toString(total));



    }

    public void comboBox1() {
        ObservableList<String> data = FXCollections.observableArrayList(all_list.getNameProduct());
        products1.setItems(data);
        products2.setItems(data);
        products3.setItems(data);
        products4.setItems(data);
        products5.setItems(data);

    }

    @FXML
    public void handleHomeButton(ActionEvent actionEvent){
        try {
            for(int i = 0;i<log1.getEmployees().size();i++){
                if(id.getText().equals(log1.getEmployees().get(i).getId()) && password.getText().equals(log1.getEmployees().get(i).getPassword())){
                    FXRouter.goTo("home");
                    loginError.setText("");
                }else {
                    loginError.setText("รหัสผ่านหรือIDไม่ถูกต้อง");
                }

            }
            for(int i = 0;i<log2.getProductions().size();i++){
                if(id.getText().equals(log2.getProductions().get(i).getId()) && password.getText().equals(log2.getProductions().get(i).getPassword())){
                    FXRouter.goTo("home2");
                    loginError.setText("");
                }else {
                    loginError.setText("รหัสผ่านหรือIDไม่ถูกต้อง");
                }
            }


        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleConfirmButton(ActionEvent actionEvent){
        error.setText("");
        complete.setText("");
        error2.setText("");

        if (Cname.getText().isEmpty() || Cnumber.getText().isEmpty()) {
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }
        if(Cnumber.getText().length() > 10 || Cnumber.getText().length() <9){
            error2.setText("โปรดกรอกเบอร์ให้ถูกต้อง");
        }else if( Cnumber.getText().substring(0,1).equals("0")){
            error2.setText("");
        }else {
            error2.setText("โปรดกรอกเบอร์ให้ถูกต้อง");
        }

        if (Cname.getText().isEmpty() && Cnumber.getText().isEmpty()){
        }else if(products1.getValue().equals("-") && products2.getValue().equals("-") && products3.getValue().equals("-")
                && products4.getValue().equals("-") && products5.getValue().equals("-")){
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }

        if (products1.getValue().equals("-")) {

        }else if(amount1.getText().equals("0")){
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }



        if (products2.getValue().equals("-")) {

        }else if(amount2.getText().equals("0")){
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");

        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }

        if (products3.getValue().equals("-")) {

        }else if(amount3.getText().equals("0")){
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }

        if (products4.getValue().equals("-")) {

        }else if(amount4.getText().equals("0")){
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }

        if (products5.getValue().equals("-")) {

        }else if(amount5.getText().equals("0")){
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }
        if (amount1.getText().equals("0")) {

        } else if (products1.getValue().equals("-")) {
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }

        if (amount2.getText().equals("0")) {

        } else if (products2.getValue().equals("-")) {
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }

        if (amount3.getText().equals("0")) {

        } else if (products3.getValue().equals("-")) {
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }

        if (amount4.getText().equals("0")) {

        } else if (products4.getValue().equals("-")) {
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }


        if (amount5.getText().equals("0")) {

        } else if (products5.getValue().equals("-")) {
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        }else if(error.getText().equals("โปรดกรอกข้อมูลให้ครบถ้วน")){
            error.setText("");
        }
        if(error.getText().equals("") && error2.getText().equals("")){

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean", "root", "9452178");
                Statement statement = connection.createStatement();



                if(products1.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO orders(cus_name,cus_number,products_name,amount,order_status)VALUES('"+Cname.getText()+"','"+ Cnumber.getText()+"','"+products1.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a1);
                    stmt.executeUpdate();
                    complete.setText("รับคำสั่งซื้อสินค้าแล้ว");

                }
                if(products2.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO orders(cus_name,cus_number,products_name,amount,order_status)VALUES('"+Cname.getText()+"','"+ Cnumber.getText()+"','"+products2.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a2);
                    stmt.executeUpdate();
                    complete.setText("รับคำสั่งซื้อสินค้าแล้ว");


                }
                if(products3.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO orders(cus_name,cus_number,products_name,amount,order_status)VALUES('"+Cname.getText()+"','"+ Cnumber.getText()+"','"+products3.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a3);
                    stmt.executeUpdate();
                    complete.setText("รับคำสั่งซื้อสินค้าแล้ว");
                }
                if(products4.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO orders(cus_name,cus_number,products_name,amount,order_status)VALUES('"+Cname.getText()+"','"+ Cnumber.getText()+"','"+products4.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a4);
                    stmt.executeUpdate();
                    complete.setText("รับคำสั่งซื้อสินค้าแล้ว");
                }
                if(products5.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO orders(cus_name,cus_number,products_name,amount,order_status)VALUES('"+Cname.getText()+"','"+ Cnumber.getText()+"','"+products5.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a5);
                    stmt.executeUpdate();
                    complete.setText("รับคำสั่งซื้อสินค้าแล้ว");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void handleAmount1Plus1Button(ActionEvent actionEvent){
        if(products1.getValue().equals("-")){
        }else {
            a1+=1;
            amount1.setText(String.valueOf(a1));
            calTotal();
        }

    }
    @FXML
    public void handleAmount2Plus1Button(ActionEvent actionEvent){
        if(products2.getValue().equals("-")){
        }else {
            a2 += 1;
            amount2.setText(String.valueOf(a2));
            calTotal();
        }
    }
    @FXML
    public void handleAmount3Plus1Button(ActionEvent actionEvent){
        if(products3.getValue().equals("-")){
        }else {
            a3+=1;
            amount3.setText(String.valueOf(a3));
            calTotal();
        }
    }
    @FXML
    public void handleAmount4Plus1Button(ActionEvent actionEvent){
        if(products4.getValue().equals("-")){
        }else {
            a4 += 1;
            amount4.setText(String.valueOf(a4));
            calTotal();
        }
    }
    @FXML
    public void handleAmount5Plus1Button(ActionEvent actionEvent){
        if(products5.getValue().equals("-")){
        }else {
            a5 += 1;
            amount5.setText(String.valueOf(a5));
            calTotal();
        }

    }

    @FXML
    public void handleAmount1Plus10Button(ActionEvent actionEvent){
        if(products1.getValue().equals("-")){
        }else {
            a1 += 10;
            amount1.setText(String.valueOf(a1));
            calTotal();
        }
    }
    @FXML
    public void handleAmount2Plus10Button(ActionEvent actionEvent){
        if(products2.getValue().equals("-")){
        }else {
            a2 += 10;
            amount2.setText(String.valueOf(a2));
            calTotal();
        }
    }
    @FXML
    public void handleAmount3Plus10Button(ActionEvent actionEvent){
        if(products3.getValue().equals("-")){
        }else {
            a3 += 10;
            amount3.setText(String.valueOf(a3));
            calTotal();
        }
    }
    @FXML
    public void handleAmount4Plus10Button(ActionEvent actionEvent){
        if(products4.getValue().equals("-")){
        }else {
            a4 += 10;
            amount4.setText(String.valueOf(a4));
            calTotal();
        }
    }
    @FXML
    public void handleAmount5Plus10Button(ActionEvent actionEvent){
        if(products5.getValue().equals("-")){
        }else {
            a5 += 10;
            amount5.setText(String.valueOf(a5));
            calTotal();
        }
    }

    @FXML
    public void handleAmount1Minus1Button(ActionEvent actionEvent){
        if(products1.getValue().equals("-")){
        }else {
            a1 -= 1;
            if (a1 < 0) {
                a1 = 0;
            }
            amount1.setText(String.valueOf(a1));
            calTotal();
        }
    }
    @FXML
    public void handleAmount2Minus1Button(ActionEvent actionEvent){
        if(products2.getValue().equals("-")){
        }else {
            a2 -= 1;
            if (a2 < 0) {
                a2 = 0;
            }
            amount2.setText(String.valueOf(a2));
            calTotal();
        }

    }
    @FXML
    public void handleAmount3Minus1Button(ActionEvent actionEvent){
        if(products3.getValue().equals("-")){
        }else {
            a3 -= 1;
            if (a3 < 0) {
                a3 = 0;
            }
            amount3.setText(String.valueOf(a3));
            calTotal();
        }
    }
    @FXML
    public void handleAmount4Minus1Button(ActionEvent actionEvent){
        if(products4.getValue().equals("-")){
        }else {
            a4 -= 1;
            if (a4 < 0) {
                a4 = 0;
            }
            amount4.setText(String.valueOf(a4));
            calTotal();
        }
    }
    @FXML
    public void handleAmount5Minus1Button(ActionEvent actionEvent){
        if(products5.getValue().equals("-")){
        }else {
            a5 -= 1;
            if (a5 < 0) {
                a5 = 0;
            }
            amount5.setText(String.valueOf(a5));
            calTotal();
        }
    }

    @FXML
    public void handleAmount1Minus10Button(ActionEvent actionEvent){
        if(products1.getValue().equals("-")){
        }else {
            a1 -= 10;
            if (a1 < 0) {
                a1 = 0;
            }
            amount1.setText(String.valueOf(a1));
            calTotal();
        }
    }
    @FXML
    public void handleAmount2Minus10Button(ActionEvent actionEvent){
        if(products2.getValue().equals("-")){
        }else {
            a2 -= 10;
            if (a2 < 0) {
                a2 = 0;
            }
            amount2.setText(String.valueOf(a2));
            calTotal();
        }
    }
    @FXML
    public void handleAmount3Minus10Button(ActionEvent actionEvent){
        if(products3.getValue().equals("-")){
        }else {
            a3 -= 10;
            if (a3 < 0) {
                a3 = 0;
            }
            amount3.setText(String.valueOf(a3));
            calTotal();
        }
    }
    @FXML
    public void handleAmount4Minus10Button(ActionEvent actionEvent){
        if(products4.getValue().equals("-")){
        }else {
            a4 -= 10;
            if (a4 < 0) {
                a4 = 0;
            }
            amount4.setText(String.valueOf(a4));
            calTotal();
        }
    }
    @FXML
    public void handleAmount5Minus10Button(ActionEvent actionEvent){
        if(products5.getValue().equals("-")){
        }else {
            a5 -= 10;
            if (a5 < 0) {
                a5 = 0;
            }
            amount5.setText(String.valueOf(a5));
            calTotal();
        }
    }

    @FXML
    public void handleCancelButton(ActionEvent actionEvent){
        Cnumber.setText("");
        Cname.setText("");
        products1.getSelectionModel().selectFirst();
        products2.getSelectionModel().selectFirst();
        products3.getSelectionModel().selectFirst();
        products4.getSelectionModel().selectFirst();
        products5.getSelectionModel().selectFirst();
        amount1.setText("0");
        amount2.setText("0");
        amount3.setText("0");
        amount4.setText("0");
        amount5.setText("0");
        totalText.setText("0");
        a1=0;
        a2=0;
        a3=0;
        a4=0;
        a5=0;
    }












}



