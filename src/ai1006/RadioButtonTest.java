package ai1006;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonTest extends JFrame {
    ImageIcon[] imageIcon;
    int i;
    JLabel lbl;
    JRadioButton[] radios;

    public RadioButtonTest() {
        setTitle("라디오버튼 테스트");   //super("프레임 제목");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();

        JPanel panNorth = new JPanel();//작은 박스
        String[] entertainers = {"오토 레이니", "랏코", "하치와레", "미쿠"};
        radios = new JRadioButton[entertainers.length];
        imageIcon = new ImageIcon[entertainers.length];
        lbl = new JLabel();
        ButtonGroup group = new ButtonGroup();
        i = 0;
        for (String entertainer : entertainers) {
            imageIcon[i] = new ImageIcon("imgs/img" + i + ".jpg");
            radios[i] = new JRadioButton(entertainer);
            group.add(radios[i]);
            radios[i].addItemListener(radioListener);
            panNorth.add(radios[i++], BorderLayout.NORTH);
        }

        lbl.setIcon(imageIcon[0]);
        add("North", panNorth);
        add(lbl);

        int w = 500, h = 500;
        int[] location = CenterFrame.getLocation(w, h);
        setBounds(location[0], location[1], w, h); //setlocation(x, y) + setSize(w, h)

        setVisible(true);
    }

    public static void main(String[] args) {
        new RadioButtonTest();
    }

    ItemListener radioListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            JRadioButton selectedRadio = (JRadioButton) e.getSource();
            if (selectedRadio == radios[0])
                lbl.setIcon(imageIcon[0]);
            else if (selectedRadio == radios[1])
                lbl.setIcon(imageIcon[1]);
            else if (selectedRadio == radios[2])
                lbl.setIcon(imageIcon[2]);
            else lbl.setIcon(imageIcon[3]);
        }
    };

}
