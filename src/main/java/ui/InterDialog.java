package ui;

import query.Inter;
import utils.*;

import javax.swing.*;
import java.awt.event.*;

public class InterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonRestore;
    private JTextArea textAreaInter;

    public InterDialog() {
        setTitle("查询接口编辑");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        onOpen();

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonRestore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRestore();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * 确定按钮，自定义查询接口、判断合法性
     */
    private void onOK() {
        boolean isLegal = true;
        // 接口是否有变化？ 是：修改(+写配置文件) 否：跳过
        if (!textAreaInter.getText().equals(TypeTransUtil.listToString(Inter.getInterList()))) {
            for (String inter : TypeTransUtil.stringToList(textAreaInter.getText())) {
                // 校验合法URL
                if (!RegUtil.isURL(inter)) {
                    JOptionPane.showMessageDialog(contentPane, "URL错误: " + inter);
                    isLegal = false;
                }
            }
            if (isLegal) {
                Inter.setInterList(textAreaInter.getText());
            }
        }
        // 这里的dispose()不写在上一个if内，是因为要处理合法且与默认相同的数据，不会触发进入if(!textAreaInter……）情况
        if (isLegal) {
            dispose();
        }
    }

    /**
     * 恢复按钮，恢复接口到初始状态
     */
    private void onRestore() {
        // 置空
        textAreaInter.setText("");
        // 恢复
        textAreaInter.append(TypeTransUtil.listToString(Inter.getDefaultInterList()));
    }

    /**
     * 打开接口配置Dialog时执行的操作
     */
    private void onOpen() {
        for (String inter : Inter.getInterList()) {
            textAreaInter.append(inter + "\n");
        }
    }
}
