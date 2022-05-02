package com.javafx.javafxstudynew.demotype4.demo11;

import com.javafx.javafxstudynew.demotype3.demo9.MessageBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author FRUIT
 */
public class RolePlayer extends Application {
    TextField txtCharacter;
    TextField txtActor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 创建  用户Character的名字  文本标签
        Label lblCharacter = new Label("Character's Name:");
        lblCharacter.setMinWidth(100);
        lblCharacter.setAlignment(Pos.BOTTOM_RIGHT);

        // 创建  用户Character的名字 字符文本字段（录入）
        txtCharacter = new TextField();
        txtCharacter.setMinWidth(200);
        txtCharacter.setMaxWidth(200);
        txtCharacter.setPromptText(
                "此处输入Character的名字");

        // 创建  用户Actor的名字  文本标签
        Label lblActor = new Label("Actor's Name:");
        lblActor.setMinWidth(100);
        lblActor.setAlignment(Pos.BOTTOM_RIGHT);

        // 创建  用户Actor的名字 字符文本字段（录入）
        txtActor = new TextField();
        txtActor.setMinWidth(200);
        txtActor.setMaxWidth(200);
        txtActor.setPromptText("此处输入用户Actor的名字");

        // 创建确定按钮
        Button btnOK = new Button("OK");
        btnOK.setMinWidth(75);
        btnOK.setOnAction(e -> btnOK_Click());

        // 创建Character窗格
        HBox paneCharacter = new HBox(20, lblCharacter, txtCharacter);
        paneCharacter.setPadding(new Insets(10));

        // 创建Actor窗格
        HBox paneActor = new HBox(20, lblActor, txtActor);
        paneActor.setPadding(new Insets(10));

        // 创建按钮窗格
        HBox paneButton = new HBox(20, btnOK);
        paneButton.setPadding(new Insets(10));
        paneButton.setAlignment(Pos.BOTTOM_RIGHT);

        // 将 Character、Actor 和 Button 窗格添加到 VBox
        VBox pane = new VBox(10, paneCharacter, paneActor, paneButton);

        // 设置舞台
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Role Player");
        primaryStage.show();
    }

    public void btnOK_Click() {
        String errorMessage = "";

        if (txtCharacter.getText().length() == 0) {
            errorMessage += "\nCharacter是必输字段.";
        }

        if (txtActor.getText().length() == 0) {
            errorMessage += "\nActor是必输字段.";
        }

        if (errorMessage.length() == 0) {
            String message = "The role of "
                    + txtCharacter.getText()
                    + " will be played by "
                    + txtActor.getText()
                    + ".";
            MessageBox.show(message, "Cast");
        } else {
            MessageBox.show(errorMessage, "Missing Data");
        }
    }
}