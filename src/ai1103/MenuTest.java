package ai1103;

import center.SetFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MenuTest extends JFrame {
    JTextArea textArea = new JTextArea();
    JFileChooser fileChooser = new JFileChooser();
    JFrame frame;
    String[] btnIconFileNames = {"fileicon","openFile","exiticon","white","blue","magenta","font_large", "font_medium", "font_small"};
    JButton[] btns = new JButton[9];
    Color[] colorNames = {Color.WHITE,Color.BLUE,Color.MAGENTA};

    public MenuTest() {
        frame = this;
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        JToolBar toolBar = new JToolBar();
        add("North",toolBar);

        for(int i = 0; i < btns.length; i++) {
            btns[i] = new JButton(new ImageIcon("imgs/" + btnIconFileNames[i] + ".png"));
            btns[i].addActionListener(btnListener);
            toolBar.add(btns[i]);
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        JMenuItem newMenuItem = new JMenuItem("new");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem closeMenuItem = new JMenuItem("Close");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(closeMenuItem);

        newMenuItem.addActionListener(fileListener);
        openMenuItem.addActionListener(fileListener);
        closeMenuItem.addActionListener(fileListener);

        JMenuItem whiteMenuItem = new JMenuItem("White");
        JMenuItem blueMenuItem = new JMenuItem("Blue");
        JMenuItem magentaMenuItem = new JMenuItem("Magenta");
        JMenuItem SizeBigMenuItem = new JMenuItem("SizeBig");               //글자크기바꾸기~
        JMenuItem SizeMiddleMenuItem = new JMenuItem("SizeMiddle");
        JMenuItem SizeSmallMenuItem = new JMenuItem("SizeSmall");
        editMenu.add(whiteMenuItem);
        editMenu.add(blueMenuItem);
        editMenu.add(magentaMenuItem);
        editMenu.add(SizeBigMenuItem);
        editMenu.add(SizeMiddleMenuItem);
        editMenu.add(SizeSmallMenuItem);

        whiteMenuItem.addActionListener(backgroundListener);
        blueMenuItem.addActionListener(backgroundListener);
        magentaMenuItem.addActionListener(backgroundListener);
        SizeBigMenuItem.addActionListener(SizeListener);
        SizeMiddleMenuItem.addActionListener(SizeListener);
        SizeSmallMenuItem.addActionListener(SizeListener);


        SetFrame.setFrame(this, "메뉴만들기", 500, 500);
    }

    ActionListener SizeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "SizeBig":
                    textArea.setFont(textArea.getFont().deriveFont(20f));
                    break;
                case "SizeMiddle":
                    textArea.setFont(textArea.getFont().deriveFont(16f));
                    break;
                case "SizeSmall":
                    textArea.setFont(textArea.getFont().deriveFont(12f));
                    break;
            }
        }
    };



    ActionListener fileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile;
            switch (e.getActionCommand()) {
                case "New":
                    textArea.setText("");
                    break;
                case "Open":
                    fileChooser.showOpenDialog(frame);
                    selectedFile = fileChooser.getSelectedFile();
                    textArea.setText(readFile(selectedFile));
                    break;
                case "Close":
                    System.exit(0);
                    break;
            }
        }
    };

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile;
            JButton eBtn = (JButton)e.getSource();
            if(eBtn == btns[0]){
                textArea.setText("");
            }else if(eBtn == btns[1]){
                fileChooser.showOpenDialog(frame);
                selectedFile = fileChooser.getSelectedFile();
                textArea.setText(readFile(selectedFile));
            }else if(eBtn == btns[2]){
                System.exit(0);
            }

            for (int i = 0; i < colorNames.length; i++) {
                if(eBtn == btns[i+3]){
                    textArea.setBackground(colorNames[i]);
                }
            }

            if(eBtn == btns[6]){
                textArea.setFont(textArea.getFont().deriveFont(20f));
            }
            else if(eBtn == btns[7]){
                textArea.setFont(textArea.getFont().deriveFont(16f));
            }else if(eBtn == btns[8]){
                textArea.setFont(textArea.getFont().deriveFont(12f));
            }
        }
    };


    ActionListener backgroundListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = null;
            switch (e.getActionCommand()) {
                case "white":
                    color = Color.WHITE;
                    break;
                case "Blue":
                    color = Color.BLUE;
                    break;
                case "Magenta":
                    color = Color.MAGENTA;
                    break;
            }
            textArea.setBackground(color);
        }
    };

    public String readFile(File selectedFile) {
        String totalLines = "";
        try {
            String line = "";
            FileReader fr = new FileReader(selectedFile);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                totalLines += line;
            }
        } catch (FileNotFoundException e) {
            textArea.setText("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return totalLines;
    }

    public static void main(String[] args) {
        new MenuTest();
    }
}