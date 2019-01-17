package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.repositories.GradesRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

@Configurable
public class ManageGradesFrame extends JInternalFrame {

    @Autowired
    private GradesRepository gradesRepository;

    JTable jTable1;

    ManageGradesFrame() {
        setTitle(("manageGradesFrame.title"));
        setLocation(new Random().nextInt(100), new Random().nextInt(100));
        setSize(550, 350);
        setVisible(false);
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel jPanelHeader = new JPanel();
        jPanelHeader.setBorder(BorderFactory.createTitledBorder(("Header")));

        JButton jButtonDelete = new JButton("Delete");
        jPanelHeader.add(jButtonDelete);
        jButtonDelete.addActionListener(ev -> jButtonDeleteActionPerformed());

        JButton jButtonAdd = new JButton("Add");
        jPanelHeader.add(jButtonAdd);
        jButtonAdd.addActionListener(ev -> jButtonAddActionPerformed());

        JButton jButtonEdit = new JButton("Edit");
        jPanelHeader.add(jButtonEdit);
        jButtonEdit.addActionListener(ev -> jButtonEditActionPerformed());
        getContentPane().add(jPanelHeader, BorderLayout.NORTH);

        jTable1 = new JTable(this.getData());
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setDefaultEditor(Object.class, null);

        getContentPane().add(new JScrollPane(jTable1), BorderLayout.CENTER);
    }

    public DefaultTableModel getData() {
        String[] columns = new String[] {"id", "name", "code", "studentId", "grade"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        gradesRepository.findAll().forEach(grades -> model.addRow(new Object[]{
                grades.getId(),
                grades.getCreditName(),
                grades.getCreditCode(),
                grades.getStudentId(),
                grades.getGrade()
        }));
        return model;
    }

    private void jButtonDeleteActionPerformed() {

        if (jTable1.getSelectedRowCount() > 0) {
            int[] selectedRows = jTable1.getSelectedRows();
            for (int selectedRow : selectedRows) {
                gradesRepository.deleteById((long) jTable1.getValueAt(selectedRow, 0));
            }
            jTable1.setModel(this.getData());
        }
    }

    private void jButtonAddActionPerformed() {
        new ManageGradesDialog(null, ("addTitle"), true, true, null);
        jTable1.setModel(this.getData());
    }

    private void jButtonEditActionPerformed() {
        if (jTable1.getSelectedRowCount() > 0) {
            long grade_id = (long) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            new ManageGradesDialog(null, ("editTitle"), true, false, gradesRepository.findById(grade_id).get());
            jTable1.setModel(this.getData());
        }
    }

}