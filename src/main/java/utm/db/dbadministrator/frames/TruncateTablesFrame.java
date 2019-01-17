package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.repositories.CreditsRepository;
import utm.db.dbadministrator.repositories.GradesRepository;
import utm.db.dbadministrator.repositories.StudentRepository;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Configurable
class TruncateTablesFrame extends JInternalFrame {

    @Autowired
    private GradesRepository gradesRepository;

    @Autowired
    private CreditsRepository creditsRepository;

    @Autowired
    private StudentRepository studentRepository;

    TruncateTablesFrame() {
        setTitle(("truncateTableFrame.title"));
        setLocation(new Random().nextInt(120) + 10, new Random().nextInt(120) + 10);
        setSize(550, 350);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton jTruncateStudentsTableButton = new JButton(("truncateStudents"));
        getContentPane().add(jTruncateStudentsTableButton);
        JButton jTruncateCreditsTableButton = new JButton(("truncateCredits"));
        getContentPane().add(jTruncateCreditsTableButton);
        JButton jTruncateGradesTableButton = new JButton(("truncateGrades"));
        getContentPane().add(jTruncateGradesTableButton);


        jTruncateStudentsTableButton.addActionListener(ev -> studentRepository.deleteAll());
        jTruncateCreditsTableButton.addActionListener(ev -> creditsRepository.deleteAll());
        jTruncateGradesTableButton.addActionListener(ev -> gradesRepository.deleteAll());
        setVisible(false);
    }
}
