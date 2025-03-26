package administration.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Home2_Controller {

    @FXML
    public void initialize() {

    }


    @FXML
    public void handleProductsPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("products2");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า products2 ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleOrdersPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("orders2");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า orders2 ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }


    }

    @FXML
    public void handleIngredientsPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ingredients2");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ingredients2 ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handlePlansPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("plan");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า plan ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAddPlansPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("add_plan");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า add_plan ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleLogOutPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
}
