package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.services.CreditsService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

@Configurable
public class ManageCreditsFrame extends JInternalFrame {

    JTable jTable1;

    @Autowired
    private CreditsService creditsService;

    ManageCreditsFrame() {
        setTitle(("manageCreditsFrame.title"));
        setLocation(new Random().nextInt(100), new Random().nextInt(100));
        setSize(550, 350);
        setVisible(false);
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel jPanelHeader = new JPanel();
        jPanelHeader.setBorder(BorderFactory.createTitledBorder("jPanelHeader"));

        JButton jButtonDelete = new JButton("jButtonDelete");
        jPanelHeader.add(jButtonDelete);
        jButtonDelete.addActionListener(ev -> jButtonDeleteActionPerformed());

        JButton jButtonAdd = new JButton("jButtonAdd");
        jPanelHeader.add(jButtonAdd);
        jButtonAdd.addActionListener(ev -> jButtonAddActionPerformed());

        JButton jButtonEdit = new JButton("jButtonEdit");
        jPanelHeader.add(jButtonEdit);
        jButtonEdit.addActionListener(ev -> jButtonEditActionPerformed());

        getContentPane().add(jPanelHeader, BorderLayout.NORTH);

        jTable1 = new JTable(this.getData());
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setDefaultEditor(Object.class, null);

        getContentPane().add(new JScrollPane(jTable1), BorderLayout.CENTER);
    }

    public DefaultTableModel getData() {
        String[] columns = new String[]{"column.id", "column.name", "column.nr", "column.code"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        creditsService.getCreditsRepository().findAll().forEach(credits -> model.addRow(new Object[]{
                credits.getId(),
                credits.getCreditName(),
                credits.getCreditNumber(),
                credits.getGetCreditCode()
        }));
        return model;
    }

    private void jButtonDeleteActionPerformed() {
        if (jTable1.getSelectedRowCount() > 0) {
            int[] selectedRows = jTable1.getSelectedRows();
            for (int selectedRow : selectedRows) {
                creditsService.getCreditsRepository().deleteById((long) jTable1.getValueAt(selectedRow, 0));
            }

            jTable1.setModel(this.getData());
        }
    }

    private void jButtonAddActionPerformed() {
        new ManageCreditsDialog(null, ("addTitle"), true, true, null);
        jTable1.setModel(this.getData());
    }

    private void jButtonEditActionPerformed() {
        if (jTable1.getSelectedRowCount() > 0) {
            long credit_id = (long) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            new ManageCreditsDialog(null, ("editTitle"), true, false, creditsService.getCreditsRepository().findById(credit_id).get());
            jTable1.setModel(this.getData());
        }
    }

}

