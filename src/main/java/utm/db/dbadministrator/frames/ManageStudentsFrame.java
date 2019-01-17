package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.BeanProvider;
import utm.db.dbadministrator.services.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

@Configurable
public class ManageStudentsFrame extends JInternalFrame {

    JTable jTable1;

    @Autowired
    private StudentService studentService;

    ManageStudentsFrame() {
        BeanProvider.autowire(this);
        setTitle(("manageStudentsFrame.title"));
        setLocation(new Random().nextInt(100), new Random().nextInt(100));
        setSize(550, 350);
        setVisible(false);
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel jPanelHeader = new JPanel();
        jPanelHeader.setBorder(BorderFactory.createTitledBorder(("jPanelHeader")));

        JButton jButtonDelete = new JButton(("jButtonDelete"));
        jPanelHeader.add(jButtonDelete);
        jButtonDelete.addActionListener(ev -> jButtonDeleteActionPerformed());

        JButton jButtonAdd = new JButton(("jButtonAdd"));
        jPanelHeader.add(jButtonAdd);
        jButtonAdd.addActionListener(ev -> jButtonAddActionPerformed());

        JButton jButtonEdit = new JButton(("jButtonEdit"));
        jPanelHeader.add(jButtonEdit);
        jButtonEdit.addActionListener(ev -> jButtonEditActionPerformed());

        getContentPane().add(jPanelHeader, BorderLayout.NORTH);

        // Table :
        jTable1 = new JTable(this.getData());
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setDefaultEditor(Object.class, null);

        getContentPane().add(new JScrollPane(jTable1), BorderLayout.CENTER);
    }

    public DefaultTableModel getData() {
        String[] columns = new String[] {"id", "firstName", "lastName", "email", "registrationNumber", "group", "year", "mobile"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        studentService.getStudentRepository().findAll().forEach(student -> model.addRow(new Object[]{
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getStudentRegistrationNumber(),
                student.getStudentGroup(),
                student.getStudentYear(),
                student.getPhone()
        }));
        return model;
    }

    private void jButtonDeleteActionPerformed() {
        if (jTable1.getSelectedRowCount() > 0) {
            int[] selectedRows = jTable1.getSelectedRows();
            for (int selectedRow : selectedRows) {
                studentService.getStudentRepository().deleteById((long) jTable1.getValueAt(selectedRow, 0));
            }
            jTable1.setModel(this.getData());
        }
    }

    private void jButtonAddActionPerformed() {
        new ManageStudentsDialog(null, ("addTitle"), true, true, null);
        jTable1.setModel(this.getData());
    }

    private void jButtonEditActionPerformed() {
        if (jTable1.getSelectedRowCount() > 0) {
            long student_id = (long) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            new ManageStudentsDialog(null, ("editTitle"), true, false, studentService.getStudentRepository().findById(student_id).get());
            jTable1.setModel(this.getData());
        }
    }

}
