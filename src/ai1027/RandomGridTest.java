package ai1027;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class RandomGridTest extends JFrame {
    ArrayList<JButton> list = new ArrayList<>();

    Color[] colors = new Color[]{
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN,
            Color.MAGENTA, Color.ORANGE, Color.PINK, Color.LIGHT_GRAY, Color.WHITE
    };

    public RandomGridTest() {
        Random rand = new Random();
        int rows = rand.nextInt(10)+1;
        int cols = rand.nextInt(10)+1;


        setLayout(new GridLayout(rows,cols,2,2));

        for (int i = 0; i < rows * cols; i++) {
            int num = rand.nextInt(10) ;

            JButton btn = new JButton("버튼" + (num+1));
            btn.setBackground(colors[num]);
            btn.setOpaque(true);

            list.add(btn);
            add(btn);
        }

        SetFrame.setFrame(this,"GridLayout 연습",350,350);
    }

    public static void main(String[] args) {
        new RandomGridTest();
    }
}
