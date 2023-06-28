package Factories;

import Decorator.ColorDecorator;
import Decorator.StatusDecorator;
import Graphics.MyShortcuts;
import Graphics.VehicleAgencyGUI;
import Vehicle.PlayGlider;
import Vehicle.SpyGlider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AirVehicleFactory extends AbstractVehicleFactory{
    /**
     * Constructs an AirVehicleFactory object.
     *
     * @param gui the VehicleAgencyGUI instance associated with the factory
     */
    public AirVehicleFactory(VehicleAgencyGUI gui) {
        super();
        current_gui = gui;
    }
    /**
     * Creates a SpyGlider vehicle.
     *
     * @param purpose the purpose of creating the vehicle
     * @return true if the vehicle is created successfully, false otherwise
     */
    public boolean createSpyGlider(final int purpose) {
        current_gui.clearFrame();
        current_gui.setFontSize(20);
        current_gui.updateCurrentMethod("createSpyGlider");
        current_gui.addReturnButton();

        // Handling the data components.
        if (purpose == add_to_agency_purpose) {
            JLabel greatChoice = new JLabel("Great choice! Please tell me more about the new SpyGlider -");
            greatChoice.setPreferredSize(greatChoiceDimension);
            MyShortcuts.addComponentToPanel(current_gui,greatChoice , current_gui.panel, 50);
        }


        int first_label_y = 130;
        current_gui.addLabelToPanel(new JLabel("Power Source:"), 5, 100,200,50);
        JTextField power_source_field = new JTextField();
        current_gui.panel.add(power_source_field);
        power_source_field.setBounds(5, 140,200,20);

        current_gui.inputValidation();

        JButton add_spyglider_button = new JButton("Add SpyGlider to Agency");
        MyShortcuts.addComponentToPanel(current_gui, add_spyglider_button, current_gui.panel, first_label_y + 370);
        add_spyglider_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String power_source = power_source_field.getText();
                if (power_source.equals(""))
                {
                    current_gui.invalid_input = true;
                    createSpyGlider(purpose);
                    return;
                }
                if (purpose == add_to_agency_purpose)
                {
                    if (!current_gui.getAgency().addVehicleToAgency(new ColorDecorator(new StatusDecorator(new SpyGlider(power_source,current_gui.temp_image)))))
                    {
                        current_gui.invalid_input = true;
                        createSpyGlider(purpose);
                        return;
                    }
                }
                current_gui.frame.remove(current_gui.panel);
                current_gui.createDialogThread(vehicle_was_added_code);

            }
        });

        // Handling the images.
        current_gui.handleImages(purpose, "Assets/Vehicles/spyglider1.png", "Assets/Vehicles/spyglider2.png", "Assets/Vehicles/spyglider3.png");
        current_gui.frame.add(current_gui.panel);
        current_gui.frame.setVisible(true);
        return true;
    }
    /**
     * Creates a PlayGlider vehicle.
     *
     * @param purpose the purpose of creating the vehicle
     * @return true if the vehicle is created successfully, false otherwise
     */
    public boolean createPlayGlider(final int purpose) {
        current_gui.clearFrame();
        current_gui.setFontSize(20);
        current_gui.updateCurrentMethod("createPlayGlider");
        current_gui.addReturnButton();

        // Handling the data components.
        if (purpose == add_to_agency_purpose) {
            JLabel greatChoice = new JLabel("Great choice!");
            greatChoice.setPreferredSize(greatChoiceDimension);
            MyShortcuts.addComponentToPanel(current_gui,greatChoice , current_gui.panel, 50);
        }

        int first_label_y = 130;

        JButton add_playglider_button = new JButton("Add PlayGlider to Agency");
        MyShortcuts.addComponentToPanel(current_gui, add_playglider_button, current_gui.panel, first_label_y + 370);
        add_playglider_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (purpose == add_to_agency_purpose) {
                    if (!current_gui.getAgency().addVehicleToAgency(new ColorDecorator(new StatusDecorator(new PlayGlider(current_gui.temp_image))))) {
                        current_gui.invalid_input = true;
                        createPlayGlider(purpose);
                        return;
                    }
                }

                current_gui.frame.remove(current_gui.panel);
                current_gui.createDialogThread(vehicle_was_added_code);

            }
        });

        current_gui.inputValidation();

        // Handling the images.
        current_gui.handleImages(purpose, "Assets/Vehicles/playglider1.jpg", "Assets/Vehicles/playglider2.jpg", "Assets/Vehicles/playglider3.png");
        current_gui.frame.add(current_gui.panel);
        current_gui.frame.setVisible(true);
        return true;
    }
    /**
     * Retrieves the requested vehicle based on the given vehicle type.
     *
     * @param vehicleType the type of the vehicle to retrieve
     * @return true if the vehicle is successfully retrieved, false otherwise
     */
    public boolean getVehicle(String vehicleType) {
        if (vehicleType.equalsIgnoreCase("PLAY GLIDER")) {
            return createPlayGlider(current_gui.add_to_agency_purpose);
        } else if (vehicleType.equalsIgnoreCase("SPY GLIDER")) {
            return createSpyGlider(current_gui.add_to_agency_purpose);
        }
        return false;
    }
}
