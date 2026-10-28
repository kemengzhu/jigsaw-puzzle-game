package com.kk.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {

    // 注册功能模块

    public RegisterJFrame() {
        this.setSize(488,500);
        // 设置页面标题
        this.setTitle("拼图注册");
        // 设置页面置顶
        this.setAlwaysOnTop(true);
        // 设置页面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setVisible(true);
    }
}
