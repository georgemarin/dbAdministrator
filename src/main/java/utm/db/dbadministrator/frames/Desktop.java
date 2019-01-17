package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
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

    private FrameAbout frameAbout = new FrameAbout();
    private CreateTablesFrame createTablesFrame = new CreateTablesFrame();
    private TruncateTablesFrame truncateTablesFrame = new TruncateTablesFrame();
    private DropTablesFrame dropTablesFrame = new DropTablesFrame();
    private ManageStudentsFrame manageStudentsFrame = new ManageStudentsFrame();
    private ManageCreditsFrame manageCreditsFrame = new ManageCreditsFrame();
    private ManageGradesFrame manageGradesFrame = new ManageGradesFrame();

    public Desktop() {

        setTitle("desktop.title");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);

        JDesktopPane jDesktopPane = new JDesktopPane();
        getContentPane().add(jDesktopPane, BorderLayout.CENTER);
        JLabel jLabelFooterState = new JLabel("desktop.jLabelFooterState");
        getContentPane().add(jLabelFooterState, BorderLayout.SOUTH);

        jDesktopPane.add(frameAbout);
        jDesktopPane.add(createTablesFrame);
        jDesktopPane.add(truncateTablesFrame);
        jDesktopPane.add(dropTablesFrame);
        jDesktopPane.add(manageStudentsFrame);
        jDesktopPane.add(manageCreditsFrame);
        jDesktopPane.add(manageGradesFrame);
        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);

        menuBar.jMenuItemQuit.addActionListener(ev -> {
            if (confirmBeforeExit()) {
                System.exit(0);
            }
        });

        menuBar.jMenuItemFrameAbout.addActionListener(ev -> frameAbout.setVisible(true));
        menuBar.jMenuItemCreateTables.addActionListener(ev -> createTablesFrame.setVisible(true));
        menuBar.jMenuItemTruncateTables.addActionListener(ev -> truncateTablesFrame.setVisible(true));
        menuBar.jMenuItemDropTables.addActionListener(ev -> dropTablesFrame.setVisible(true));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                if (confirmBeforeExit()) {
                    System.exit(0);
                }
            }
        });

        menuBar.jMenuItemManageStudents.addActionListener(ev -> {
            manageStudentsFrame.jTable1.setModel(manageStudentsFrame.getData());
            manageStudentsFrame.setVisible(true);
        });

        menuBar.jMenuItemManageCredits.addActionListener(ev -> {
            manageCreditsFrame.jTable1.setModel(manageCreditsFrame.getData());
            manageCreditsFrame.setVisible(true);
        });

        menuBar.jMenuItemManageGrades.addActionListener(ev -> {
            manageGradesFrame.jTable1.setModel(manageGradesFrame.getData());
            manageGradesFrame.setVisible(true);
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
        return JOptionPane.showConfirmDialog(this, "desktop.confirmbeforeexitdialog.text",
                "desktop.confirmbeforeexitdialog.title", JOptionPane.YES_NO_OPTION) == 0;
    }
}
