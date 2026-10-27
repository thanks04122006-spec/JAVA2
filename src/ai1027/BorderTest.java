package ai1027;

import center.CenterFrame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BorderTest extends JFrame {

    ArrayList<JButton> list = new ArrayList<>(5);
//    Border Layout 위치 배열
    String[] positions = {
            "North",
            "South",
            "West",
            "East",
            "Center"
     };

    public BorderTest() {
        super("BorderLayout 연습");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int w = 250, h =250;

        setLayout(new BorderLayout(10, 10));

//Jframe의 5개의 영역에 버튼을 추가하시오
//        1. 버튼 객체 생성
//        2. JFrame의 각자 위치에 추가
//        각 버튼을 클릭하면 버튼의 라벨 문자열이 선택되었다는 메세지창을 보이시오
        int i = 1;

        for (String loc:positions) {
            JButton btn = new JButton("버튼" + i++);
            btn.addActionListener(btnListener);
            add(loc, btn);
            list.add(btn);
        }

        Point point = CenterFrame2.getLocation(w,h);
        setBounds(point.x,point.y,w,h);
        setVisible(true);
    }

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            JOptionPane.showMessageDialog(null, btn.getText() + "이(가) 선택 되었습니다");
        }
    };

    public static void main(String[] args) {
        new BorderTest();
    }
}
