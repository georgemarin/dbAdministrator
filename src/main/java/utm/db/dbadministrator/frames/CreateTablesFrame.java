package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.BeanProvider;
import utm.db.dbadministrator.entities.Credits;
import utm.db.dbadministrator.entities.Grades;
import utm.db.dbadministrator.entities.Student;
import utm.db.dbadministrator.services.CreditsService;
import utm.db.dbadministrator.services.GradesService;
import utm.db.dbadministrator.services.StudentService;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

@Configurable
class CreateTablesFrame extends JInternalFrame {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CreditsService creditsService;

    @Autowired
    private GradesService gradesService;

    CreateTablesFrame() {
        BeanProvider.autowire(this);
        setTitle("createTableFrame.title");
        setLocation(new Random().nextInt(120) + 10, new Random().nextInt(120) + 10);
        setSize(550, 350);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton jCreateStudentsTableButton = new JButton("createTableFrame.createStudentsTableButton");
        getContentPane().add(jCreateStudentsTableButton);
        JButton jCreateCreditsTableButton = new JButton("createCreditsTableButton");
        getContentPane().add(jCreateCreditsTableButton);
        JButton jCreateGradesTableButton = new JButton("createGradesTableButton");
        getContentPane().add(jCreateGradesTableButton);

        JButton jPopulateStudentsTableButton = new JButton("populateStudentsTableButton");
        getContentPane().add(jPopulateStudentsTableButton);
        JButton jPopulateCreditsTableButton = new JButton("populateCreditsTableButton");
        getContentPane().add(jPopulateCreditsTableButton);
        JButton jPopulateGradesTableButton = new JButton("populateGradesTableButton");
        getContentPane().add(jPopulateGradesTableButton);

        jPopulateStudentsTableButton.addActionListener(e -> this.populateStudentsTable());
        jPopulateCreditsTableButton.addActionListener(e -> this.populateCreditsTable());
        jPopulateGradesTableButton.addActionListener(e -> this.populateGradesTable());
        setVisible(false);
    }

    private void populateStudentsTable() {
        Student georgeMarin = new Student(1, "George", "Marin", "marin.george2016@s.utm.ro", "12345", "2019", "318", "0729641715");
        Student georgeMarin2 = new Student(2, "George", "Marin", "marin.george2016@s.utm.ro", "12346", "2019", "318", "0729641715");
        Student georgeMarin3 = new Student(3, "George", "Marin", "marin.george2016@s.utm.ro", "12347", "2019", "318", "0729641715");
        studentService.getStudentRepository().saveAll(Arrays.asList(georgeMarin, georgeMarin2, georgeMarin3));
    }

    private void populateCreditsTable() {
        Credits pp = new Credits(1, "Programare Procedurala", "5", "12345");
        Credits analiza = new Credits(2, "Analiza Matematica", "5", "12346");
        Credits proiectare = new Credits(3, "Proiectarea Interfetelor Grafice", "6", "12347");
        creditsService.getCreditsRepository().saveAll(Arrays.asList(pp, analiza, proiectare));
    }

    private void populateGradesTable() {
        Grades pp = new Grades(1, "Progrmare Procedurala", "12345", 1, "5");
        Grades analiza = new Grades(2, "Analiza Matematica", "12346", 2, "5");
        Grades proiectare = new Grades(3, "Proiectarea Interfetelor Grafice", "12347", 1, "5");
        gradesService.getGradesRepository().saveAll(Arrays.asList(pp, analiza, proiectare));
    }
}
