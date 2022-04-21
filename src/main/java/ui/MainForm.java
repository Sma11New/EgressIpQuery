package ui;

import query.Inter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static query.Query.*;

public class MainForm {
    private JPanel root;
    private JButton ConfigInterButton;
    private JButton startButton;
    private JButton ConfigProxyButton;
    private JTextArea viewMsgTextArea;

    public MainForm() {

        ConfigInterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InterDialog().setVisible(true);
            }
        });

        ConfigProxyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProxyDialog proxyDialog = new ProxyDialog();
                proxyDialog.setVisible(true);
                String proxy = proxyDialog.getProxy();
                viewMsgTextArea.append("使用代理：" + proxy + "\n");
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // viewMsgTextArea.append(data.toString() + "\n");
                List<String> interList = Inter.getInterList();
                for (String inter: interList
                     ) {
                    viewMsgTextArea.append(inter + " -- " + doQuery(inter) + "\n");
                }
                // doQuery(viewMsgTextArea);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("出口IP查询 By 小新");
        // JFrame frame = new JFrame("Egress IP Query");
        frame.setContentPane(new MainForm().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}