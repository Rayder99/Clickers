package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try{
        GridPane gp = new GridPane();
        Label xLabel = new Label("X-Value");
        Label yLabel = new Label("Y-Value");
        TextField xValue = new TextField("960");
        TextField yValue = new TextField("540");
        Slider xSlider = new Slider(1,1920,1);
        Slider ySlider = new Slider(1,1080,1);
        xSlider.setValue(960);
        ySlider.setValue(540);

        xSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                xSlider.setValue(Math.round(xSlider.getValue()));
                xValue.setText(String.valueOf(xSlider.getValue()));
            }
        });
        ySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                ySlider.setValue(Math.round(ySlider.getValue()));
                yValue.setText(String.valueOf(ySlider.getValue()));
            }
        });

        Label clickLabel = new Label("Number of clicks");
        TextField clickValue = new TextField("500");

        javafx.scene.control.Button startButton = new javafx.scene.control.Button("Auto Click");
            javafx.scene.control.Button saveone = new javafx.scene.control.Button("1");
            javafx.scene.control.Button savetwo = new javafx.scene.control.Button("2");
            javafx.scene.control.Button savethree = new javafx.scene.control.Button("3");
            javafx.scene.control.Button savefour = new javafx.scene.control.Button("4");
            saveone.setMinWidth(180);
            savetwo.setMinWidth(180);
            savethree.setMinWidth(180);
            savefour.setMinWidth(180);
            ProgressBar pb = new ProgressBar(0);
        pb.setMinWidth(180);
        pb.setMinHeight(40);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int x = (int) xSlider.getValue();
                int y=  (int) ySlider.getValue();
                int clicks = Integer.parseInt(clickValue.getText());
                pb.setProgress(0);

                try {
                    clicker(x, y, clicks, pb);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
            }
        );

        gp.setConstraints(xLabel,0,0);
        gp.setConstraints(xSlider,0,1);
        gp.setConstraints(ySlider,0,4);
        gp.setConstraints(yLabel,0,3);
        gp.setConstraints(xValue,0,2);
        gp.setConstraints(yValue,0,5);
        gp.setConstraints(clickLabel,0,6);
        gp.setConstraints(clickValue,0,7);
        gp.setConstraints(startButton,0,8);
            gp.setConstraints(saveone,0,10);
            gp.setConstraints(savetwo,0,11);
            gp.setConstraints(savethree,0,12);
            gp.setConstraints(savefour,0,13);
        gp.setConstraints(pb,0,15);

        gp.getChildren().add(xLabel);
        gp.getChildren().add(xSlider);
        gp.getChildren().add(xValue);
        gp.getChildren().add(yLabel);
        gp.getChildren().add(yValue);
        gp.getChildren().add(ySlider);
        gp.getChildren().add(clickLabel);
        gp.getChildren().add(clickValue);
        gp.getChildren().add(startButton);
        gp.getChildren().add(pb);
        gp.getChildren().add(saveone);
        gp.getChildren().add(savetwo);
        gp.getChildren().add(savethree);
        gp.getChildren().add(savefour);
        gp.setPadding(new Insets(10,10,10,10));
        gp.setVgap(10);
        gp.setHgap(10);

        Scene scene = new Scene(gp,200,490) ;
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();} catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void clicker(int x, int y, int quantity, ProgressBar pb) throws IOException, AWTException {

        int tick = (int) Math.ceil(quantity/100);
        int progress = 0;
        Robot robot = new Robot();
        robot.mouseMove(x, y);
        robot.delay(500);

        for (int i = 0; i < quantity; i++) {

            if (i % tick < 1){

                progress++;
                pb.setProgress(progress/100);

            }
            robot.mousePress(InputEvent.BUTTON1_MASK);//Linksklick (Alles auswählen)
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(1);

        }
        return;
    }
}