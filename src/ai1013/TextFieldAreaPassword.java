package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;

public class TextFieldAreaPassword extends JFrame {
    public TextFieldAreaPassword() {
        setTitle("문자열 입력 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

//        한줄입력
        JTextField textField = new JTextField("", 20);
//        여러줄 입력
        JTextArea textArea = new JTextArea("",5, 20);
//        비밀번호 입력
        JPasswordField passwordField = new JPasswordField(20);

        add(textField);
        add(textArea);
        add(passwordField);

        int w = 250, h = 250;
        int[] location = CenterFrame.getLocation(w,h);
        setBounds(location[0],location[1],w,h); //setLocation(w,y) =+ setSize(w, h)
        setVisible(true);

    }

    public static void main(String[] args) {
        new TextFieldAreaPassword();
    }
}
