package ui;

import query.Inter;
import query.Query;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm {
    private JPanel root;
    private JButton ConfigInterButton;
    private JButton startButton;
    private JButton ConfigProxyButton;
    private JTextArea viewMsgTextArea;

    public MainForm() {
        /**
         * 开始查询，查询出口IP、IP归属
         */
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 查询出口IP
                List<String> interList = Inter.getInterList();
                viewMsgTextArea.append("------\n");
                for (String inter : interList) {
                    String egressIp = Query.doQueryIp(inter);
                    viewMsgTextArea.append("  " + egressIp + " -- " + inter + "\n");
                    Inter.setEgressIpList(egressIp);
                }
                // 查询IP归属
                viewMsgTextArea.append(" ------\n");
                for (String egressIp : Inter.getEgressIpList()) {
                    viewMsgTextArea.append("   " + egressIp + " -- " + Query.doQueryAddr(egressIp) + "\n");
                }
                Inter.setEgressIpListNull();
            }
        });

        /**
         * 配置接口
         */
        ConfigInterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InterDialog().setVisible(true);
            }
        });

        /**
         * 配置代理
         */
        ConfigProxyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProxyDialog proxyDialog = new ProxyDialog();
                proxyDialog.setVisible(true);
                if (ProxyDialog.isClickOk) {
                    ProxyDialog.isClickOk = false;
                    String proxy = proxyDialog.getProxy();
                    viewMsgTextArea.append("使用代理：" + proxy + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("出口IP查询_v0.1 By 小新");
        frame.setContentPane(new MainForm().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}