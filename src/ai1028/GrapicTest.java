package ai1028;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;

public class GrapicTest extends JFrame {
    public GrapicTest(){
        add("Center",new GrapicPanel());
        SetFrame.setFrame(this,"그리기 연습",500,500);
    }

    public static void main(String[] args) {
        new GrapicTest();
    }
}
