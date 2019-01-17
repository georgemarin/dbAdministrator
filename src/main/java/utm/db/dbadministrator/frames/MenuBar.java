package utm.db.dbadministrator.frames;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static java.awt.Toolkit.getDefaultToolkit;

class MenuBar extends JMenuBar {
    JMenuItem jMenuItemCreateTables = new JMenuItem(("CreateTables"));
    JMenuItem jMenuItemTruncateTables = new JMenuItem(("TruncateTables"));
    JMenuItem jMenuItemDropTables = new JMenuItem(("DropTables"));
    JMenuItem jMenuItemManageStudents = new JMenuItem(("Students"));
    JMenuItem jMenuItemManageCredits = new JMenuItem(("Credits"));
    JMenuItem jMenuItemManageGrades = new JMenuItem(("Grades"));
    JMenuItem jMenuItemQuit = new JMenuItem(("Quit"));

    JMenuItem jMenuItemFrameAbout = new JMenuItem(("FrameAbout"));

    MenuBar() {

        // file :
        JMenu jMenuDatabaseActions = new JMenu(("DatabaseActions"));
        add(jMenuDatabaseActions);
        jMenuDatabaseActions.setMnemonic(KeyEvent.VK_F);

        jMenuItemCreateTables.setAccelerator(KeyStroke.getKeyStroke('C', getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuDatabaseActions.add(jMenuItemCreateTables);
        jMenuDatabaseActions.addSeparator();

        jMenuItemTruncateTables.setAccelerator(KeyStroke.getKeyStroke('T', getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuDatabaseActions.add(jMenuItemTruncateTables);
        jMenuDatabaseActions.addSeparator();

        jMenuItemDropTables.setAccelerator(KeyStroke.getKeyStroke('P', getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuDatabaseActions.add(jMenuItemDropTables);
        jMenuDatabaseActions.addSeparator();

        jMenuItemManageStudents.setAccelerator(KeyStroke.getKeyStroke('S', getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuDatabaseActions.add(jMenuItemManageStudents);
        jMenuDatabaseActions.addSeparator();

        jMenuItemManageCredits.setAccelerator(KeyStroke.getKeyStroke('D', getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuDatabaseActions.add(jMenuItemManageCredits);
        jMenuDatabaseActions.addSeparator();

        jMenuItemManageGrades.setAccelerator(KeyStroke.getKeyStroke('G', getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuDatabaseActions.add(jMenuItemManageGrades);
        jMenuDatabaseActions.addSeparator();

        jMenuItemQuit.setAccelerator(KeyStroke.getKeyStroke('Q', getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuDatabaseActions.add(jMenuItemQuit);

        JMenu jMenuHelp = new JMenu(("Help"));
        add(jMenuHelp);
        jMenuHelp.setMnemonic(KeyEvent.VK_H);

        jMenuItemFrameAbout.setAccelerator(KeyStroke.getKeyStroke('A', getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuHelp.add(jMenuItemFrameAbout);
    }
}