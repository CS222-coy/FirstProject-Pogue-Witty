package edu.bsu.cs222;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WikipediaApplication extends Application {

    private final TextField textField = new TextField();
    private final Button button = new Button("Search Revisions");
    private final Executor executor = Executors.newSingleThreadExecutor();


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.show();

    }

    private Parent createUI() {
        button.setOnAction((event) -> {
            button.setDisable(true);
            textField.setDisable(true);
            executor.execute(()->{

                String text = textField.getText();
                int value = Integer.parseInt(text);
                WikipediaRevisionParser parser = new WikipediaRevisionParser();
                boolean result = parser.doesPageExist(value);

                Platform.runLater(()->{
                    button.setDisable(false);
                    textField.setDisable(false);
                });
            });
        });


        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                textField,
                button
        );
        return vbox;
    }
}
