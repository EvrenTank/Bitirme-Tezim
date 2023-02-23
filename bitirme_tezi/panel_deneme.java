package bitirme_tezi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panel_deneme extends JPanel implements ActionListener {
    public panel_deneme() {
        setLayout(null);
        setBackground(new Color(120,100,120));
        setSize(1000,1000);
        JButton button=new JButton(" Buton 1");
        button.setFocusPainted(false);
        button.setBounds(200,200,100,100);
        button.addActionListener(this);
        add(button);
    }
    public void elementleri_ekle(){

    }
    public static void main(String[] args) {
        panel_deneme panel=new panel_deneme();
        JFrame frame=new JFrame(" Deneme amaçlı yazılım");
        frame.setBackground(Color.red);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,800);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField field=new JTextField();
        field.setBounds(100,400,100,50);
        this.add(field);

    }
}


