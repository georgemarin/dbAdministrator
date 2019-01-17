package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.entities.Grades;
import utm.db.dbadministrator.services.GradesService;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Configurable
class ManageGradesDialog extends JDialog {

    @Autowired
    private GradesService gradesService;

    private JTextField jTextFieldName = new JTextField(50);
    private JTextField jTextFieldCode = new JTextField(50);
    private JTextField jTextFieldStdId = new JTextField(20);
    private JTextField jTextFieldGrade = new JTextField(5);

    private boolean isNew;
    private Grades grades;

    ManageGradesDialog(Frame owner, String title, boolean modal, boolean isNew, Grades grades) {
        super(owner, title, modal);

        this.isNew = isNew;
        this.grades = grades;
        setLocation(new Random().nextInt(150), new Random().nextInt(150));
        setSize(350, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if (isNew) {
            setLayout(new GridLayout(5, 2));
        } else {
            setLayout(new GridLayout(6, 2));
            JLabel jLabelId = new JLabel("Id");
            getContentPane().add(jLabelId);
            JTextField jTextFieldId = new JTextField(50);
            getContentPane().add(jTextFieldId);
            jTextFieldId.setEditable(false);

            // write values in form :
            jTextFieldId.setText("" + grades.getId());
            jTextFieldName.setText(grades.getCreditName());
            jTextFieldCode.setText(grades.getCreditCode());
            jTextFieldStdId.setText(String.valueOf(grades.getStudentId()));
            jTextFieldGrade.setText(grades.getGrade());
        }

        JLabel jLabelName = new JLabel("Name");
        getContentPane().add(jLabelName);
        getContentPane().add(jTextFieldName);

        JLabel jLabelCode = new JLabel("Code");
        getContentPane().add(jLabelCode);
        getContentPane().add(jTextFieldCode);

        JLabel jLabelStdId = new JLabel("StudentId");
        getContentPane().add(jLabelStdId);
        getContentPane().add(jTextFieldStdId);

        JLabel jLabelGrade = new JLabel("Grade");
        getContentPane().add(jLabelGrade);
        getContentPane().add(jTextFieldGrade);

        JButton jButtonSave = new JButton(("Save"));
        getContentPane().add(jButtonSave);
        jButtonSave.addActionListener(ev -> jButtonSaveActionPerformed());

        JButton jButtonCancel = new JButton(("Cancel"));
        getContentPane().add(jButtonCancel);
        jButtonCancel.addActionListener(ev -> jButtonCancelActionPerformed());

        setVisible(true);
    }

    private void jButtonSaveActionPerformed() {
        Grades grades = new Grades();

        grades.setCreditName(jTextFieldName.getText());
        grades.setCreditCode(jTextFieldCode.getText());
        grades.setStudentId(Long.valueOf(jTextFieldStdId.getText()));
        grades.setGrade(jTextFieldGrade.getText());

        if (this.isNew) {
            gradesService.getGradesRepository().save(grades);
            this.dispose();
        } else {
            grades.setId(this.grades.getId());
            gradesService.getGradesRepository().save(grades);
            this.dispose();
        }

    }

    private void jButtonCancelActionPerformed() {
        this.dispose();
    }

}