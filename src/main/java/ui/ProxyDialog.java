package ui;

import query.Inter;
import query.Proxy;
import utils.RegUtil;

import javax.print.attribute.standard.NumberUp;
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

    public static boolean isClickOk = false;

    public ProxyDialog() {
        setTitle("添加代理");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSetProxyOK);
        onOpen();

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
    }

    /**
     * 点击配置代理的Ok，执行：获取数据、检查合法、setProxy
     */
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
        isClickOk = true;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * 打开接口配置Dialog时执行的操作，设定文本框当前值
     */
    private void onOpen() {
        if (Proxy.getProxy.ip() != null) {
            textAreaProxyIp.setText(Proxy.getProxy.ip());
            textAreaProxyPort.setText(String.valueOf(Proxy.getProxy.port()));
        }
    }

    /**
     * 获取组合好的proxy
     *
     * @return proxy
     */
    public String getProxy() {
        return proxy;
    }
}
