package com.sher.cal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.Vector;


public class Calculator {
    String result = "";

    // 操作
    String str1 = "0";
    String str2 = "0";
    String signal = "+";

    // k1:输入方向
    int k1 = 1;
    // k2:符号数量
    int k2 = 1;
    // k3:str1是否能被清零
    int k3 = 1;
    // k4:str2是否能被清零
    int k4 = 1;
    // k5:小数点是否被录入
    int k5 = 1;

    JButton store;

    Vector<JButton> vt = new Vector<JButton>(20,10);

    // 创建一个JFrame，主窗体
    JFrame frame = new JFrame("计算器");

    // 可以显示 20 列的文本内容
    JTextField result_TextField = new JTextField(result, 20);

    // 清除按钮
    JButton clear_Button = new JButton("clear");

    // 数字键0到9
    JButton button0 = new JButton("0");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");

    // 命令按钮
    JButton button_dot = new JButton(".");
    JButton button_add = new JButton("+");
    JButton button_minus = new JButton("-");
    JButton button_mul = new JButton("*");
    JButton button_div = new JButton("/");

    // 计算按钮
    JButton button_res = new JButton("=");

    public Calculator() {
        // 文本右对齐
        result_TextField.setHorizontalAlignment(JTextField.RIGHT);

        // 将组件加到容器内
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(4,4,5,5));
        pan.add(button7);
        pan.add(button8);
        pan.add(button9);
        pan.add(button_div);
        pan.add(button4);
        pan.add(button5);
        pan.add(button6);
        pan.add(button_mul);
        pan.add(button1);
        pan.add(button2);
        pan.add(button3);
        pan.add(button_minus);
        pan.add(button0);
        pan.add(button_dot);
        pan.add(button_res);
        pan.add(button_add);

        pan.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JPanel pan2 = new JPanel();
        pan2.setLayout(new BorderLayout());
        pan2.add(result_TextField, BorderLayout.WEST);
        pan2.add(clear_Button, BorderLayout.EAST);

        // 设置窗口出现的位置
        frame.setLocation(300,200);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(pan, BorderLayout.NORTH);
        frame.getContentPane().add(pan2, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        // 事件处理
        class Listener implements ActionListener {
            // 数字触发下时触发
            public void actionPerformed(ActionEvent e) {
                String ss = ((JButton)e.getSource()).getText();
                store = (JButton)e.getSource();
                vt.add(store);
                if (k1 == 1) {
                    if (k1 == 1) {
                        str1 = "";
                        k5 = 1;
                    }
                    str1 += ss;
                    k3 += 1;
                    // 显示对应数字
                    result_TextField.setText(str1);
                } else if (k1 == 2) {
                    if (k4 == 1) {
                        str2 = "";
                        k5 = 1;
                    }
                    str2 += ss;
                    k4 += 1;
                    result_TextField.setText(str2);
                }
            }
        }

        class Listener_signal implements ActionListener {
            // 运算符
            public void actionPerformed(ActionEvent e) {
                String ss2 = ((JButton)e.getSource()).getText();
                store = (JButton)e.getSource();
                vt.add(store);
                if (k2 == 1) {
                    k1 = 2;
                    k5 = 1;
                    signal = ss2;
                    k2 += 1;
                } else {
                    int a = vt.size();
                    JButton c = (JButton)vt.get(a - 2);
                    if (!(c.getText().equals("+")) && !(c.getText().equals("-"))
                            && !(c.getText().equals("/")) &&  !(c.getText().equals("*"))) {
                        cal();
                        str1 = result;
                        k1 = 2;
                        k5 = 1;
                        k4 = 1;
                        signal = ss2;
                    }
                    k2 += 1;
                }
            }
        }

        // 清空
        class Listener_clear implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                store = (JButton)e.getSource();
                vt.add(store);
                k2=k3=k4=k5=1;
                str1 = "0";
                str2 = "0";
                signal = "";
                result = "";
                result_TextField.setText(result);
                vt.clear();
            }
        }

        // 结果
        class Listener_res implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                store = (JButton)e.getSource();
                vt.add(store);
                cal();
                k1=k2=k3=k4=1;
                str1 = result;
            }
        }

        // 小数点
        class Listener_dots implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                store = (JButton)e.getSource();
                vt.add(store);
                if (k5 == 1) {
                    String ss2 = ((JButton) e.getSource()).getText();
                    if (k1 == 1) {
                        if (k3 == 1) {
                            str1 = "";
                            k5 = 1;
                        }

                        str1 += ss2;
                        k3 += 1;
                        result_TextField.setText(str1);
                    } else if (k1 == 2) {
                        if(k4 == 1) {
                            str2 = "";
                            k5 = 1;
                        }
                        str2 += ss2;
                        k4 += 1;
                        result_TextField.setText(str2);
                    }
                }
                k5 += 1;
            }
        }

        // 把各个响应绑定在各组件上
        Listener_res l_res = new Listener_res();
        Listener l = new Listener();
        Listener_signal l_signal = new Listener_signal();
        Listener_clear l_clear = new Listener_clear();
        Listener_dots l_dots = new Listener_dots();

        button7.addActionListener(l);
        button8.addActionListener(l);
        button9.addActionListener(l);
        button_div.addActionListener(l_signal);
        button4.addActionListener(l);
        button5.addActionListener(l);
        button6.addActionListener(l);
        button_mul.addActionListener(l_signal);
        button1.addActionListener(l);
        button2.addActionListener(l);
        button3.addActionListener(l);
        button_minus.addActionListener(l_signal);
        button0.addActionListener(l);
        button_dot.addActionListener(l_dots);
        button_res.addActionListener(l_res);
        button_add.addActionListener(l_signal);
        clear_Button.addActionListener(l_clear);

        frame.addWindowFocusListener(new WindowAdapter() {
            // @Override
            public void windowOpened(WindowEvent e) {
                System.exit(0);
                // super.windowOpened(e);
            }
        });

    }

    public void cal() {
        // 运算逻辑
        double a2;
        double b2;
        String c = signal;
        double result2 = 0;
        if (c.equals("")) {
            result_TextField.setText("输入运算符");
        } else {
            if (str1.equals("."))
                str1 = "0.0";
            if (str2.equals("."))
                str2 = "0.0";

            a2 = Double.valueOf(str1).doubleValue();
            b2 = Double.valueOf(str2).doubleValue();

            if (c.equals("+"))
                result2 = a2 + b2;

            if (c.equals("-"))
                result2 = a2 - b2;

            if (c.equals("*")) {
                BigDecimal m1 = new BigDecimal(Double.toString(a2));
                BigDecimal m2 = new BigDecimal(Double.toString(b2));
                result2 = m1.multiply(m2).doubleValue();
            }

            if (c.equals("/")) {
                if (b2 == 0) {
                    result2 = 0;
                } else {
                    result2 = a2 / b2;
                }
            }

            result = ((new Double(result2)).toString());
            result_TextField.setText(result);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calculator cal = new Calculator();
    }
}
