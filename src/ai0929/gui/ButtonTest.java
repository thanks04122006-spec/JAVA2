package ai0929.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTest extends JFrame {
    public ButtonTest(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSizeDim =  toolkit.getScreenSize();
        int sw = screenSizeDim.width;
        int sh = screenSizeDim.height;
        int w = 500;
        int h = 200;

        int x = (sw-w)/2;
        int y = (sh-h)/2;

        setLayout(new FlowLayout());
        setTitle("버튼 컴퍼넌트");
        setSize(w,h);
        setLocation(x,y);

        JButton btn = new JButton("메세지 대화상자 보이기");

        btn.addActionListener(new ButtonActionListener());

        add(btn);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class ButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "메세지박스입니다");
        }
    }

    public static void main(String[] args){
        new ButtonTest();
    }
}
