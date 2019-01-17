package utm.db.dbadministrator.frames;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.BeanProvider;
import utm.db.dbadministrator.services.CreditsService;
import utm.db.dbadministrator.services.GradesService;
import utm.db.dbadministrator.services.StudentService;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Configurable
class DropTablesWindow extends JInternalFrame {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradesService gradesService;

    @Autowired
    private CreditsService creditsService;


    DropTablesWindow() {
        BeanProvider.autowire(this);
        setTitle("dropTables");
        setLocation(new Random().nextInt(120) + 10, new Random().nextInt(120) + 10);
        setSize(550, 400);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton jDropStudentsTableButton = new JButton("dropStudents");
        getContentPane().add(jDropStudentsTableButton);
        JButton jDropCreditsTableButton = new JButton("dropCredits");
        getContentPane().add(jDropCreditsTableButton);
        JButton jDropGradesTableButton = new JButton("dropGrades");
        getContentPane().add(jDropGradesTableButton);


        jDropStudentsTableButton.addActionListener(ev -> studentService.getStudentRepository().deleteAll());
        jDropCreditsTableButton.addActionListener(ev -> creditsService.getCreditsRepository().deleteAll());
        jDropGradesTableButton.addActionListener(ev -> gradesService.getGradesRepository().deleteAll());
        setVisible(false);
    }
}
