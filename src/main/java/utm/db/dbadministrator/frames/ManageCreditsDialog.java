package utm.db.dbadministrator.frames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import utm.db.dbadministrator.BeanProvider;
import utm.db.dbadministrator.entities.Credits;
import utm.db.dbadministrator.services.CreditsService;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Configurable
class ManageCreditsDialog extends JDialog {


    @Autowired
    private CreditsService creditsService;

    private final Credits creditToEdit;
    private JTextField jTextFieldName = new JTextField(50);
    private JTextField jTextFieldNr = new JTextField(5);
    private JTextField jTextFieldCode = new JTextField(50);

    private boolean isNew;

    ManageCreditsDialog(Frame owner, String title, boolean modal, boolean isNew, Credits creditToEdit) {
        super(owner, title, modal);
        BeanProvider.autowire(this);
        this.isNew = isNew;
        this.creditToEdit = creditToEdit;

        setLocation(new Random().nextInt(150), new Random().nextInt(150));
        setSize(350, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if (isNew) {
            setLayout(new GridLayout(4, 2));
        } else {
            setLayout(new GridLayout(5, 2));
            JLabel jLabelId = new JLabel(("aboutFrameJLabelId"));
            getContentPane().add(jLabelId);
            JTextField jTextFieldId = new JTextField(50);
            getContentPane().add(jTextFieldId);
            jTextFieldId.setEditable(false);

            jTextFieldId.setText("" + creditToEdit.getId());
            jTextFieldName.setText(creditToEdit.getCreditName());
            jTextFieldNr.setText(creditToEdit.getCreditNumber());
            jTextFieldCode.setText(creditToEdit.getGetCreditCode());
        }

        JLabel jLabelName = new JLabel(("aboutFrameJLabelName"));
        getContentPane().add(jLabelName);
        getContentPane().add(jTextFieldName);

        JLabel jLabelNr = new JLabel(("aboutFrameJLabelNr"));
        getContentPane().add(jLabelNr);
        getContentPane().add(jTextFieldNr);

        JLabel jLabelCode = new JLabel(("aboutFrameJLabelCode"));
        getContentPane().add(jLabelCode);
        getContentPane().add(jTextFieldCode);

        JButton jButtonSave = new JButton(("aboutFrameJButtonSave"));
        getContentPane().add(jButtonSave);
        jButtonSave.addActionListener(ev -> jButtonSaveActionPerformed());
        JButton jButtonCancel = new JButton(("aboutFrameJButtonCancel"));
        getContentPane().add(jButtonCancel);
        jButtonCancel.addActionListener(ev -> jButtonCancelActionPerformed());
        setVisible(true);
    }

    private void jButtonSaveActionPerformed() {
        Credits credits = new Credits();
        credits.setCreditName(jTextFieldName.getText());
        credits.setCreditNumber(jTextFieldNr.getText());
        credits.setGetCreditCode(jTextFieldCode.getText());
        if (this.isNew) {
            creditsService.getCreditsRepository().save(credits);
            this.dispose();
        } else {
            credits.setId(this.creditToEdit.getId());
            creditsService.getCreditsRepository().save(credits);
            this.dispose();
        }
    }

    private void jButtonCancelActionPerformed() {
        this.dispose();
    }

}
