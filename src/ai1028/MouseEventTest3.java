package ai1028;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventTest3 extends JFrame {
    JLabel lbl = new JLabel();

    public MouseEventTest3() {
        JPanel panel = new JPanel();
        String[] btnText = {"오토", "랏코", "하치와레"};

        for (int i = 0; i < btnText.length; i++) {
            JButton button = new JButton(btnText[i]);
            button.addMouseListener(btnmouseListener);
            panel.add(button);
        }

        add("North",panel);
        add("Center",lbl);
        lbl.setOpaque(true);
        lbl.setBackground(Color.WHITE);


        SetFrame.setFrame(this, "버튼에 따른 이미지변경",300,300);
    }

MouseListener btnmouseListener = new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        ImageIcon icon = null;

        switch(button.getText()){
            case "오토":
                icon = new ImageIcon("./imgs/img0.jpg");
                break;
            case "랏코":
                icon = new ImageIcon("./imgs/img1.jpg");
                break;
            case "하치와레":
                icon = new ImageIcon("./imgs/img2.jpg");
                break;
        }

        lbl.setIcon(icon);
    }
};

    public static void main(String[] args) {
        new MouseEventTest3();
    }
}
