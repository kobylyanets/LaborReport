package ru.indraft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.indraft.service.LocaleService;

public class Main extends Application {

    private static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
    private static final String LABOR_TAB_FXML_PATH = "/view/LaborPage.fxml";

    private static final class Title {
        private static final String MAIN_WINDOW = "main.window.title";
        private static final String LABOR_TAB = "tab.labor.title";
    }

    private LocaleService lres = LocaleService.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane rootLayout = FXMLLoader.load(getClass().getResource(MAIN_WINDOW_FXML_PATH));
        initLaborTab(rootLayout);
        primaryStage.setTitle(lres.get(Title.MAIN_WINDOW));
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    private void initLaborTab(TabPane tabPane) throws Exception {
        Tab tab = new Tab();
        tab.setText(lres.get(Title.LABOR_TAB));
        AnchorPane content = FXMLLoader.load(getClass().getResource(LABOR_TAB_FXML_PATH), lres.getResourceBundle());
        tab.setContent(content);
        tabPane.getTabs().add(tab);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
