package administration.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.io.IOException;

public class Home_Controller {

    @FXML
    public void initialize(){

    }



    @FXML
    public void handleProductsPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("products");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า products ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }



    @FXML
    public void handleOrdersPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("orders");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า orders ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }
    @FXML
    public void handleIngredientsPointButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ingredients");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ingredients ไม่ได้");
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
