package Graphics;
import javax.swing.*;
import java.awt.*;
/**
 * The GifDialogThread class represents a thread that displays a GIF dialog.
 * It extends the Thread class and is used to show a loading GIF animation in a dialog window.
 */
public class GifDialogThread extends Thread {
    protected JDialog dialog;
    /**
     * Constructs a GifDialogThread object with the specified JFrame and title.
     * The dialog contains a loading GIF animation and is displayed as always on top.
     * @param gui the VehicleAgencyGUI to which the dialog is relative
     * @param title the title of the dialog
     */
    public GifDialogThread(VehicleAgencyGUI gui, String title)
    {
        DialogThread.openMissionsCounter += 1;
        gui.showMenu();
        dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        dialog.setTitle(title);
        dialog.setSize(new Dimension(250, 100));
        dialog.getContentPane().setBackground(Color.white);


        // Create a JLabel to display the GIF
        JLabel gifLabel = new JLabel(new ImageIcon(getClass().getResource("Assets/loading_gif.gif")));

        // Set the layout of the dialog
        dialog.setLayout(new BorderLayout());

        // Add the gifLabel to the center of the dialog
        dialog.add(gifLabel, BorderLayout.CENTER);

        dialog.setLocation(gui.getFrame().getX() - 250 , gui.getFrame().getY());
        // Set the dialog to be visible
        dialog.setVisible(true);
    }
}
