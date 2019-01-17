package utm.db.dbadministrator.frames;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class AboutWindow extends JInternalFrame {

    AboutWindow() {
        setTitle("About");
        setLocation(new Random().nextInt(140) + 50, new Random().nextInt(150) + 50);
        setSize(320, 400);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JTextArea jTextArea1 = new JTextArea(11, 21);
        jTextArea1.setText("Aplicatia a fost dezvoltata de George Marin pentru poriectul la Proiectarea Interfetelor Grafice. An III ID, Universitatea Titu Maiorescu");
        jTextArea1.setEditable(false);
        jTextArea1.setEnabled(false);

        getContentPane().setLayout(new FlowLayout());
        JLabel jLabelHeader = new JLabel("");
        getContentPane().add(jLabelHeader);
        getContentPane().add(new JScrollPane(jTextArea1));
        JButton jButtonOk = new JButton("ok");
        getContentPane().add(jButtonOk);

        jButtonOk.addActionListener(ev -> setVisible(false));
        setVisible(false);
    }
}