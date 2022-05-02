package com.javafx.javafxstudynew.demotype1.demo1;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 * @author FRUIT
 */
public class ClickMe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Button btn;

    @Override
    public void start(Stage primaryStage) {
        // 创建按钮
        btn = new Button();
        btn.setText("Click me please!");
        btn.setOnAction(e -> buttonClick());

        // 将按钮添加到布局面板中
        BorderPane pane = new BorderPane();
        pane.setCenter(btn);

        // 将布局面板添加到场景中
        Scene scene = new Scene(pane, 300, 250);

        // 完成并展示舞台

        primaryStage.setScene(scene);
        primaryStage.setTitle("The Click Me App");
        primaryStage.show();
    }

    public void buttonClick() {
        if (btn.getText() == "Click me please!") {
            btn.setText("You clicked me!");
        } else {
            btn.setText("Click me please!");
        }
    }
}
