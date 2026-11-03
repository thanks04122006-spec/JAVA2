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
    Frame frame;

    public MenuTest() {
        frame = this;
        JScrollPane scrollPane = new JScrollPane(textArea,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(textArea);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu Filemenu = new JMenu("File");
        JMenu editmenu = new JMenu("Edit");
        menuBar.add(Filemenu);
        menuBar.add(editmenu);

        JMenuItem newMenuItem = new JMenuItem("New");//textArea의 문자열 초기화
        JMenuItem openMenuItem = new JMenuItem("Open"); //JFileChooser(showOpenDialog)
        JMenuItem closeMenuItem = new JMenuItem("Close");//system.EXit
        Filemenu.add(newMenuItem);
        Filemenu.add(openMenuItem);
        Filemenu.add(closeMenuItem);

        JMenuItem whiteMenuItem = new JMenuItem("white Color");
        JMenuItem blueMenuItem = new JMenuItem("blue Color");
        JMenuItem magentaMenuItem = new JMenuItem("Magenta Color");
        editmenu.add(whiteMenuItem);
        editmenu.add(blueMenuItem);
        editmenu.add(magentaMenuItem);

        whiteMenuItem.addActionListener(backListener);
        blueMenuItem.addActionListener(backListener);
        magentaMenuItem.addActionListener(backListener);

        newMenuItem.addActionListener(FileListener);
        openMenuItem.addActionListener(FileListener);
        closeMenuItem.addActionListener(FileListener);

        SetFrame.setFrame(this, "메뉴 만들기",500,500);

    }

    ActionListener FileListener = new ActionListener() {
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

    public String readFile(File selectedFile) {
        String lines = "";
        String totallines = "";
        try{
            FileReader fileReader = new FileReader(selectedFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((lines = bufferedReader.readLine()) != null) {
                totallines += lines +"\n";
            }
        }
        catch (FileNotFoundException e) {
            textArea.setText("File Not Found");
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return totallines;
    }

    ActionListener backListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = null;
            switch (e.getActionCommand()) {
                case "white Color":
                    color = Color.WHITE;
                    break;
                case "blue Color":
                    color = Color.BLUE;
                    break;
                case "Magenta Color":
                    color = Color.MAGENTA;
                    break;
            }
            textArea.setBackground(color);
        }
    };

    public static void main(String[] args) {
        new MenuTest();
    }
}
