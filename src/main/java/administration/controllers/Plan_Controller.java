package administration.controllers;

import administration.models.All_List;
import administration.models.Ingredients;
import administration.models.Orders;
import administration.models.Plan;
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

public class Plan_Controller {


    private ObservableList<Plan> planObservableList;
    @FXML
    private TableView<Plan> plansTableView;
    @FXML
    private TextField planID,status,search;
    @FXML
    private Label error,error2,error3;
    private String v2,v3;
    private int v1,v5,v4,pID,st,bb,odP;
    All_List all_list = new All_List();
    private String str;


    @FXML
    public void initialize(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
            Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
            ResultSet resultSet = statement.executeQuery("Select * from plan");

            while (resultSet.next()){

                v1=resultSet.getInt("plan_id");
                v2=resultSet.getString("plan_date");
                v3=resultSet.getString("plan_products");
                v4=resultSet.getInt("plan_amount");
                v5=resultSet.getInt("plan_status");
                Plan plan = new Plan(v1,v2,v3,v4,v5);
                all_list.addPlan(plan);



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        showPlansData();


    }


    public void showPlansData() {
//        System.out.println(all_list.getProducts());
       planObservableList = FXCollections.observableArrayList(all_list.getPlans());
        plansTableView.setItems(planObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("Title:รหัสแผน", "Field:id"));
        configs.add(new StringConfig("Title:วันที่จะทำ", "Field:date"));
        configs.add(new StringConfig("Title:ชื่อสินค้า", "Field:planProduct"));
        configs.add(new StringConfig("Title:จำนวน", "Field:amount"));
        configs.add(new StringConfig("Title:สถานะ", "Field:status"));
        int i = 0;
        for (StringConfig conf : configs) {
            TableColumn col = new TableColumn(conf.get("Title"));
            if (i == 1) {
                col.setPrefWidth(200);
            }
            if (i == 2) {
                col.setPrefWidth(400);
            }
            if (i == 3) {
                col.setPrefWidth(107);
            }
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("Field")));
            col.setStyle("-fx-alignment: CENTER;");
            plansTableView.getColumns().add(col);
            i++;
        }
    }


    @FXML
    public void handleSearchPointButton(ActionEvent actionEvent) {

        for(int i=0;i<all_list.getPlans().size();i++){
            str = Integer.toString(all_list.getPlans().get(i).getId());
            if(search.getText().equals(str)){
                error3.setText("");
                break;
            }else {
                error3.setText("ไม่พบรหัสPlansในระบบ");
            }
        }

        if(error3.getText().equals("")){
            try{
                searchTable();
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
                Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
                pID = Integer.parseInt(search.getText());
                ResultSet resultSet = statement.executeQuery("Select * from plan Where plan_id = '"+pID+"'");
                while (resultSet.next()){
                    v1=resultSet.getInt("plan_id");
                    v2=resultSet.getString("plan_date");
                    v3=resultSet.getString("plan_products");
                    v4=resultSet.getInt("plan_amount");
                    v5=resultSet.getInt("plan_status");
                    Plan plan = new Plan(v1,v2,v3,v4,v5);
                    all_list.addPlan(plan);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            showPlansData();

            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean","root","9452178");
                Statement statement = connection.createStatement();
//            statement.execute("Insert into products(product_name,product_amount)Values('abc',10);");
                ResultSet resultSet = statement.executeQuery("Select * from plan");

                while (resultSet.next()){

                    v1=resultSet.getInt("plan_id");
                    v2=resultSet.getString("plan_date");
                    v3=resultSet.getString("plan_products");
                    v4=resultSet.getInt("plan_amount");
                    v5=resultSet.getInt("plan_status");
                    Plan plan = new Plan(v1,v2,v3,v4,v5);
                    all_list.addPlan(plan);



                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        }

    }

    private void searchTable(){
        all_list.getPlans().clear();
        planObservableList.clear();
        plansTableView.getColumns().clear();


    }

    @FXML
    public void handleBackPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home2");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleUpdatePointButton(ActionEvent actionEvent) {
        error.setText("");
        error2.setText("");
        for (int i = 0; i < all_list.getPlans().size(); i++) {
            str = Integer.toString(all_list.getPlans().get(i).getId());
            if (planID.getText().equals(str)) {
                st = Integer.parseInt(status.getText());
                error.setText("");
                break;
            } else {
                error.setText("ไม่พบรหัสPlansในระบบ");

            }
        }
        if (st > 1 || st < 0 || status.getText().equals("")) {
            error2.setText("สถานะไม่ถูกต้อง");
        }

        if (error2.getText().equals("") && error.getText().equals("")) {
            error.setText("");
            error2.setText("");
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/bean", "root", "9452178");
                bb = Integer.parseInt(planID.getText());

                String sql = "UPDATE plan SET plan_status = '" + st + "' WHERE plan_id = ?;";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, bb);
                stmt.executeUpdate();
                refreshTable();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    private void refreshTable(){
        all_list.getPlans().clear();
        planObservableList.clear();
        plansTableView.getColumns().clear();
        initialize();


    }

    @FXML
    public void handleResetPointButton(ActionEvent actionEvent) {
        searchTable();
        initialize();
    }




}
