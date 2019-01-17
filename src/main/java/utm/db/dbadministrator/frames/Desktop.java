package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.BeanProvider;
import utm.db.dbadministrator.services.CreditsService;
import utm.db.dbadministrator.services.GradesService;
import utm.db.dbadministrator.services.StudentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Configurable
public class Desktop extends JFrame {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradesService gradesService;

    @Autowired
    private CreditsService creditsService;

    private AboutWindow aboutWindow = new AboutWindow();
    private CreateTablesWindow createTablesWindow = new CreateTablesWindow();
    private TruncateTablesWindow truncateTablesWindow = new TruncateTablesWindow();
    private DropTablesWindow dropTablesWindow = new DropTablesWindow();
    private ManageStudentsWindow manageStudentsWindow = new ManageStudentsWindow();
    private ManageCreditsWindow manageCreditsWindow = new ManageCreditsWindow();
    private ManageGradesWindow manageGradesWindow = new ManageGradesWindow();

    public Desktop() {
        BeanProvider.autowire(this);
        setTitle("Database Administrator App");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);

        JDesktopPane jDesktopPane = new JDesktopPane();
        getContentPane().add(jDesktopPane, BorderLayout.CENTER);
        JLabel jLabelFooterState = new JLabel("Footer");
        getContentPane().add(jLabelFooterState, BorderLayout.SOUTH);

        jDesktopPane.add(aboutWindow);
        jDesktopPane.add(createTablesWindow);
        jDesktopPane.add(truncateTablesWindow);
        jDesktopPane.add(dropTablesWindow);
        jDesktopPane.add(manageStudentsWindow);
        jDesktopPane.add(manageCreditsWindow);
        jDesktopPane.add(manageGradesWindow);
        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);

        menuBar.jMenuItemQuit.addActionListener(ev -> {
            if (confirmBeforeExit()) {
                System.exit(0);
            }
        });

        menuBar.jMenuItemFrameAbout.addActionListener(ev -> aboutWindow.setVisible(true));
        menuBar.jMenuItemCreateTables.addActionListener(ev -> createTablesWindow.setVisible(true));
        menuBar.jMenuItemTruncateTables.addActionListener(ev -> truncateTablesWindow.setVisible(true));
        menuBar.jMenuItemDropTables.addActionListener(ev -> dropTablesWindow.setVisible(true));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                if (confirmBeforeExit()) {
                    System.exit(0);
                }
            }
        });

        menuBar.jMenuItemManageStudents.addActionListener(ev -> {
            manageStudentsWindow.jTable1.setModel(manageStudentsWindow.getData());
            manageStudentsWindow.setVisible(true);
        });

        menuBar.jMenuItemManageCredits.addActionListener(ev -> {
            manageCreditsWindow.jTable1.setModel(manageCreditsWindow.getData());
            manageCreditsWindow.setVisible(true);
        });

        menuBar.jMenuItemManageGrades.addActionListener(ev -> {
            manageGradesWindow.jTable1.setModel(manageGradesWindow.getData());
            manageGradesWindow.setVisible(true);
        });

        setVisible(true);

        String output = "Tables STUDENTS, CREDITS & GRADES have been created";
        if (!studentService.getStudentRepository().findAll().isEmpty() &&
                !creditsService.getCreditsRepository().findAll().isEmpty() &&
                !gradesService.getGradesRepository().findAll().isEmpty()) {
            output += "\nTables STUDENTS, CREDITS & GRADES have data";
        }
        JOptionPane.showMessageDialog(null, output, "Database information", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean confirmBeforeExit() {
        return JOptionPane.showConfirmDialog(this, "Confirm exit",
                "Confirm Exit", JOptionPane.YES_NO_OPTION) == 0;
    }
}
