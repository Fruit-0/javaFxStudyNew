package com.javafx.javafxstudynew.demotype4.demo12;

import com.javafx.javafxstudynew.demotype3.demo9.MessageBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author FRUIT
 */
public class PizzaOrder extends Application {

    Stage stage;
    TextField txtName;

    /**
     * 客户姓名、电话和地址字段
     */
    TextField txtPhone;
    TextField txtAddress;
    RadioButton rdoSmall;

    /**
     * 大小单选按钮
     */
    RadioButton rdoMedium;
    RadioButton rdoLarge;
    RadioButton rdoThin;

    /**
     * 外壳样式单选按钮
     */
    RadioButton rdoThick;
    CheckBox chkPepperoni;

    /**
     * 顶部单选按钮
     */
    CheckBox chkSausage;
    CheckBox chkLingua;
    CheckBox chkOlives;
    CheckBox chkMushrooms;
    CheckBox chkTomatoes;
    CheckBox chkAnchovies;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        // ----- 创建顶部窗格 -----

        Text textHeading = new Text("立即订购您的披萨!");
        textHeading.setFont(new Font(20));
        HBox paneTop = new HBox(textHeading);
        paneTop.setPadding(new Insets(20, 10, 20, 10));

        // ---------- 创建客户窗格 ----------

        // 创建名称标签和文本字段

        Label lblName = new Label("Name:");
        lblName.setPrefWidth(100);
        txtName = new TextField();
        txtName.setPrefColumnCount(20);
        txtName.setPromptText("Enter the customer's name here");
        txtName.setMaxWidth(Double.MAX_VALUE);
        HBox paneName = new HBox(lblName, txtName);

        // 创建电话号码标签和文本字段

        Label lblPhone = new Label("Phone Number:");
        lblPhone.setPrefWidth(100);
        txtPhone = new TextField();
        txtPhone.setPrefColumnCount(20);
        txtPhone.setPromptText("Enter the customer's phone number here");
        HBox panePhone = new HBox(lblPhone, txtPhone);

        // 创建地址标签和文本字段

        Label lblAddress = new Label("Address:");
        lblAddress.setPrefWidth(100);
        txtAddress = new TextField();
        txtAddress.setPrefColumnCount(20);
        txtAddress.setPromptText("Enter the customer's address here");
        HBox paneAddress = new HBox(lblAddress, txtAddress);

        // 创建客户窗格

        VBox paneCustomer = new VBox(10, paneName, panePhone, paneAddress);

        // ---------- 创建订单窗格 ----------

        // 创建大小窗格

        Label lblSize = new Label("Size");
        rdoSmall = new RadioButton("Small");
        rdoMedium = new RadioButton("Medium");
        rdoLarge = new RadioButton("Large");
        rdoMedium.setSelected(true);
        ToggleGroup groupSize = new ToggleGroup();
        rdoSmall.setToggleGroup(groupSize);
        rdoMedium.setToggleGroup(groupSize);
        rdoLarge.setToggleGroup(groupSize);

        VBox paneSize = new VBox(lblSize, rdoSmall, rdoMedium, rdoLarge);
        paneSize.setSpacing(10);

        // 创建外壳窗格

        Label lblCrust = new Label("Crust");
        rdoThin = new RadioButton("Thin");
        rdoThick = new RadioButton("Thick");
        rdoThin.setSelected(true);
        ToggleGroup groupCrust = new ToggleGroup();
        rdoThin.setToggleGroup(groupCrust);
        rdoThick.setToggleGroup(groupCrust);

        VBox paneCrust = new VBox(lblCrust, rdoThin, rdoThick);
        paneCrust.setSpacing(10);

        // 创建浇头窗格

        Label lblToppings = new Label("Toppings");
        chkPepperoni = new CheckBox("Pepperoni");
        chkSausage = new CheckBox("Sausage");
        chkLingua = new CheckBox("Linguica");

        chkOlives = new CheckBox("Olives");
        chkMushrooms = new CheckBox("Mushrooms");
        chkTomatoes = new CheckBox("Tomatoes");
        chkAnchovies = new CheckBox("Anchovies");

        FlowPane paneToppings = new FlowPane(Orientation.VERTICAL, chkPepperoni, chkSausage, chkLingua, chkOlives, chkMushrooms, chkTomatoes, chkAnchovies);
        paneToppings.setPadding(new Insets(10, 0, 10, 0));
        paneToppings.setHgap(20);
        paneToppings.setVgap(10);
        paneToppings.setPrefWrapLength(150);

        VBox paneTopping = new VBox(lblToppings, paneToppings);

        // 将大小、外壳和配料窗格添加到订单窗格

        HBox paneOrder = new HBox(50, paneSize, paneCrust, paneTopping);

        // 创建中心窗格

        VBox paneCenter = new VBox(20, paneCustomer, paneOrder);
        paneCenter.setPadding(new Insets(0, 10, 0, 10));

        // ---------- 创建底部窗格 ----------

        Button btnOK = new Button("OK");
        btnOK.setPrefWidth(80);
        btnOK.setOnAction(e -> btnOKClick());

        Button btnCancel = new Button("Cancel");
        btnCancel.setPrefWidth(80);
        btnCancel.setOnAction(e -> btnCancelClick());

        Region spacer = new Region();

        HBox paneBottom = new HBox(10, spacer, btnOK, btnCancel);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 10, 20, 10));

        // ---------- 完成场景 ----------

        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);

        // 创建场景和舞台

        Scene scene = new Scene(paneMain);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Order");
        primaryStage.show();

    }

    public void btnOKClick() {

        // 使用客户信息创建消息字符串

        String msg = "Customer:\n\n";
        msg += "\t" + txtName.getText() + "\n";
        msg += "\t" + txtAddress.getText() + "\n";
        msg += "\t" + txtPhone.getText() + "\n\n";
        msg += "You have ordered a ";

        // Add the pizza size

        if (rdoSmall.isSelected()) {
            msg += "small ";
            if (rdoMedium.isSelected()) {
                msg += "medium ";
            }
        } else {
            if (rdoMedium.isSelected()) {
                msg += "medium ";
            }
        }
        if (rdoLarge.isSelected()) {
            msg += "large ";
            if (rdoThin.isSelected()) {
                msg += "thin crust pizza with ";
            }
        } else {
            if (rdoThin.isSelected()) {
                msg += "thin crust pizza with ";
            }
        }

        // Add the crust style

        if (rdoThick.isSelected()) {
            msg += "thick crust pizza with ";
        }

        // Add the toppings

        String toppings = "";
        toppings = buildToppings(chkPepperoni, toppings);
        toppings = buildToppings(chkSausage, toppings);
        toppings = buildToppings(chkLingua, toppings);
        toppings = buildToppings(chkOlives, toppings);
        toppings = buildToppings(chkTomatoes, toppings);
        toppings = buildToppings(chkMushrooms, toppings);
        toppings = buildToppings(chkAnchovies, toppings);

        if (toppings.equals("")) {
            msg += "no toppings.";
        } else {
            msg += "the following toppings:\n"
                    + toppings;
        }
        // Display the message
        MessageBox.show(msg, "Order Details");
    }

    public String buildToppings(CheckBox chk, String msg) {
        // 显示浇头列表的辅助方法
        if (chk.isSelected()) {
            if (!msg.equals("")) {
                msg += ", ";
            }
            msg += chk.getText();
        }
        return msg;
    }

    public void btnCancelClick() {
        stage.close();
    }
}