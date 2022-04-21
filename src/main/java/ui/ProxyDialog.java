package ui;

import query.Proxy;
import utils.RegUtil;

import javax.swing.*;
import java.awt.event.*;

public class ProxyDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonSetProxyOK;
    private JButton buttonSetProxyCancel;
    private JComboBox comboBoxProxy;
    private JTextField textAreaProxyIp;
    private JTextField textAreaProxyPort;
    private String proxy;

    public ProxyDialog() {
        setTitle("添加代理");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSetProxyOK);

        // buttonSetProxyOK.addActionListener(e -> onOK());
        buttonSetProxyOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonSetProxyCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        textAreaProxyIp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("111111");
            }
        });
    }

    public String getProxy() {
        return proxy;
    }

    private void onOK() {
        String proxyIp = textAreaProxyIp.getText();
        String proxyPort = textAreaProxyPort.getText();
        String proxyType = (String) comboBoxProxy.getSelectedItem();
        // 空 -> null
        if (proxyIp.equals("") && proxyPort.equals("")) {
            proxy = null;
            Proxy.setProxy(null, null, 0);
            dispose();
        } else if (!RegUtil.isIp(proxyIp)) {
            JOptionPane.showMessageDialog(contentPane, "IP错误");
        } else if (!RegUtil.isPort(proxyPort)) {
            JOptionPane.showMessageDialog(contentPane, "端口错误");
        } else {
            proxy = proxyType + "://" + proxyIp + ":" + proxyPort;
            Proxy.setProxy(proxyType, proxyIp, Integer.valueOf(proxyPort));
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ProxyDialog dialog = new ProxyDialog();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setTitle("查询接口编辑");
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        System.exit(0);
    }
}
