package final_project.view;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

public class ProductionRegisterPane extends JPanel {
    private final JTextField id = new JTextField(20);
    private final JTextField date = new JTextField(20);
    private final JTextField target = new JTextField(20);
    private final JTextField actual = new JTextField(20);
    private final JButton registerButton = new JButton("생산 등록");

    public ProductionRegisterPane() {
        super(new FlowLayout(FlowLayout.CENTER, 16, 24));
        JPanel form = new JPanel(new GridLayout(0, 2, 12, 12));
        form.add(new JLabel("생산번호"));
        form.add(id);
        form.add(new JLabel("생산일 (연-월-일)"));
        form.add(date);
        form.add(new JLabel("목표 수량"));
        form.add(target);
        form.add(new JLabel("실제 생산 수량"));
        form.add(actual);
        form.add(new JLabel());
        form.add(registerButton);
        add(form);
        clearForm();
    }

    public void onRegister(Runnable action) {
        registerButton.addActionListener(event -> action.run());
    }

    public String productionId() {
        return id.getText().strip();
    }

    public String productionDate() {
        return date.getText().strip();
    }

    public String targetQuantity() {
        return target.getText();
    }

    public String actualQuantity() {
        return actual.getText();
    }

    public void clearForm() {
        id.setText("");
        date.setText(LocalDate.now().toString());
        target.setText("");
        actual.setText("0");
    }
}
