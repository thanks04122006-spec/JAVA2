package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListComboBox extends JFrame {

    public ListComboBox() {
        setTitle("문자열 입력 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        String[] items = {"강아지","고양이", "토끼", "미어캣", "코알라"};
        JList list = new JList(items);
        JComboBox comboBox = new JComboBox(items);

        add(comboBox);
        add(list);

        int w = 450, h = 450;
        int[] location = CenterFrame.getLocation(w,h);
        setBounds(location[0],location[1],w,h); //setLocation(w,y) =+ setSize(w, h)
        setVisible(true);
    }


    public static void main(String[] args) {
        new ListComboBox();
    }
}
