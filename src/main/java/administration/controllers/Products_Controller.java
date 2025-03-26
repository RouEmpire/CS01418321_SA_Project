package administration.controllers;

import administration.models.All_List;
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

public class Products_Controller {
    private ObservableList<Products> productsObservableList;
    @FXML
    private TableView<Products> productsTableView;
    @FXML
    private TextField search,productsAM,productsID;
    @FXML
    private Label error,error1,error2;
    private String v2;
    private int v1,v3,v4,idP,pdAM;
    All_List all_list = new All_List();
    private String str;
    @FXML
    public void initialize(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from products");

            while (resultSet.next()){

                v1=resultSet.getInt("product_id");
                v2=resultSet.getString("product_name");
                v3=resultSet.getInt("product_amount");
                v4=resultSet.getInt("product_price");
                Products products = new Products(v1,v2,v3,v4);
                all_list.addProduct(products);
                all_list.addIdProduct(v1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        showProductsData();


    }

    public void showProductsData(){
//        System.out.println(all_list.getProducts());
        productsObservableList = FXCollections.observableArrayList(all_list.getProducts());
        productsTableView.setItems(productsObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("Title:รหัส","Field:id"));
        configs.add(new StringConfig("Title:ชื่อสินค้า","Field:name"));
        configs.add(new StringConfig("Title:จำนวนคงเหลือ","Field:amount"));
        configs.add(new StringConfig("Title:ราคาต่อชิ้น","Field:price"));

        int i = 0;
        for (StringConfig conf: configs) {
            TableColumn col = new TableColumn(conf.get("Title"));
            if(i==1){
                col.setPrefWidth(688);
            }
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("Field")));
            productsTableView.getColumns().add(col);
            i++;
        }


    }



    @FXML
    public void handleSearchPointButton(ActionEvent actionEvent) {
        for(int i =0;i<all_list.getProducts().size();i++){
            str = Integer.toString(all_list.getProducts().get(i).getId());
            if(search.getText().equals(str)){
                error.setText("");
                break;
            }else {
                error.setText("ไม่พบรหัสProductในระบบ");
            }
        }
        if(error.getText().equals("")){
            try{
                searchTable();
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
                Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
                idP = Integer.parseInt(search.getText());
                ResultSet resultSet = statement.executeQuery("Select * from products Where product_id = '"+idP+"'");
                while (resultSet.next()){

                    v1=resultSet.getInt("product_id");
                    v2=resultSet.getString("product_name");
                    v3=resultSet.getInt("product_amount");
                    v4=resultSet.getInt("product_price");
                    Products products = new Products(v1,v2,v3,v4);
                    all_list.addProduct(products);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            showProductsData();

            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
                Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
                idP = Integer.parseInt(search.getText());
                ResultSet resultSet = statement.executeQuery("Select * from products");
                while (resultSet.next()){

                    v1=resultSet.getInt("product_id");
                    v2=resultSet.getString("product_name");
                    v3=resultSet.getInt("product_amount");
                    v4=resultSet.getInt("product_price");
                    Products products = new Products(v1,v2,v3,v4);
                    all_list.addProduct(products);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }




        }
    }
    private void searchTable(){
        all_list.getProducts().clear();
        productsObservableList.clear();
        productsTableView.getColumns().clear();


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
    public void handleResetPointButton(ActionEvent actionEvent) {
        searchTable();
        initialize();
    }
    @FXML
    public void handleUpdatePointButton(ActionEvent actionEvent) {
        error1.setText("");
        error2.setText("");

        for(int i =0;i<all_list.getProducts().size();i++){
            str = Integer.toString(all_list.getProducts().get(i).getId());
            if(productsID.getText().equals(str)){
                error1.setText("");
                break;
            }else {
                error1.setText("ไม่พบรหัสProductsในระบบ");

            }
        }
        if(productsAM.getText().equals("")){
            error2.setText("ใส่จำนวนไม่ถูกต้อง");
        }else{
            try {
                pdAM = Integer.parseInt(productsAM.getText());
            }catch (NumberFormatException e){
                error2.setText("ใส่จำนวนไม่ถูกต้อง");
            }

        }
        if(pdAM<0){
            error2.setText("ใส่จำนวนไม่ถูกต้อง");
        }


        if(error2.getText().equals("") && error1.getText().equals("")){
            error1.setText("");
            error2.setText("");
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean", "root", "9452178");
                idP = Integer.parseInt(productsID.getText());

                String sql = "UPDATE products SET product_amount = '"+pdAM+"' WHERE product_id = ?;";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1,idP);
                stmt.executeUpdate();
                refreshTable();



            }catch (Exception e){
                e.printStackTrace();
            }

        }



    }

    private void refreshTable(){
        all_list.getProducts().clear();
        productsObservableList.clear();
        productsTableView.getColumns().clear();
        initialize();


    }
}
