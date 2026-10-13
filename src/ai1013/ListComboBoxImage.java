package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListComboBoxImage extends JFrame {

    private JLabel lbl;

    public ListComboBoxImage() {
        setTitle("문자열 입력 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] items = {"강아지", "고양이", "토끼", "미어캣", "코알라"};

        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.addItemListener(comboListener);

        lbl = new JLabel(new ImageIcon("pets/pet1.jpg"));
        lbl.setHorizontalAlignment(JLabel.CENTER);

        add(comboBox, BorderLayout.NORTH);
        add(lbl, BorderLayout.CENTER);

        int w = 450, h = 450;
        int[] location = CenterFrame.getLocation(w, h);
        setBounds(location[0], location[1], w, h);

        setVisible(true);
    }

    ItemListener comboListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {

                int imgPath = 1;
                String selectedItem = (String) e.getItem();

                switch (selectedItem) {
                    case "강아지":
                        imgPath = 1;
                        break;

                    case "고양이":
                        imgPath = 2;
                        break;

                    case "토끼":
                        imgPath = 3;
                        break;

                    case "미어캣":
                        imgPath = 4;
                        break;

                    case "코알라":
                        imgPath = 5;
                        break;
                }

                lbl.setIcon(new ImageIcon("pets/pet" + imgPath + ".jpg"));
            }
        }
    };

    public static void main(String[] args) {
        new ListComboBoxImage();
    }
}