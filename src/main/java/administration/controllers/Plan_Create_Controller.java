package administration.controllers;

import administration.models.All_List;
import administration.models.Products;
import administration.services.LoginDataSource;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Plan_Create_Controller {
    @FXML
    private ComboBox<String> products1, products2, products3, products4, products5;
    @FXML
    private TextField amount1, amount2, amount3, amount4, amount5,totalText;
    @FXML
    private Label error,complete;
    @FXML
    private DatePicker datePicker;
    private Date date;
    private int a1,a2,a3,a4,a5,e1;
    All_List all_list = new All_List();



    public void initialize() {

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
        amount1.setStyle("-fx-opacity: 1.0;");
        amount2.setStyle("-fx-opacity: 1.0;");
        amount3.setStyle("-fx-opacity: 1.0;");
        amount4.setStyle("-fx-opacity: 1.0;");
        amount5.setStyle("-fx-opacity: 1.0;");
        amount1.setText("0");
        amount2.setText("0");
        amount3.setText("0");
        amount4.setText("0");
        amount5.setText("0");
        datePicker.getEditor().setDisable(true);
        datePicker.getEditor().setOpacity(1);

    }

    public void comboBox1() {
        ObservableList<String> data = FXCollections.observableArrayList(all_list.getNameProduct());
        products1.setItems(data);
        products2.setItems(data);
        products3.setItems(data);
        products4.setItems(data);
        products5.setItems(data);

    }
    public void getDate(ActionEvent actionEvent){
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        date = Date.from(instant);


    }
    @FXML
    public void handleConfirmButton(ActionEvent actionEvent){
        error.setText("");
        complete.setText("");
        if(datePicker.getValue()==null){
            error.setText("โปรดกรอกข้อมูลให้ครบถ้วน");

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
        if(error.getText().equals("")){
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean", "root", "9452178");
                Statement statement = connection.createStatement();

                if(products1.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO plan(plan_date, plan_products, plan_amount, plan_status)VALUES('"+sqlDate+"','"+ products1.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a1);
                    stmt.executeUpdate();
                    complete.setText("เพิ่มแผนผลิตแล้ว");

                }
                if(products2.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO plan(plan_date, plan_products, plan_amount, plan_status)VALUES('"+sqlDate+"','"+ products2.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a2);
                    stmt.executeUpdate();
                    complete.setText("เพิ่มแผนผลิตแล้ว");


                }
                if(products3.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO plan(plan_date, plan_products, plan_amount, plan_status)VALUES('"+sqlDate+"','"+ products3.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a3);
                    stmt.executeUpdate();
                    complete.setText("เพิ่มแผนผลิตแล้ว");
                }
                if(products4.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO plan(plan_date, plan_products, plan_amount, plan_status)VALUES('"+sqlDate+"','"+ products4.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a4);
                    stmt.executeUpdate();
                    complete.setText("เพิ่มแผนผลิตแล้ว");
                }
                if(products5.getValue().equals("-")){

                }else  {
                    String sql = "INSERT INTO plan(plan_date, plan_products, plan_amount, plan_status)VALUES('"+sqlDate+"','"+ products5.getValue()+"',?,0);";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1, a5);
                    stmt.executeUpdate();
                    complete.setText("เพิ่มแผนผลิตแล้ว");
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

        }

    }
    @FXML
    public void handleAmount2Plus1Button(ActionEvent actionEvent){
        if(products2.getValue().equals("-")){
        }else {
            a2 += 1;
            amount2.setText(String.valueOf(a2));
        }
    }
    @FXML
    public void handleAmount3Plus1Button(ActionEvent actionEvent){
        if(products3.getValue().equals("-")){
        }else {
            a3+=1;
            amount3.setText(String.valueOf(a3));
        }
    }
    @FXML
    public void handleAmount4Plus1Button(ActionEvent actionEvent){
        if(products4.getValue().equals("-")){
        }else {
            a4 += 1;
            amount4.setText(String.valueOf(a4));
        }
    }
    @FXML
    public void handleAmount5Plus1Button(ActionEvent actionEvent){
        if(products5.getValue().equals("-")){
        }else {
            a5 += 1;
            amount5.setText(String.valueOf(a5));
        }

    }

    @FXML
    public void handleAmount1Plus10Button(ActionEvent actionEvent){
        if(products1.getValue().equals("-")){
        }else {
            a1 += 10;
            amount1.setText(String.valueOf(a1));
        }
    }
    @FXML
    public void handleAmount2Plus10Button(ActionEvent actionEvent){
        if(products2.getValue().equals("-")){
        }else {
            a2 += 10;
            amount2.setText(String.valueOf(a2));
        }
    }
    @FXML
    public void handleAmount3Plus10Button(ActionEvent actionEvent){
        if(products3.getValue().equals("-")){
        }else {
            a3 += 10;
            amount3.setText(String.valueOf(a3));
        }
    }
    @FXML
    public void handleAmount4Plus10Button(ActionEvent actionEvent){
        if(products4.getValue().equals("-")){
        }else {
            a4 += 10;
            amount4.setText(String.valueOf(a4));
        }
    }
    @FXML
    public void handleAmount5Plus10Button(ActionEvent actionEvent){
        if(products5.getValue().equals("-")){
        }else {
            a5 += 10;
            amount5.setText(String.valueOf(a5));
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
        }
    }

    @FXML
    public void handleBackPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home2");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home2 ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleCancelButton(ActionEvent actionEvent){
        datePicker.getEditor().setText("");
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
        a1=0;
        a2=0;
        a3=0;
        a4=0;
        a5=0;
    }

}
