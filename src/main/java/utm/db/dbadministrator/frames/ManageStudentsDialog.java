package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.entities.Student;
import utm.db.dbadministrator.repositories.StudentRepository;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Configurable
class ManageStudentsDialog extends JDialog {

    @Autowired
    private StudentRepository studentRepository;

    private JTextField jTextFieldFirstName = new JTextField(40);
    private JTextField jTextFieldLastName = new JTextField(40);
    private JTextField jTextFieldEmail = new JTextField(40);
    private JTextField jTextFieldRegNr = new JTextField(20);
    private JTextField jTextFieldYear = new JTextField(4);
    private JTextField jTextFieldGroup = new JTextField(4);
    private JTextField jTextFieldPhone = new JTextField(11);

    private boolean isNew;
    private Student studentToEdit;

    ManageStudentsDialog(Frame owner, String title, boolean modal, boolean isNew, Student studentToEdit) {
        super(owner, title, modal);

        this.isNew = isNew;
        this.studentToEdit = studentToEdit;

        setLocation(new Random().nextInt(150), new Random().nextInt(150));
        setSize(350, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if (isNew) {
            setLayout(new GridLayout(8, 2));
        } else {
            setLayout(new GridLayout(9, 2));
            JLabel jLabelId = new JLabel(("Id"));
            getContentPane().add(jLabelId);
            JTextField jTextFieldId = new JTextField(40);
            getContentPane().add(jTextFieldId);
            jTextFieldId.setEditable(false);
            jTextFieldId.setText("" + studentToEdit.getId());
            jTextFieldFirstName.setText(studentToEdit.getFirstName());
            jTextFieldLastName.setText(studentToEdit.getLastName());
            jTextFieldEmail.setText(studentToEdit.getEmail());
            jTextFieldRegNr.setText(studentToEdit.getStudentRegistrationNumber());
            jTextFieldYear.setText(studentToEdit.getStudentYear());
            jTextFieldGroup.setText(studentToEdit.getStudentGroup());
            jTextFieldPhone.setText(studentToEdit.getPhone());
        }

        JLabel jLabelFirstName = new JLabel(("FirstName"));
        getContentPane().add(jLabelFirstName);
        getContentPane().add(jTextFieldFirstName);

        JLabel jLabelLastName = new JLabel(("LastName"));
        getContentPane().add(jLabelLastName);
        getContentPane().add(jTextFieldLastName);

        JLabel jLabelEmail = new JLabel(("Email"));
        getContentPane().add(jLabelEmail);
        getContentPane().add(jTextFieldEmail);

        JLabel jLabelRegNr = new JLabel(("RegistrationNumber"));
        getContentPane().add(jLabelRegNr);
        getContentPane().add(jTextFieldRegNr);

        JLabel jLabelYear = new JLabel(("Year"));
        getContentPane().add(jLabelYear);
        getContentPane().add(jTextFieldYear);

        JLabel jLabelGroup = new JLabel(("Group"));
        getContentPane().add(jLabelGroup);
        getContentPane().add(jTextFieldGroup);

        JLabel jLabelPhone = new JLabel(("Phone"));
        getContentPane().add(jLabelPhone);
        getContentPane().add(jTextFieldPhone);

        JButton jButtonSave = new JButton(("Save"));
        getContentPane().add(jButtonSave);
        jButtonSave.addActionListener(ev -> jButtonSaveActionPerformed());

        JButton jButtonCancel = new JButton(("Cancel"));
        getContentPane().add(jButtonCancel);
        jButtonCancel.addActionListener(ev -> jButtonCancelActionPerformed());

        setVisible(true);
    }

    private void jButtonSaveActionPerformed() {

        Student student = new Student();
        student.setFirstName(jTextFieldFirstName.getText());
        student.setLastName(jTextFieldLastName.getText());
        student.setEmail(jTextFieldEmail.getText());
        student.setStudentRegistrationNumber(jTextFieldRegNr.getText());
        student.setStudentYear(jTextFieldYear.getText());
        student.setStudentGroup(jTextFieldGroup.getText());
        student.setPhone(jTextFieldPhone.getText());

        if (this.isNew) {
            studentRepository.save(student);
            this.dispose();
        } else {
            student.setId(this.studentToEdit.getId());
            studentRepository.save(student);
            this.dispose();
        }
    }

    private void jButtonCancelActionPerformed() {
        this.dispose();
    }

}
