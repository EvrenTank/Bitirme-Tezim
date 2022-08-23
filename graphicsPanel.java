package bitirme_tezi;

import javax.swing.*;

public class graphicsPanel extends JPanel {

    public graphicsPanel() {
        setLayout(null);
        liquid_names liquid_names = new liquid_names();
        String isimler[] = liquid_names.get_names();
        JComboBox<String> isim_listesi = new JComboBox<String>(isimler);
        this.setBounds(250,25,150,25);
        this.add(isim_listesi);

    }

    public static void main( String args[]) {
        graphicsPanel myPanel = new graphicsPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(myPanel);
        frame.setVisible(true);
    }
}
