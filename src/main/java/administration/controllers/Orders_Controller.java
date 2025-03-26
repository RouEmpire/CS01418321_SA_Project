package administration.controllers;

import administration.models.All_List;
import administration.models.Orders;
import administration.models.Products;
import administration.services.StringConfig;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Orders_Controller {

    private ObservableList<Orders> ordersObservableList;
    @FXML
    private TableView<Orders> ordersTableView;
    @FXML
    private TextField orderID,status,search;
    @FXML
    private Label error,error2,error3;
    private String v2,v3,v4;
    private int v1,v5,v6,odID,st,bb;
    All_List all_list = new All_List();
    private String str;


    @FXML
    public void initialize(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from orders");

            while (resultSet.next()){

                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showOrdersData();


    }



    public void showOrdersData(){
//        System.out.println(all_list.getProducts());
        ordersObservableList = FXCollections.observableArrayList(all_list.getOrders());
        ordersTableView.setItems(ordersObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("Title:รหัสออเดอร์","Field:id"));
        configs.add(new StringConfig("Title:ชื่อผู้สั่ง","Field:cusName"));
        configs.add(new StringConfig("Title:เบอร์โทรศัพท์","Field:cusNumber"));
        configs.add(new StringConfig("Title:ชื่อสินค้า","Field:proName"));
        configs.add(new StringConfig("Title:จำนวน","Field:amount"));
        configs.add(new StringConfig("Title:สถานะ","Field:orderStatus"));


        int i = 0;
        for (StringConfig conf: configs) {
            TableColumn col = new TableColumn(conf.get("Title"));
            if(i==1){
                col.setPrefWidth(400);
            }
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("Field")));
            col.setStyle("-fx-alignment: CENTER;");
            ordersTableView.getColumns().add(col);
            i++;
        }




    }

    @FXML
        public void handleUpdatePointButton(ActionEvent actionEvent) {
        error.setText("");
        error2.setText("");
        if(status.getText().equals("")){
        }else{
            bb = Integer.parseInt(status.getText());
        }
        for(int i =0;i<all_list.getOrders().size();i++){
            str = Integer.toString(all_list.getOrders().get(i).getId());
            if(orderID.getText().equals(str)){
                error.setText("");
                if(bb>3||bb<-1 || status.getText().isEmpty()) {
                    error2.setText("สถานะไม่ถูกต้อง");
                }else if(status.getText().equals("1")){
                    error2.setText("ไม่มีสิทธิ์");
                }else if(status.getText().equals("-1")&& all_list.getOrders().get(i).getOrderStatus() == 0
                        || status.getText().equals("3")&& all_list.getOrders().get(i).getOrderStatus() == 2
                        || status.getText().equals("2")&& all_list.getOrders().get(i).getOrderStatus() == 1){
                }else {
                    error2.setText("ไม่สามารถเปลี่ยนข้ามสถานะได้");
                }
                break;
            }else {
                if(status.getText().equals("")){
                    error2.setText("สถานะไม่ถูกต้อง");
                }
                error.setText("ไม่พบรหัสOrderในระบบ");
            }
        }

        if(error2.getText().equals("")&& error.getText().equals("")){
            error.setText("");
            error2.setText("");
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean", "root", "9452178");
                odID = Integer.parseInt(orderID.getText());
                st = Integer.parseInt(status.getText());

                String sql = "UPDATE orders SET order_status = '"+st+"' WHERE order_id = ?;";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, odID);
                stmt.executeUpdate();
                refreshTable();



            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    private void refreshTable(){
        all_list.getOrders().clear();
        ordersObservableList.clear();
        ordersTableView.getColumns().clear();
        initialize();


    }
    @FXML
    public void handleBackPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleStatusMinus1Button(ActionEvent actionEvent) {
        try{
            searchTable();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from orders Where order_status = -1");
            while (resultSet.next()){
                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showOrdersData();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from orders");

            while (resultSet.next()){

                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void handleStatus1Button(ActionEvent actionEvent) {
        try{
            searchTable();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from orders Where order_status = 1");
            while (resultSet.next()){
                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showOrdersData();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from orders");

            while (resultSet.next()){

                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handleStatus2Button(ActionEvent actionEvent) {
        try{
            searchTable();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from orders Where order_status = 2");
            while (resultSet.next()){
                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showOrdersData();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from orders");

            while (resultSet.next()){

                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handleSearchPointButton(ActionEvent actionEvent) {
        error3.setText("");
        for(int i =0;i<all_list.getOrders().size();i++){
            str = Integer.toString(all_list.getOrders().get(i).getId());
            if(search.getText().equals(str)){
                error3.setText("");
                break;
            }else{
                error3.setText("ไม่พบรหัสOrdersในระบบ");
            }
        }
        if(error3.getText().equals("")){
            try{
                searchTable();
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
                Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
                odID = Integer.parseInt(search.getText());
                ResultSet resultSet = statement.executeQuery("Select * from orders Where order_id = '"+odID+"'");
                while (resultSet.next()){
                    v1=resultSet.getInt("order_id");
                    v2=resultSet.getString("cus_name");
                    v3=resultSet.getString("cus_number");
                    v4=resultSet.getString("products_name");
                    v5=resultSet.getInt("amount");
                    v6=resultSet.getInt("order_status");
                    Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                    all_list.addOrders(orders);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            showOrdersData();

            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
                Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
                ResultSet resultSet = statement.executeQuery("Select * from orders");

                while (resultSet.next()){

                    v1=resultSet.getInt("order_id");
                    v2=resultSet.getString("cus_name");
                    v3=resultSet.getString("cus_number");
                    v4=resultSet.getString("products_name");
                    v5=resultSet.getInt("amount");
                    v6=resultSet.getInt("order_status");
                    Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                    all_list.addOrders(orders);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }




        }
    }


    private void searchTable(){

        all_list.getOrders().clear();
        ordersObservableList.clear();
        ordersTableView.getColumns().clear();



    }
    @FXML
    public void handleResetPointButton(ActionEvent actionEvent) {
        searchTable();
        initialize();
    }

    @FXML
    public void handleStatus0Button(ActionEvent actionEvent) {
        try{
            searchTable();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from orders Where order_status = 0");
            while (resultSet.next()){
                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showOrdersData();

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from orders");

            while (resultSet.next()){

                v1=resultSet.getInt("order_id");
                v2=resultSet.getString("cus_name");
                v3=resultSet.getString("cus_number");
                v4=resultSet.getString("products_name");
                v5=resultSet.getInt("amount");
                v6=resultSet.getInt("order_status");
                Orders orders = new Orders(v1,v2,v3,v4,v5,v6);
                all_list.addOrders(orders);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
