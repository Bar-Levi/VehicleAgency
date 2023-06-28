package Graphics;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;
/**
 * The UpdatingDatabaseDialogThread class represents a thread for updating the database.
 * It extends the Thread class and implements Serializable.
 */
public class UpdatingDatabaseDialogThread extends Thread implements Serializable {
    private JDialog dialog;
    private int freezing_sec = 5;
    private VehicleAgencyGUI current_gui;
    public static ReentrantLock updating_database_lock = new ReentrantLock();
    /**
     * Constructs an UpdatingDatabaseDialogThread object with the specified VehicleAgencyGUI.
     * It creates and initializes the dialog for updating the database.
     * @param gui the VehicleAgencyGUI associated with the thread
     */
    public UpdatingDatabaseDialogThread(VehicleAgencyGUI gui) {
        super();
        current_gui = gui;
        dialog = new JDialog(current_gui.getFrame(), "Updating Database", false);
        current_gui.setFontSize(30);
        JLabel label = new JLabel("<html><div style=\"text-align: center;\">Updating database…<br>Please wait.</div></html>");
        label.setFont(current_gui.getFont());
        dialog.add(label);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(current_gui.getFrame());
    }
    /**
     * Pauses the execution of the thread for the specified duration.
     * @param seconds the duration to pause in seconds
     */
    private void freezeFrame(int seconds) {
        // Disable all components in the frame
        for (DialogThread dialogThread:current_gui.openDialogs) {
            setComponentsEnabled(dialogThread.dialog.getContentPane(), false);
        }

        setComponentsEnabled(current_gui.getFrame().getContentPane(), false);
        setComponentsEnabled(ChangeFlagFrame.getReference(), false);
        setComponentsEnabled(AgencyVehiclesReport.getReference(), false);

        // Pause the execution for the specified duration
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setComponentsEnabled(current_gui.getFrame().getContentPane(), true);
        setComponentsEnabled(ChangeFlagFrame.getReference(), true);
        setComponentsEnabled(AgencyVehiclesReport.getReference(), true);
        for (DialogThread dialogThread:current_gui.openDialogs) {
            setComponentsEnabled(dialogThread.dialog.getContentPane(), true);
        }

    }
    /**
     * Recursively enables or disables the components within the given container.
     * @param container the container to enable or disable components
     * @param enabled   true to enable components, false to disable components
     */
    private static void setComponentsEnabled(Container container, boolean enabled) {
        if (container != null) {
            Component[] components = container.getComponents();
            for (Component component : components) {
                if (component instanceof Container) {
                    setComponentsEnabled((Container) component, enabled);
                }
                component.setEnabled(enabled);
            }
        }

    }
    /**
     * Overrides the run() method of the Thread class.
     * Performs the database update operation.
     */
    public void run(){
        DialogThread.openMissionsCounter += 1;
        current_gui.lock.lock();
        dialog.setVisible(true);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        updating_database_lock.lock();
        try {
            freezeFrame(freezing_sec);

        } catch (Exception ex) {}
        finally {
            updating_database_lock.unlock();
            dialog.dispose();
            if(current_gui.dialog !=null)
                current_gui.dialog.dispose();
            DialogThread.openMissionsCounter-=1;
            current_gui.lock.unlock();
            current_gui.showMenu();

        }
    }
}
