package Factories;

import Decorator.ColorDecorator;
import Decorator.StatusDecorator;
import Graphics.MyShortcuts;
import Graphics.VehicleAgencyGUI;
import Vehicle.ElectricBicycle;
import Vehicle.Jeep;
import Vehicle.ManualBicycle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LandVehicleFactory extends AbstractVehicleFactory{
    /**
     * Constructs a LandVehicleFactory object.
     *
     * @param gui The VehicleAgencyGUI object associated with the factory.
     */
    public LandVehicleFactory(VehicleAgencyGUI gui) {
        super();
        current_gui = gui;
    }
    /**
     * Creates a Jeep with user input and adds it to the agency.
     * @param purpose The purpose of creating the Jeep.
     * @return True if the Jeep is successfully created and added to the agency, false otherwise.
     */
    public boolean createJeep(final int purpose) {
        current_gui.clearFrame();
        current_gui.setFontSize(20);
        current_gui.updateCurrentMethod("createJeep");
        current_gui.addReturnButton();

        // Handling the data components.
        if (purpose == add_to_agency_purpose) {
            JLabel greatChoice = new JLabel("Great choice! Please tell me more about the new Jeep -");
            greatChoice.setPreferredSize(greatChoiceDimension);
            MyShortcuts.addComponentToPanel(current_gui, greatChoice, current_gui.panel, 50);

        }

        current_gui.addLabelToPanel(new JLabel("Jeep Model:"), 20, first_label_y, 150, 30);
        JTextField jeep_model_field = new JTextField();
        jeep_model_field.setBounds(3, first_label_y + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(jeep_model_field);

        current_gui.addLabelToPanel(new JLabel("Max Speed:"), 20, first_label_y + 70, 150, 30);
        JTextField max_speed_field = new JTextField();
        max_speed_field.setBounds(3, first_label_y + 70 + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(max_speed_field);

        current_gui.addLabelToPanel(new JLabel("Fuel Usage:"), 20, first_label_y + 2*70, 150, 30);
        JTextField fuel_usage_field = new JTextField();
        fuel_usage_field.setBounds(3, first_label_y + 2*70 + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(fuel_usage_field);

        current_gui.addLabelToPanel(new JLabel("Engine Lifetime:"), 20, first_label_y + 3*70, 250, 30);
        JTextField engine_lifetime_field = new JTextField();
        current_gui.panel.add(engine_lifetime_field);
        engine_lifetime_field.setBounds(3, first_label_y + 3*70 + 30, 150, current_gui.font.getSize());

        current_gui.inputValidation();

        JButton add_jeep_button = new JButton("Add Jeep to Agency");
        MyShortcuts.addComponentToPanel(current_gui, add_jeep_button, current_gui.panel, first_label_y + 370);
        add_jeep_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max_speed, fuel_usage, engine_lifetime;
                String vehicle_model;
                vehicle_model = jeep_model_field.getText();
                try {
                    if (vehicle_model.equals(""))
                        throw new Exception();
                    max_speed = Integer.parseInt(max_speed_field.getText());
                    fuel_usage = Integer.parseInt(fuel_usage_field.getText());
                    engine_lifetime = Integer.parseInt(engine_lifetime_field.getText());
                }
                catch(Exception exception) {
                    current_gui.invalid_input = true;
                    createJeep(purpose);
                    return;
                }
                if (purpose == add_to_agency_purpose)
                    if (!current_gui.getAgency().addVehicleToAgency(new ColorDecorator(new StatusDecorator(new Jeep(vehicle_model, max_speed, fuel_usage, engine_lifetime, current_gui.temp_image)))))
                    {
                        current_gui.invalid_input = true;
                        createJeep(purpose);
                        return;
                    }
                current_gui.frame.remove(current_gui.panel);
                current_gui.createDialogThread(vehicle_was_added_code);
            }
        });

        //Handling the images data.
        current_gui.handleImages(purpose, "Assets/Vehicles/jeep1.png", "Assets/Vehicles/jeep2.jpg", "Assets/Vehicles/jeep3.jpg");
        current_gui.frame.add(current_gui.panel);
        current_gui.frame.setVisible(true);
        return true;
    }
    /**
     * Creates a Manual Bicycle with user input and adds it to the agency.
     * @param purpose The purpose of creating the Manual Bicycle.
     * @return True if the Manual Bicycle is successfully created and added to the agency, false otherwise.
     */
    public boolean createManualBicycle(final int purpose) {
        current_gui.clearFrame();
        current_gui.setFontSize(20);
        current_gui.updateCurrentMethod("createManualBicycle");
        current_gui.addReturnButton();
        if (purpose == add_to_agency_purpose) {
            JLabel greatChoice = new JLabel("Great choice! Please tell me more about the new ManualBicycle -");
            greatChoice.setPreferredSize(greatChoiceDimension);
            MyShortcuts.addComponentToPanel(current_gui,greatChoice , current_gui.panel, 50);
        }

        int first_label_y = 100;

        // Handling the data components.
        current_gui.addLabelToPanel(new JLabel("Bicycle Model:"), 20, first_label_y, 150, 30);
        JTextField bicycle_model_field = new JTextField();
        bicycle_model_field.setBounds(3, first_label_y + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(bicycle_model_field);

        current_gui.addLabelToPanel(new JLabel("Max Speed:"), 20, first_label_y + 70, 150, 30);
        JTextField max_speed_field = new JTextField();
        max_speed_field.setBounds(3, first_label_y + 70 + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(max_speed_field);

        current_gui.addLabelToPanel(new JLabel("Max Passengers:"), 20, first_label_y + 2*70, 250, 30);
        JTextField max_passengers_field = new JTextField();
        max_passengers_field.setBounds(3, first_label_y + 2*70 + 30, 250, current_gui.font.getSize());
        current_gui.panel.add(max_passengers_field);


        current_gui.addLabelToPanel(new JLabel("Road Type:"), 20, first_label_y + 3*70, 250, 30);


        JRadioButton paved_button = new JRadioButton("Paved");
        JRadioButton dirt_button = new JRadioButton("Dirt");
        ButtonGroup button_group = new ButtonGroup();
        button_group.add(paved_button);
        button_group.add(dirt_button);

        paved_button.setBounds(10, first_label_y + 3*70 + 30,100,30);
        dirt_button.setBounds(10, first_label_y + 3*70 + 60,100,30);
        paved_button.setFont(current_gui.font);
        dirt_button.setFont(current_gui.font);
        current_gui.panel.add(paved_button);
        current_gui.panel.add(dirt_button);

        current_gui.inputValidation();

        JButton add_bicycle_button = new JButton("Add ManualBicycle to Agency");
        MyShortcuts.addComponentToPanel(current_gui, add_bicycle_button, current_gui.panel, first_label_y + 370);
        add_bicycle_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max_speed, max_passengers;
                String vehicle_model, road_type;
                boolean isPaved;
                vehicle_model = bicycle_model_field.getText();
                isPaved = paved_button.isSelected();
                try {
                    if (!(paved_button.isSelected() || dirt_button.isSelected()))
                        throw new Exception();
                    max_passengers = Integer.parseInt(max_passengers_field.getText());
                    max_speed = Integer.parseInt(max_speed_field.getText());
                    if (isPaved) road_type = "Paved";
                    else road_type = "Dirt";
                }
                catch(Exception exception) {
                    current_gui.invalid_input = true;
                    createManualBicycle(purpose);
                    return;
                }
                if (purpose == add_to_agency_purpose)
                    if (!current_gui.getAgency().addVehicleToAgency(new ColorDecorator(new StatusDecorator(new ManualBicycle(vehicle_model, max_passengers, max_speed, road_type ,current_gui.temp_image)))))
                    {
                        current_gui.invalid_input = true;
                        createManualBicycle(purpose);
                        return;
                    }
                current_gui.frame.remove(current_gui.panel);
                current_gui.createDialogThread(vehicle_was_added_code);
            }
        });

        // Handling the images.
        current_gui.handleImages(purpose, "Assets/Vehicles/bicycle1.jpg", "Assets/Vehicles/bicycle2.jpg", "Assets/Vehicles/bicycle3.jpg");
        current_gui.frame.add(current_gui.panel);
        current_gui.frame.setVisible(true);
        return true;
    }
    /**
     * Creates an Electric Bicycle with user input and adds it to the agency.
     * @param purpose The purpose of creating the Electric Bicycle.
     * @return True if the Electric Bicycle is successfully created and added to the agency, false otherwise.
     */
    public boolean createElectricBicycle(final int purpose) {
        current_gui.clearFrame();
        current_gui.setFontSize(20);
        current_gui.updateCurrentMethod("createElectricBicycle");
        current_gui.addReturnButton();
        if (purpose == add_to_agency_purpose) {
            JLabel greatChoice = new JLabel("Great choice! Please tell me more about the new ElectricBicycle -");
            greatChoice.setPreferredSize(greatChoiceDimension);
            MyShortcuts.addComponentToPanel(current_gui,greatChoice , current_gui.panel, 50);
        }

        int first_label_y = 100;

        // Handling the data components.
        current_gui.addLabelToPanel(new JLabel("Bicycle Model:"), 20, first_label_y, 150, 30);
        JTextField bicycle_model_field = new JTextField();
        bicycle_model_field.setBounds(3, first_label_y + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(bicycle_model_field);

        current_gui.addLabelToPanel(new JLabel("Max Speed:"), 20, first_label_y + 70, 150, 30);
        JTextField max_speed_field = new JTextField();
        max_speed_field.setBounds(3, first_label_y + 70 + 30, 150, current_gui.font.getSize());
        current_gui.panel.add(max_speed_field);

        current_gui.addLabelToPanel(new JLabel("Max Passengers:"), 20, first_label_y + 2*70, 250, 30);
        JTextField max_passengers_field = new JTextField();
        max_passengers_field.setBounds(3, first_label_y + 2*70 + 30, 250, current_gui.font.getSize());
        current_gui.panel.add(max_passengers_field);

        current_gui.addLabelToPanel(new JLabel("Engine Lifetime:"), 20, first_label_y + 3*70, 250, 30);
        JTextField engine_lifetime_field = new JTextField();
        current_gui.panel.add(engine_lifetime_field);
        engine_lifetime_field.setBounds(3, first_label_y + 3*70 + 30, 150, current_gui.font.getSize());


        current_gui.addLabelToPanel(new JLabel("Road Type:"), 20, first_label_y + 4*70, 250, 30);
        JRadioButton paved_button = new JRadioButton("Paved");
        JRadioButton dirt_button = new JRadioButton("Dirt");
        ButtonGroup button_group = new ButtonGroup();
        button_group.add(paved_button);
        button_group.add(dirt_button);

        paved_button.setBounds(10, first_label_y + 4*70 + 30,100,30);
        dirt_button.setBounds(10, first_label_y + 4*70 + 60,100,30);
        paved_button.setFont(current_gui.font);
        dirt_button.setFont(current_gui.font);
        current_gui.panel.add(paved_button);
        current_gui.panel.add(dirt_button);


        current_gui.inputValidation();

        JButton add_bicycle_button = new JButton("Add ElectricBicycle to Agency");
        MyShortcuts.addComponentToPanel(current_gui, add_bicycle_button, current_gui.panel, first_label_y + 370);
        add_bicycle_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max_speed, max_passengers, engine_lifetime;
                String vehicle_model, road_type;
                boolean isPaved;
                vehicle_model = bicycle_model_field.getText();
                isPaved = paved_button.isSelected();
                try {
                    if (!(paved_button.isSelected() || dirt_button.isSelected()))
                        throw new Exception();
                    max_passengers = Integer.parseInt(max_passengers_field.getText());
                    max_speed = Integer.parseInt(max_speed_field.getText());
                    engine_lifetime = Integer.parseInt(engine_lifetime_field.getText());

                    if (isPaved) road_type = "Paved";
                    else road_type = "Dirt";
                }
                catch(Exception exception) {
                    current_gui.invalid_input = true;
                    createElectricBicycle(purpose);
                    return;
                }
                if (purpose == add_to_agency_purpose)
                    if (!current_gui.getAgency().addVehicleToAgency(new ColorDecorator(new StatusDecorator(new ElectricBicycle(vehicle_model, max_passengers, max_speed, road_type , engine_lifetime, current_gui.temp_image)))))
                    {
                        current_gui.invalid_input = true;
                        createElectricBicycle(purpose);
                        return;
                    }
                current_gui.frame.remove(current_gui.panel);
                current_gui.createDialogThread(vehicle_was_added_code);
            }
        });

        // Handling the images.
        current_gui.handleImages(purpose, "Assets/Vehicles/electricbicycle1.jpg", "Assets/Vehicles/electricbicycle2.png", "Assets/Vehicles/electricbicycle3.jpg");
        current_gui.frame.add(current_gui.panel);
        current_gui.frame.setVisible(true);
        return true;
    }
    /**
     * Retrieves a vehicle of the specified type from the factory.
     * @param vehicleType The type of vehicle to retrieve.
     * @return True if the vehicle is successfully retrieved, false otherwise.
     */
    public boolean getVehicle(String vehicleType){
        if(vehicleType.equalsIgnoreCase("JEEP")) { createJeep(current_gui.add_to_agency_purpose); }
        else if(vehicleType.equalsIgnoreCase("ELECTRIC BICYCLE")) { createElectricBicycle(current_gui.add_to_agency_purpose); }
        else if(vehicleType.equalsIgnoreCase("MANUAL BICYCLE")) { createManualBicycle(current_gui.add_to_agency_purpose); }
        return false;
    }
}
