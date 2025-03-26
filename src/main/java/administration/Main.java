package administration;

import com.github.saacsos.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXRouter.bind(this, primaryStage, "Bean", 1280, 1024);

        configRoute();

        FXRouter.goTo("add_plan");

    }

    private static void configRoute() {

        FXRouter.when("products", "Product_show_View.fxml");
        FXRouter.when("products2", "Product2_show_View.fxml");
        FXRouter.when("home", "Home_show_View.fxml");
        FXRouter.when("home2", "Home2_show_View.fxml");
        FXRouter.when("ingredients", "Ingredients_show_View.fxml");
        FXRouter.when("ingredients2", "Ingredients2_show_View.fxml");
        FXRouter.when("login", "Order_create_View.fxml");
        FXRouter.when("orders", "Order_show_View.fxml");
        FXRouter.when("orders2", "Order2_show_View.fxml");
        FXRouter.when("plan", "Plan_show_View.fxml");
        FXRouter.when("add_plan", "Plan_create_View.fxml");


    }


    public static void main(String[] args) {
        launch(args);
    }
}
