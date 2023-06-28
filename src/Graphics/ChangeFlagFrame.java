package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 *The ChangeFlagFrame class represents a frame for changing the country flag for all sea vehicles in a vehicle agency.
 *It extends the JFrame class and implements the Serializable interface.
 */
public class ChangeFlagFrame extends JFrame implements Serializable {
    private static ChangeFlagFrame instance;
    private VehicleAgencyGUI current_gui;
    private JPanel panel;
    private int frameWidth = 600;
    private int frameHeight = 400;
    private int buttons_width = frameWidth/8;
    private int buttons_height = frameHeight/6;
    private UpdatingDatabaseDialogThread updatingDatabase = null;
    /**
     *Constructs a new ChangeFlagFrame object.
     *@param gui The VehicleAgencyGUI associated with this frame.
     */
    private ChangeFlagFrame(VehicleAgencyGUI gui) {
        super();
        current_gui = gui;
    }
    /**
     *Returns the reference to the ChangeFlagFrame instance.
     *@return The reference to the ChangeFlagFrame instance.
     */
    public static ChangeFlagFrame getReference() { return instance; }
    /**
     *Returns the instance of the ChangeFlagFrame class.
     *If the instance is null, it creates a new instance and assigns it to the instance variable.
     *@param gui The VehicleAgencyGUI associated with this frame.
     *@return The instance of the ChangeFlagFrame class.
     */
    public static ChangeFlagFrame getInstance(VehicleAgencyGUI gui) {
        if (instance == null) {
            instance = new ChangeFlagFrame(gui);
        }
        return instance;
    }
    /**
     *Initializes the ChangeFlagFrame by setting the layout, size, and visibility.
     */
    public void Initialize() {
        setLayout(new BorderLayout(10, 10));
        panel = new JPanel();
        panel.setSize(frameWidth, frameHeight);
        panel.setLayout(null);
        current_gui.setFontSize(30);
        setSize(new Dimension(frameWidth, frameHeight));
        setResizable(false);
        setVisible(true);

    }
    /**
     *Builds the flags frame by adding labels and buttons for choosing the new country flag.
     */
    public void buildFlagsFrame() {
        {
            current_gui.updateCurrentMethod("changeFlag");

            JLabel label1 = new JLabel("Choose your new country flag");
            JLabel label2 = new JLabel("for all sea vehicles:");

            MyShortcuts.addComponentToPanel(current_gui, label1, panel, 20);
            MyShortcuts.addComponentToPanel(current_gui, label2, panel, 40);
            MyShortcuts.centerComponent(this, label1, 20);
            MyShortcuts.centerComponent(this, label2, 60);



            // Creating buttons.
            JButton israel_button = MyShortcuts.pathToButton(current_gui, "Assets/Flags/IsraelFlag.png", buttons_width, buttons_height);
            JButton usa_button = MyShortcuts.pathToButton(current_gui, "Assets/Flags/UnitedStatesFlag.png", buttons_width, buttons_height);
            JButton germany_button = MyShortcuts.pathToButton(current_gui, "Assets/Flags/GermanyFlag.png", buttons_width, buttons_height);
            JButton greece_button = MyShortcuts.pathToButton(current_gui, "Assets/Flags/GreeceFlag.png", buttons_width, buttons_height);
            JButton sumalia_button = MyShortcuts.pathToButton(current_gui, "Assets/Flags/SumaliaFlag.png", buttons_width, buttons_height);
            JButton italy_button = MyShortcuts.pathToButton(current_gui, "Assets/Flags/ItalyFlag.png", buttons_width, buttons_height);
            JButton pirate_button = MyShortcuts.pathToButton(current_gui, "Assets/Flags/PirateFlag.png", buttons_width, buttons_height);
            // Modifying action listener.
            israel_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current_gui.setNewFlag("Israel");
                    updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
                    updatingDatabase.start();
                }
            });
            usa_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current_gui.setNewFlag("United States");
                    updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
                    updatingDatabase.start();

                }
            });
            germany_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current_gui.setNewFlag("Germany");
                    updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
                    updatingDatabase.start();
                }
            });
            greece_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current_gui.setNewFlag("Greece");
                    updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
                    updatingDatabase.start();
                }
            });
            sumalia_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current_gui.setNewFlag("Sumalia");
                    updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
                    updatingDatabase.start();
                }
            });
            italy_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current_gui.setNewFlag("Italy");
                    updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
                    updatingDatabase.start();
                }
            });
            pirate_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current_gui.setNewFlag("Pirate");
                    updatingDatabase = new UpdatingDatabaseDialogThread(current_gui);
                    updatingDatabase.start();
                }
            });


            // Setting buttons' bounds.
            int top_buttons_y = frameHeight/4, width_gap = (frameWidth - 3*buttons_width)/4, height_gap = width_gap/3;
            israel_button.setBounds(width_gap, top_buttons_y, buttons_width, buttons_height);
            usa_button.setBounds(width_gap*2 + buttons_width, top_buttons_y, buttons_width, buttons_height);
            germany_button.setBounds(width_gap*3 + buttons_width*2, top_buttons_y, buttons_width, buttons_height);
            greece_button.setBounds(width_gap, top_buttons_y + buttons_height + height_gap, buttons_width, buttons_height);
            sumalia_button.setBounds(width_gap*2 + buttons_width, top_buttons_y + buttons_height + height_gap, buttons_width, buttons_height);
            italy_button.setBounds(width_gap*3 + buttons_width*2, top_buttons_y + buttons_height + height_gap, buttons_width, buttons_height);
            pirate_button.setBounds(width_gap*2 + buttons_width, top_buttons_y + buttons_height*2 + height_gap*2, buttons_width, buttons_height);

            // Add buttons to panel.
            panel.add(israel_button);
            panel.add(usa_button);
            panel.add(germany_button);
            panel.add(greece_button);
            panel.add(sumalia_button);
            panel.add(italy_button);
            panel.add(pirate_button);

            add(panel);

            setVisible(true);
        }
    }
}
