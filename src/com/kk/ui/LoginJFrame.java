package com.kk.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
    // 登录功能模块
    public LoginJFrame() {
        // 创建登录界面的时候，同时给这个界面去设置一些信息
        this.setSize(488,430);
        // 设置页面标题
        this.setTitle("拼图登录");
        // 设置页面置顶
        this.setAlwaysOnTop(true);
        // 设置页面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setVisible(true);


    }
}
