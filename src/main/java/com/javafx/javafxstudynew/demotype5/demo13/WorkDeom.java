package com.javafx.javafxstudynew.demotype5.demo13;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author FRUIT
 */
public class WorkDeom extends Application {

    public Node topNode;
    public Node rightNode;
    public Node bottomNode;
    public Node leftNode;
    public Node centNode;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageMain) throws Exception {
        //设置顶部得基础菜单
        setTopMenuBarMain();








        // ---------- 完成场景 ----------
        //设置主要布局信息
        //设置边框布局
        BorderPane paneMain = new BorderPane();
        // 顶部布局
        paneMain.setTop(topNode);
        // 右侧布局
        paneMain.setRight(rightNode);
        // 底部布局
        paneMain.setBottom(bottomNode);
        // 中心布局
        paneMain.setCenter(leftNode);
        // 左侧布局
        paneMain.setLeft(centNode);


        // 创建主场景
        Scene sceneMain = new Scene(paneMain);
        //主舞台
        stageMain.setScene(sceneMain);
        stageMain.setTitle("主窗口");
        stageMain.setHeight(500);
        stageMain.setWidth(500);
        stageMain.show();
    }

    /**
     * @Description: 设置舞台得基础菜单
     * @Author: YX-WJ
     * @Date: 2022/5/3 0:20

     * @return: javafx.scene.control.MenuBar
     **/
    public void setTopMenuBarMain(){
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,editMenu);
        this.topNode = menuBar;

    }
}
