package administration.controllers;

import administration.models.All_List;
import administration.models.Ingredients;
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
import java.text.NumberFormat;
import java.util.ArrayList;

public class Ingredients2_Controller {


    private ObservableList<Ingredients> ingredientsObservableList;
    @FXML
    private TableView<Ingredients> ingredientsTableView;
    @FXML
    private TextField ingredientsID,ingredientsAM,search;
    @FXML
    private Label error,error2,error3;
    private String v2,str;
    private int v1,v3,e2;
    private float v4,e1;

    All_List all_list = new All_List();
    public void initialize(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from ingredients");

            while (resultSet.next()){

                v1=resultSet.getInt("ingredients_id");
                v2=resultSet.getString("ingredients_name");
                v4=resultSet.getFloat("ingredients_amount");
                Ingredients ingredients = new Ingredients(v1,v2,v4);
                all_list.addIngredient(ingredients);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showIngredientsData();


    }
    public void showIngredientsData(){

        ingredientsObservableList = FXCollections.observableArrayList(all_list.getIngredients());
        ingredientsTableView.setItems(ingredientsObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("Title:รหัส","Field:id"));
        configs.add(new StringConfig("Title:ชื่อวัตถุดิบ","Field:name"));
        configs.add(new StringConfig("Title:จำนวนคงเหลือ","Field:amount"));

        int i = 0;
        for (StringConfig conf: configs) {
            TableColumn col = new TableColumn(conf.get("Title"));
            if(i==1){
                col.setPrefWidth(688);
            }
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("Field")));
            ingredientsTableView.getColumns().add(col);
            i++;
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
    public void handleUpdatePointButton(ActionEvent actionEvent) {
        error.setText("");
        error2.setText("");

        for(int i =0;i<all_list.getIngredients().size();i++){
            str = Integer.toString(all_list.getIngredients().get(i).getId());
            if(ingredientsID.getText().equals(str)){

                error.setText("");
                break;
            }else {
                error.setText("ไม่พบรหัสIngredientsในระบบ");

            }
        }
        if(ingredientsAM.getText().equals("")){
            error2.setText("ใส่จำนวนไม่ถูกต้อง");
        }else{
            try {
                e1 = Float.parseFloat(ingredientsAM.getText());
            }catch (NumberFormatException e){
                error2.setText("ใส่จำนวนไม่ถูกต้อง");
            }

        }
        if(e1<0){
            error2.setText("ใส่จำนวนไม่ถูกต้อง");
        }


        if(error2.getText().equals("") && error.getText().equals("")){
            error.setText("");
            error2.setText("");
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean", "root", "9452178");
                e2 = Integer.parseInt(ingredientsID.getText());

                String sql = "UPDATE ingredients SET ingredients_amount = '"+e1+"' WHERE ingredients_id = ?;";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1,e2);
                stmt.executeUpdate();
                refreshTable();



            }catch (Exception e){
                e.printStackTrace();
            }

        }



    }

    private void refreshTable(){
        all_list.getIngredients().clear();
        ingredientsObservableList.clear();
        ingredientsTableView.getColumns().clear();
        initialize();


    }


    @FXML
    public void handleSearchPointButton(ActionEvent actionEvent) {

        for(int i=0;i<all_list.getIngredients().size();i++){
            str = Integer.toString(all_list.getIngredients().get(i).getId());
            if(search.getText().equals(str)){
                error3.setText("");
                break;
            }else {
                error3.setText("ไม่พบรหัสingredientsในระบบ");
            }
        }

        if(error3.getText().equals("")){
            try{
                searchTable();
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
                Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
                e1 = Integer.parseInt(search.getText());
                ResultSet resultSet = statement.executeQuery("Select * from ingredients Where ingredients_id = '"+e1+"'");
                while (resultSet.next()){
                    v1=resultSet.getInt("ingredients_id");
                    v2=resultSet.getString("ingredients_name");
                    v4=resultSet.getFloat("ingredients_amount");
                    Ingredients ingredients = new Ingredients(v1,v2,v4);
                    all_list.addIngredient(ingredients);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            showIngredientsData();

            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
                Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
                ResultSet resultSet = statement.executeQuery("Select * from ingredients");

                while (resultSet.next()){

                    v1=resultSet.getInt("ingredients_id");
                    v2=resultSet.getString("ingredients_name");
                    v4=resultSet.getFloat("ingredients_amount");
                    Ingredients ingredients = new Ingredients(v1,v2,v4);
                    all_list.addIngredient(ingredients);



                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        }


    }

    private void searchTable(){
        all_list.getIngredients().clear();
        ingredientsObservableList.clear();
        ingredientsTableView.getColumns().clear();


    }
    @FXML
    public void handleResetPointButton(ActionEvent actionEvent) {
        searchTable();
        initialize();
    }
}
