package Factories;

import Decorator.ColorDecorator;
import Decorator.StatusDecorator;
import Graphics.MyShortcuts;
import Graphics.VehicleAgencyGUI;
import Vehicle.CruiseShip;
import Vehicle.Frigate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SeaVehicleFactory extends AbstractVehicleFactory{
    /**
     * Constructs a SeaVehicleFactory object.
     *
     * @param gui The VehicleAgencyGUI object associated with the factory.
     */
    public SeaVehicleFactory(VehicleAgencyGUI gui) { super(); current_gui = gui; }
    /**
     * Creates a Frigate and adds it to the agency.
     *
     * @param purpose The purpose of creating the Frigate.
     * @return True if the Frigate is created and added successfully, false otherwise.
     */
    public boolean createFrigate(final int purpose) {
        current_gui.clearFrame();
        current_gui.setFontSize(20);
        current_gui.updateCurrentMethod("createFrigate");
        current_gui.addReturnButton();

        // Handling the data components.
        if (purpose == add_to_agency_purpose) {
            JLabel greatChoice = new JLabel("Great choice! Please tell me more about the new Frigate -");
            greatChoice.setPreferredSize(greatChoiceDimension);
            MyShortcuts.addComponentToPanel(current_gui,greatChoice , current_gui.panel, 50);
        }


        int first_label_y = 130;
        current_gui.addLabelToPanel(new JLabel("Frigate Model:"), 5, 100,200,50);
        JTextField frigate_model_field = new JTextField();
        current_gui.panel.add(frigate_model_field);
        frigate_model_field.setBounds(5, 140,200,20);

        current_gui.addLabelToPanel(new JLabel("Max Speed:"), 5, 185,200,50);
        JTextField max_speed_field = new JTextField();
        current_gui.panel.add(max_speed_field);
        max_speed_field.setBounds(5, 225,200,20);

        current_gui.addLabelToPanel(new JLabel("Max Passengers:"), 5, 275,300,50);
        JTextField max_passengers_field = new JTextField();
        current_gui.panel.add(max_passengers_field);
        max_passengers_field.setBounds(5, 315,200,20);

        JRadioButton yes_button = new JRadioButton("Moving with wind");
        JRadioButton no_button = new JRadioButton("Not moving with wind");
        ButtonGroup button_group = new ButtonGroup();
        button_group.add(yes_button);
        button_group.add(no_button);

        yes_button.setBounds(10, 350,200,30);
        no_button.setBounds(10, 390,250,30);
        yes_button.setFont(current_gui.font);
        no_button.setFont(current_gui.font);
        current_gui.panel.add(yes_button);
        current_gui.panel.add(no_button);

        current_gui.inputValidation();

        JButton add_frigate_button = new JButton("Add Frigate to Agency");
        MyShortcuts.addComponentToPanel(current_gui, add_frigate_button, current_gui.panel, first_label_y + 370);
        add_frigate_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max_speed, max_passengers;
                boolean moving_with_wind;
                String vehicle_model;
                vehicle_model = frigate_model_field.getText();
                try {
                    max_speed = Integer.parseInt(max_speed_field.getText());
                    max_passengers = Integer.parseInt(max_passengers_field.getText());
                    moving_with_wind = yes_button.isSelected();
                    if (vehicle_model.equals("") || !(yes_button.isSelected() || no_button.isSelected()))
                        throw new Exception();
                    if (purpose == add_to_agency_purpose)
                    {
                        if (!current_gui.getAgency().addVehicleToAgency(new ColorDecorator(new StatusDecorator(new Frigate(vehicle_model, max_speed, max_passengers, moving_with_wind, current_gui.temp_image)))))
                        {
                            current_gui.invalid_input = true;
                            createFrigate(purpose);
                            return;
                        }
                    }

                }
                catch(Exception exception) {
                    current_gui.invalid_input = true;
                    createFrigate(purpose);
                    return;
                }

                current_gui.frame.remove(current_gui.panel);
                current_gui.createDialogThread(vehicle_was_added_code);
            }
        });

        //Handling the images data.
        current_gui.handleImages(purpose, "Assets/Vehicles/frigate1.jpg", "Assets/Vehicles/frigate2.png", "Assets/Vehicles/frigate3.png");
        current_gui.frame.add(current_gui.panel);
        current_gui.frame.setVisible(true);
        return true;
    }
    /**
     * Creates a CruiseShip and adds it to the agency.
     *
     * @param purpose The purpose of creating the CruiseShip.
     * @return True if the CruiseShip is created and added successfully, false otherwise.
     */
    public boolean createCruiseShip(final int purpose) {
        current_gui.clearFrame();
        current_gui.setFontSize(20);
        current_gui.updateCurrentMethod("createCruiseShip");
        current_gui.addReturnButton();
        if (purpose == add_to_agency_purpose) {
            JLabel greatChoice = new JLabel("Great choice! Please tell me more about the new CruiseShip -");
            greatChoice.setPreferredSize(greatChoiceDimension);
            MyShortcuts.addComponentToPanel(current_gui,greatChoice , current_gui.panel, 50);
        }

        int first_label_y = 100;

        // Handling the data components.
        current_gui.addLabelToPanel(new JLabel("Model:"), 20, first_label_y, 150, 30);
        JTextField amphibious_model_field = new JTextField();
        amphibious_model_field.setBounds(3, first_label_y + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(amphibious_model_field);

        current_gui.addLabelToPanel(new JLabel("Max Passengers:"), 20, first_label_y + 60, 250, 30);
        JTextField max_passengers_field = new JTextField();
        max_passengers_field.setBounds(3, first_label_y + 60 + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(max_passengers_field);

        current_gui.addLabelToPanel(new JLabel("Max Speed:"), 20, first_label_y + 2*60, 150, 30);
        JTextField max_speed_field = new JTextField();
        max_speed_field.setBounds(3, first_label_y + 2*60 + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(max_speed_field);

        current_gui.addLabelToPanel(new JLabel("Country flag:"), 20, first_label_y + 3*60, 250, 30);
        JTextField country_field = new JTextField();
        current_gui.panel.add(country_field);
        country_field.setBounds(3, first_label_y + 3*60 + 30, 150, current_gui.font.getSize());

        current_gui.addLabelToPanel(new JLabel("Fuel usage:"), 20, first_label_y + 4*60, 250, 30);
        JTextField fuel_usage_field = new JTextField();
        current_gui.panel.add(fuel_usage_field);
        fuel_usage_field.setBounds(3, first_label_y + 4*60 + 30, 150, current_gui.font.getSize());

        current_gui.addLabelToPanel(new JLabel("Engine lifetime:"), 20, first_label_y + 5*60, 250, 30);
        JTextField engine_lifetime_field = new JTextField();
        current_gui.panel.add(engine_lifetime_field);
        engine_lifetime_field.setBounds(3, first_label_y + 5*60 + 30, 150, current_gui.font.getSize());

        current_gui.inputValidation();

        JButton add_amphibious_button = new JButton("Add CruiseShip to Agency");
        MyShortcuts.addComponentToPanel(current_gui, add_amphibious_button, current_gui.panel, first_label_y + 420);
        add_amphibious_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max_speed, fuel_usage, engine_lifetime, max_passengers;
                String vehicle_model, country_flag;
                vehicle_model = amphibious_model_field.getText();
                country_flag = country_field.getText();
                try {
                    max_passengers = Integer.parseInt(max_passengers_field.getText());
                    max_speed = Integer.parseInt(max_speed_field.getText());
                    engine_lifetime = Integer.parseInt(engine_lifetime_field.getText());
                    fuel_usage = Integer.parseInt(fuel_usage_field.getText());
                }
                catch(Exception exception) {
                    current_gui.invalid_input = true;
                    createCruiseShip(purpose);
                    return;
                }
                if (purpose == add_to_agency_purpose)
                    if (!current_gui.getAgency().addVehicleToAgency(new ColorDecorator(new StatusDecorator(new CruiseShip(vehicle_model, max_passengers, max_speed, country_flag, fuel_usage, engine_lifetime, current_gui.temp_image)))))
                    {
                        current_gui.invalid_input = true;
                        createCruiseShip(purpose);
                        return;
                    }

                current_gui.frame.remove(current_gui.panel);
                current_gui.createDialogThread(vehicle_was_added_code);
            }
        });

        // Handling images.
        current_gui.handleImages(purpose, "Assets/Vehicles/cruise1.jpg","Assets/Vehicles/cruise2.jpg", "Assets/Vehicles/cruise3.jpg");
        current_gui.frame.add(current_gui.panel);
        current_gui.frame.setVisible(true);
        return true;
    }
    /**
     * Gets the specified vehicle type and invokes the corresponding creation method.
     *
     * @param vehicleType The type of vehicle to create.
     * @return True if the vehicle is created successfully, false otherwise.
     */
    public boolean getVehicle(String vehicleType){
        if(vehicleType.equalsIgnoreCase("FRIGATE")) { createFrigate(current_gui.add_to_agency_purpose); }
        else if(vehicleType.equalsIgnoreCase("CRUISE SHIP")) { createCruiseShip(current_gui.add_to_agency_purpose); }
        return false;
    }
}
