package ai1013;

import center.CenterFrame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FlowLayoutTest extends JFrame {

    ArrayList<JButton> list = new ArrayList<>(5);
    Boolean flag = true;
    Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN};

    public FlowLayoutTest() {
        setTitle("FlowLayout 연습");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));

        for (int i = 0; i < 5; i++) {
            JButton btn = new JButton("버튼" + (i + 1));
            list.add(btn);
            btn.addActionListener(actionListener);
            add(btn);
        }

        int w = 250, h = 250;
        Point point = CenterFrame2.getLocation(w, h);
        setBounds(point.x, point.y, w, h);

        setVisible(true);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton selectedbtn = (JButton) e.getSource();

            if (flag) {
                selectedbtn.setBackground(colors[list.indexOf(selectedbtn)]);
                flag = false;
            } else {
                selectedbtn.setBackground(Color.LIGHT_GRAY);
                flag = true;
            }
        }
    };

    public static void main(String[] args) {
        new FlowLayoutTest();
    }
}