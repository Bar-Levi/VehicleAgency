package Graphics;

import Decorator.IVehicle;
import Decorator.StatusDecorator;
import ObserverPackage.Subject;
import Vehicle.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Represents a thread that handles dialog boxes and user interactions in a graphical user interface.
 * Extends the Thread class and implements the Serializable interface.
 */
public class DialogThread extends Thread implements Serializable, Subject {
    VehicleAgencyGUI current_gui;
    int vehicle_index;
    public static final int MAX_CONCURRENT_FRAMES = 7;
    public static final int NUM_THREADS = 7;
    public static final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    public static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_FRAMES, true);

    public static int openMissionsCounter = 0;
    public static ReentrantLock dialog_lock = new ReentrantLock();
    int current_code;
    public IVehicle currentVehicle;
    protected JSlider slider;
    JDialog dialog;
    JDialog waiting_dialog;
    public LandDriver landDriver;
    public SeaDriver seaDriver;
    public AirDriver airDriver;
    public AmphibiousDriver amphibiousDriver;
    public HybridDriver hybridDriver;
    public VehicleSeller vehicleSeller;
    Font font;
    public final int init_destination_code = -3;
    public final int show_chosen_image_code = -4;
    public final int show_error_code = -5;
    public final int clicked_on_vehicle_code = -6;
    public final int updating_database_code = -7;
    public final int vehicle_was_added_code = -8;
    public final int testing_vehicle_code = -9;
    public final int done_testing_code = -10;
    public final int loading_empty_agency_code = -11;
    public final int buying_confirmation_code = -12;
    /**
     * Constructs a DialogThread object.
     * @param gui the VehicleAgencyGUI instance associated with the thread
     * @param code an integer representing the code associated with the dialog
     * @param vehicle_index the index of the vehicle in the agency
     */
    public DialogThread(VehicleAgencyGUI gui, int code, int vehicle_index)
    {
        super();
        current_gui = gui;
        current_code = code;
        this.vehicle_index = vehicle_index;
        if (current_gui.getAgency().getVehicles().length >= 1)
            currentVehicle = current_gui.getAgency().getVehicles()[Math.min(vehicle_index, current_gui.getAgency().getVehicles().length-1)];
        current_gui.openDialogs.add(this);

    }
    /**
     * Shows a waiting dialog.
     */
    public void showWaitingDialog() {
        openMissionsCounter+=1;
        waiting_dialog = new JDialog(current_gui.getFrame(), "Waiting...", false);
        JLabel label = new JLabel("Waiting for driver to return...");
        current_gui.setFontSize(30);
        label.setFont(current_gui.getFont());
        waiting_dialog.add(label);
        waiting_dialog.pack();
        waiting_dialog.setResizable(false);
        waiting_dialog.setVisible(true);
    }
    /**
     *Sets the font size for the current font to the specified size.
     *Creates a new Font object with the font family "Arial", center baseline alignment,
     *and the specified font size. Assigns the new font object to the instance variable font.
     *Returns a boolean value of true to indicate that the font size has been successfully set.
     *@param size an integer value representing the desired font size in points
     *@return true to indicate that the font size has been successfully set
     */
    public boolean setFontSize(final int size) {
        font = new Font("Arial", Font.CENTER_BASELINE, size*4/5); return true;}
    /**
     * Shows a dialog box with the specified title and label text.
     * @param title the title of the dialog box
     * @param labelText the text to be displayed in the dialog box
     */
    public void showDialog(String title, String labelText) {
        DialogThread.openMissionsCounter += 1;
        dialog_lock.lock();
        dialog = new JDialog(current_gui.getFrame(), title, false);
        setFontSize(30);
        JLabel label = new JLabel("<html><i>"+labelText+"</i></html>");
        label.setFont(font);
        dialog.add(label);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocation(current_gui.getFrame().getX() - dialog.getPreferredSize().width, current_gui.getFrame().getY());
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
        Timer timer = new Timer(3000, e -> { dialog.dispose(); DialogThread.openMissionsCounter -= 1; if (!current_gui.current_method.startsWith("create"))
            current_gui.showMenu();});
        timer.setRepeats(false);
        timer.start();
        dialog_lock.unlock();
    }
    /**
     * Performs an actual test drive with the given slider value.
     * @param slider the slider value representing the distance of the test drive
     */
    void ActualTestDrive(JSlider slider) {
        dialog.dispose();
        current_gui.setTestingKM(slider.getValue());
        current_code = done_testing_code;
        updateDialog();
        currentVehicle.setTraveled(currentVehicle.getTraveled() + slider.getValue());
        notifyObserver(slider.getValue());
    }
    /**
     * Executes the drive operation using a separate thread from the ExecutorService thread pool.
     *
     * @param driver The thread representing the driver.
     */
    public void executeDrive(Thread driver) {
        executor.execute(() -> {
            try {
                semaphore.acquire(); // Acquire a permit from the semaphore
                SwingUtilities.invokeLater(() -> {
                    driver.start();
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
    /**
     * Handles the event when the user clicks the test drive button.
     */
    public void clickedTestDriveButton() {
        openMissionsCounter += 1;
        try {
            if (currentVehicle.getVehicleLock().isLocked() && currentVehicle.getSellingLock().isLocked())  // If the vehicle is locked, and the seller is locked - vehicle is being bought,
            {
                dialog.dispose();
                showDialog(currentVehicle.getVehicleType() + " in Buying Process", "<html>This " + currentVehicle.getVehicleType() + " is<br>currently in a buying<br>process by another user.</html>");

                Thread x = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (currentVehicle.getUserConfirmedBuying()) {
                            try {
                                currentVehicle.getUserConfirmedBuying().wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            // Work
                        }
                    }
                });
                x.start();
            }
            else if (currentVehicle.getVehicleLock().isLocked() && !currentVehicle.getSellingLock().isLocked())  // If the vehicle is locked, and the seller is unlocked - vehicle is being tested,
            {
                dialog.dispose();
                showDialog(currentVehicle.getVehicleType() + " in Testing Process", "<html>This " + currentVehicle.getVehicleType() + " is<br>currently in a testing<br>process by another driver.</html>");
            }
        } catch (Exception ex) {
        }
        finally {
            openMissionsCounter-=1;
            if (!current_gui.current_method.startsWith("create"))
                current_gui.showMenu();
        }
        currentVehicle.setCurrentDriversCount(currentVehicle.getCurrentDriversCount() + 1);
        if (currentVehicle.getVehicle() instanceof HybridPlane) {
            hybridDriver = new HybridDriver(this);
            executeDrive(hybridDriver);
        } else if (currentVehicle.getVehicle() instanceof AmphibiousVehicle ) {
            amphibiousDriver = new AmphibiousDriver(this);
            executeDrive(amphibiousDriver);
        } else if (currentVehicle.getVehicle() instanceof LandVehicle) {
            landDriver = new LandDriver(this);
            executeDrive(landDriver);
        } else if (currentVehicle.getVehicle() instanceof SeaVehicle) {
            seaDriver = new SeaDriver(this);
            executeDrive(seaDriver);
        } else if (currentVehicle.getVehicle() instanceof AirVehicle) {
            airDriver = new AirDriver(this);
            executeDrive(airDriver);
        }



    }
    /**
     * Handles the event when the user clicks the buy button.
     */
    public void clickedBuyButton() {
        openMissionsCounter+=1;

        try {
            if (currentVehicle.getVehicleLock().isLocked() && currentVehicle.getSellingLock().isLocked())  // If the vehicle is being sold.
            {
                dialog.dispose();
                showDialog(currentVehicle.getVehicleType() + " in Buying Process", "<html>This " + currentVehicle.getVehicleType() + " is<br>currently in a buying<br>process by another user.<html>");
                currentVehicle.getUserConfirmedBuying().wait(1000);
            } else if (currentVehicle.getVehicleLock().isLocked() && !currentVehicle.getSellingLock().isLocked()) {
                showDialog(currentVehicle.getVehicleType() + " in Testing Process", "<html>This " + currentVehicle.getVehicleType() + " is<br>currently in a testing<br>process by another driver.<html>");
                currentVehicle.getUserFinishedTesting().wait(1000);
            }
        } catch (Exception ex) {}
        finally {
            openMissionsCounter-=1;
            if (!current_gui.current_method.startsWith("create"))
                current_gui.showMenu();


        }
        vehicleSeller = new VehicleSeller(this);
        vehicleSeller.start();

    }

    /**
     *Displays a dialog box depending on the value of the code parameter.
     *@return boolean value indicating whether the dialog box was successfully opened or not.
     */
    protected boolean updateDialog() {
        if (dialog != null)
            dialog.dispose();
        if (current_code == init_destination_code) {
            showDialog("Destination Initialized", "Initialized destination for all vehicles in agency.");
            UpdatingDatabaseDialogThread updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
            updatingDatabase.start();
        }
        else if (current_code == show_chosen_image_code) {
            dialog = new JDialog(current_gui.getFrame(), "Popup Window", false);
            dialog.setLayout(new BorderLayout());
            JLabel image_label = new JLabel(current_gui.temp_image);
            dialog.add(image_label, BorderLayout.CENTER);
            JLabel label = new JLabel("<html><u>Here's your chosen image</u></html>");
            dialog.add(label, BorderLayout.NORTH);

            setFontSize(30);
            label.setFont(font);
            dialog.add(label, BorderLayout.NORTH);

            JButton continue_button = new JButton("Continue");
            continue_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
            continue_button.setBackground(new Color(183, 193, 203));
            dialog.add(continue_button, BorderLayout.SOUTH);
            dialog.pack();
            dialog.setLocationRelativeTo(current_gui.getFrame());
            dialog.setVisible(true);
        }
        else if (current_code == show_error_code) {
            showDialog("Error", "[ERROR] Your input is invalid!");

        }
        else if (current_code == clicked_on_vehicle_code) {
            dialog = new JDialog(current_gui.getFrame(), currentVehicle.getVehicleType() +" options:", false);
            JLabel label = new JLabel("Choose your preferred action:");
            setFontSize(30);
            label.setFont(font);
            dialog.add(label, BorderLayout.NORTH);

            JButton buy_vehicle_button = new JButton("Buy");
            buy_vehicle_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                    clickedBuyButton();
                }
            });
            buy_vehicle_button.setBackground(new Color(203, 193, 183));
            buy_vehicle_button.setBounds(label.getX(), label.getY() + label.getHeight(), 50, 100);

            JButton test_drive_button = new JButton("Test Drive");
            test_drive_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                    current_code = testing_vehicle_code;
                    updateDialog();
                }
            });
            test_drive_button.setBackground(new Color(183, 193, 203));
            test_drive_button.setBounds(label.getX() + label.getWidth() - 50, label.getY() + label.getHeight(), 50, 100);

            JPanel buttons_panel = new JPanel();
            buttons_panel.setLayout(new GridLayout(1, 2));
            buttons_panel.add(buy_vehicle_button);
            buttons_panel.add(test_drive_button);
            dialog.add(buttons_panel, BorderLayout.CENTER);
            dialog.pack();
            dialog.setLocationRelativeTo(current_gui.getFrame());
            dialog.setVisible(true);
        }
        else if (current_code == buying_confirmation_code) {
            dialog.dispose();
            dialog = new JDialog(current_gui.getFrame(), "Buying Confirmation", false);
            JLabel label = new JLabel("Are you sure you want to buy this " + currentVehicle.getVehicleType());
            setFontSize(30);
            label.setFont(font);
            dialog.add(label, BorderLayout.NORTH);
            dialog.setAlwaysOnTop(true);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    openMissionsCounter-=1;
                    if (!current_gui.current_method.startsWith("create"))
                        current_gui.showMenu();
                }
            });

            JButton buy_vehicle_button = new JButton("Yes");
            buy_vehicle_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                    current_gui.removeVehicleFromAgency(currentVehicle);
                    UpdatingDatabaseDialogThread updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
                    updatingDatabase.start();
                    synchronized (currentVehicle.getUserConfirmedBuying()) {
                        currentVehicle.getUserConfirmedBuying().notifyAll();
                        openMissionsCounter-=1;
                        if (!current_gui.current_method.startsWith("create"))
                            current_gui.showMenu();

                    }
                }
            });
            buy_vehicle_button.setBackground(new Color(180, 250, 180));
            buy_vehicle_button.setBounds(label.getX(), label.getY() + label.getHeight(), 50, 100);

            JButton test_drive_button = new JButton("No");
            test_drive_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                    synchronized (currentVehicle.getUserConfirmedBuying()) {
                    currentVehicle.getUserConfirmedBuying().notifyAll();
                        ((StatusDecorator)currentVehicle.getIvehicle()).setStatus("Available",current_gui);
                        openMissionsCounter-=1;
                        if (!current_gui.current_method.startsWith("create"))
                            current_gui.showMenu();
                    }

                }
            });
            test_drive_button.setBackground(new Color(250, 180, 180));
            test_drive_button.setBounds(label.getX() + label.getWidth() - 50, label.getY() + label.getHeight(), 50, 100);

            JPanel buttons_panel = new JPanel();
            buttons_panel.setLayout(new GridLayout(1, 2));
            buttons_panel.add(buy_vehicle_button);
            buttons_panel.add(test_drive_button);
            dialog.add(buttons_panel, BorderLayout.CENTER);
            dialog.pack();
            dialog.setLocationRelativeTo(current_gui.getFrame());
            dialog.setVisible(true);
        }
        else if (current_code == testing_vehicle_code) {
            dialog = new JDialog(current_gui.getFrame(), "Testing a " + current_gui.getAgency().getVehicles()[vehicle_index].getVehicleType(), false);
            JLabel title = new JLabel("How many kilometers you want to ride?");
            setFontSize(30);
            title.setFont(font);
            dialog.add(title, BorderLayout.NORTH);
            slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
            slider.setMinorTickSpacing(5);
            slider.setMajorTickSpacing(25);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            dialog.add(slider);

            JButton test_drive_button = new JButton("Test " + slider.getValue() + " Kilometers!");

            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    test_drive_button.setText("Test " + slider.getValue() + " Kilometers!");
                }
            });
            test_drive_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                    clickedTestDriveButton();
                }
            });
            test_drive_button.setBackground(new Color(183, 193, 203));
            test_drive_button.setBounds(slider.getX() + slider.getWidth() - 50, slider.getY() + slider.getHeight(), 50, 100);


            JPanel buttons_panel = new JPanel();
            buttons_panel.setLayout(new GridLayout(2, 1));
            buttons_panel.add(slider);
            buttons_panel.add(test_drive_button);
            dialog.add(buttons_panel, BorderLayout.CENTER);
            dialog.pack();
            dialog.setLocationRelativeTo(current_gui.getFrame());
            dialog.setVisible(true);
        }
        else if (current_code == done_testing_code) {
            showDialog("Testing Done", "Finished test drive.");
        }
        else if (current_code == updating_database_code) {
            showDialog("Updating Database", "<html><div style=\"text-align: center;\">Updating database…<br>Please wait.</div></html>");
            //freezeFrame(current_gui.getFrame(), new Random().nextInt(4) + 5);
            synchronized (this) {
                try {
                    sleep(4000);
                } catch (Exception ex) {
                }
            }
            if (!current_gui.current_method.startsWith("create"))
                current_gui.showMenu();

        }
        else if (current_code == loading_empty_agency_code){
            showDialog("Loading Failed", "<html>You haven't saved any agency yet.<br>Start building your first agency! :D</html>");

        }
        else if (current_code == vehicle_was_added_code){
            UpdatingDatabaseDialogThread updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
            updatingDatabase.start();
            current_gui.buildAgency();
        }

        return true;
    }

    /**
     * Executes the thread's task, which involves updating the dialog and removing the current instance from the open dialogs list.
     */
    @Override
    public void run() {
        updateDialog();
        current_gui.openDialogs.remove(this);

    }
    /**
     * Notifies the observer with the traveled distance.
     *
     * @param traveledDistance The distance traveled by the vehicle.
     */
    @Override
    public void notifyObserver(int traveledDistance) {
        current_gui.update(traveledDistance);
    }
}
