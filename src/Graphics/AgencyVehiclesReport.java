package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;
/**
 The AgencyVehiclesReport class represents a graphical user interface for displaying the inventory of a vehicle agency.
 It extends the JFrame class and implements the Serializable interface.
 */
public class AgencyVehiclesReport extends JFrame implements Serializable {
    public static AgencyVehiclesReport instance;
    public static final Dimension reportScrollDimension = new Dimension(800, 150);
    private VehicleAgencyGUI current_gui;
    private JPanel panel;
    private AgencyScrollPane agencyVehiclesReport;
    /**
     * Returns the reference to the singleton instance of the AgencyVehiclesReport class.
     *
     * @return The reference to the singleton instance of the AgencyVehiclesReport class.
     */
    public static AgencyVehiclesReport getReference() { return instance; }
    /**
     * Constructs a new instance of the AgencyVehiclesReport class.
     * @param gui The VehicleAgencyGUI instance.
     */
    private AgencyVehiclesReport(VehicleAgencyGUI gui) {
        super("Inventory");
        setBackground(new Color(150, 120, 120));
        Initialize(gui);
        setLocationRelativeTo(gui.getFrame());
        setLocation(getX(), gui.getFrame().getY()-getHeight());
    }
    /**
     * Returns the singleton instance of the AgencyVehiclesReport class. If the instance is null, creates a new instance.
     * @param gui The VehicleAgencyGUI instance.
     * @return The singleton instance of the AgencyVehiclesReport class.
     */
    public static AgencyVehiclesReport getInstance(VehicleAgencyGUI gui) {
        if (instance == null)
            instance = new AgencyVehiclesReport(gui);
        return instance;
    }
    /**
     * Initializes the AgencyVehiclesReport instance.
     * @param gui The VehicleAgencyGUI instance.
     */
    private void Initialize(VehicleAgencyGUI gui) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        current_gui = gui;
        setSize(reportScrollDimension);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        agencyVehiclesReport = new AgencyScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, false);
        setVisible(true);
        RefreshFrame(); // Invoke RefreshFrame() after adding the component
    }
    /**
     * Adds a focus listener to the AgencyVehiclesReport instance.
     */
    private void ListenToFocus() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                RefreshFrame();
                setVisible(true);
            }
            @Override
            public void focusLost(FocusEvent e) {}
        });
    }
    /**
     * Refreshes the frame and updates the vehicle inventory.
     */
    public void RefreshFrame() {
        if (current_gui.getAgency().getVehicles().length > 0) {
            remove(agencyVehiclesReport);
            agencyVehiclesReport = agencyVehiclesReport.fillScrollWithVehicles(current_gui, reportScrollDimension);
            agencyVehiclesReport.setSize(new Dimension(current_gui.frameWidth, reportScrollDimension.height));
        } else {
            remove(agencyVehiclesReport);
            agencyVehiclesReport = new AgencyScrollPane(MyShortcuts.pathToLabel(current_gui, "Assets/closed_dealership.png", current_gui.frameWidth, 500), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, false);
            agencyVehiclesReport.setBounds(0, 40, current_gui.frameWidth, reportScrollDimension.height);
        }
        add(agencyVehiclesReport);
        getContentPane().repaint();
        ListenToFocus();
        setVisible(true);
    }
}

