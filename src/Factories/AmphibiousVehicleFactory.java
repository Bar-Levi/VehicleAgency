package Factories;

import Decorator.ColorDecorator;
import Decorator.StatusDecorator;
import Graphics.MyShortcuts;
import Graphics.VehicleAgencyGUI;
import Vehicle.AmphibiousVehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AmphibiousVehicleFactory extends AbstractVehicleFactory{
    /**
     * Constructs an AmphibiousVehicleFactory object.
     *
     * @param gui the VehicleAgencyGUI instance associated with the factory
     */
    public AmphibiousVehicleFactory(VehicleAgencyGUI gui) { super(); current_gui = gui;}
    /**
     * Creates an AmphibiousVehicle based on user input and adds it to the agency.
     *
     * @param purpose the purpose of creating the AmphibiousVehicle
     * @return true if the AmphibiousVehicle is successfully created and added to the agency, false otherwise
     */
    public boolean createAmphibious(final int purpose) {
        current_gui.clearFrame();
        current_gui.setFontSize(20);
        current_gui.updateCurrentMethod("createAmphibious");
        current_gui.addReturnButton();
        if (purpose == add_to_agency_purpose) {
            JLabel greatChoice = new JLabel("Great choice! Please tell me more about the new Amphibious -");
            greatChoice.setPreferredSize(greatChoiceDimension);
            MyShortcuts.addComponentToPanel(current_gui,greatChoice , current_gui.panel, 50);
        }
        current_gui.setFontSize(15);

        int first_label_y = 50;

        // Handling the data components.
        current_gui.addLabelToPanel(new JLabel("Model:"), 20, first_label_y, 150, 30);
        JTextField amphibious_model_field = new JTextField();
        amphibious_model_field.setBounds(3, first_label_y + 30, 150, (int)(current_gui.font.getSize()*1.5));
        current_gui.panel.add(amphibious_model_field);

        current_gui.addLabelToPanel(new JLabel("Max Passengers:"), 20, first_label_y + 50, 250, 30);
        JTextField max_passengers_field = new JTextField();
        max_passengers_field.setBounds(3, first_label_y + 50 + 30, 150, (int)(current_gui.font.getSize()*1.5));
        current_gui.panel.add(max_passengers_field);

        current_gui.addLabelToPanel(new JLabel("Max Speed:"), 20, first_label_y + 2*50, 150, 30);
        JTextField max_speed_field = new JTextField();
        max_speed_field.setBounds(3, first_label_y + 2*50 + 30, 150, (int)(current_gui.font.getSize()*1.5));
        current_gui.panel.add(max_speed_field);

        current_gui.addLabelToPanel(new JLabel("Country flag:"), 20, first_label_y + 3*50, 250, 30);
        JTextField country_field = new JTextField();
        current_gui.panel.add(country_field);
        country_field.setBounds(3, first_label_y + 3*50 + 30, 150, (int)(current_gui.font.getSize()*1.5));

        current_gui.addLabelToPanel(new JLabel("Wheels number:"), 20, first_label_y + 4*50, 250, 30);
        JTextField wheels_num_field = new JTextField();
        current_gui.panel.add(wheels_num_field);
        wheels_num_field.setBounds(3, first_label_y + 4*50 + 30, 150, (int)(current_gui.font.getSize()*1.5));

        current_gui.addLabelToPanel(new JLabel("Fuel usage:"), 20, first_label_y + 5*50, 250, 30);
        JTextField fuel_usage_field = new JTextField();
        current_gui.panel.add(fuel_usage_field);
        fuel_usage_field.setBounds(3, first_label_y + 5*50 + 30, 150, (int)(current_gui.font.getSize()*1.5));

        current_gui.addLabelToPanel(new JLabel("Engine lifetime:"), 20, first_label_y + 6*50, 250, 30);
        JTextField engine_lifetime_field = new JTextField();
        current_gui.panel.add(engine_lifetime_field);
        engine_lifetime_field.setBounds(3, first_label_y + 6*50 + 30, 150, (int)(current_gui.font.getSize()*1.5));

        current_gui.inputValidation();

        current_gui.addLabelToPanel(new JLabel("Moving with wind:"), 20, first_label_y + 7*50, 400, 30);
        JRadioButton yes_button = new JRadioButton("Moving with wind");
        JRadioButton no_button = new JRadioButton("Not moving with wind");
        ButtonGroup button_group = new ButtonGroup();
        button_group.add(yes_button);
        button_group.add(no_button);
        yes_button.setBounds(10, first_label_y + 7*50 + 30,150,30);
        no_button.setBounds(10, first_label_y + 7*50 + 30 + 30,250,30);
        yes_button.setFont(current_gui.font);
        no_button.setFont(current_gui.font);
        current_gui.panel.add(yes_button);
        current_gui.panel.add(no_button);

        current_gui.setFontSize(20);
        JButton add_amphibious_button = new JButton("Add Amphibious vehicle to Agency");
        MyShortcuts.addComponentToPanel(current_gui, add_amphibious_button, current_gui.panel, first_label_y + 450);
        add_amphibious_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max_speed, fuel_usage, engine_lifetime, max_passengers, wheels_number;
                String vehicle_model, country_flag;
                boolean moving_with_wind;
                vehicle_model = amphibious_model_field.getText();
                country_flag = country_field.getText();
                try {
                    wheels_number = Integer. parseInt(wheels_num_field.getText());
                    max_passengers = Integer.parseInt(max_passengers_field.getText());
                    max_speed = Integer.parseInt(max_speed_field.getText());
                    engine_lifetime = Integer.parseInt(engine_lifetime_field.getText());
                    fuel_usage = Integer.parseInt(fuel_usage_field.getText());
                    moving_with_wind = yes_button.isSelected();
                    if (!(yes_button.isSelected() || no_button.isSelected()))
                        throw new Exception();
                }
                catch(Exception exception) {
                    current_gui.invalid_input = true;
                    createAmphibious(purpose);
                    return;
                }
                if (purpose == add_to_agency_purpose)
                    if (!current_gui.getAgency().addVehicleToAgency(new ColorDecorator(new StatusDecorator(new AmphibiousVehicle(vehicle_model, max_passengers, max_speed, moving_with_wind, country_flag, wheels_number, fuel_usage, engine_lifetime, current_gui.temp_image)))))
                    {
                        current_gui.invalid_input = true;
                        createAmphibious(purpose);
                        return;
                    }

                current_gui.frame.remove(current_gui.panel);
                current_gui.createDialogThread(vehicle_was_added_code);

            }
        });

        // Handling images.
        current_gui.handleImages(purpose, "Assets/Vehicles/amphibious1.png", "Assets/Vehicles/amphibious2.jpg", "Assets/Vehicles/amphibious3.png");
        current_gui.frame.add(current_gui.panel);
        current_gui.frame.setVisible(true);
        return true;
    }
    /**
     * Retrieves the requested vehicle type and initiates the creation process.
     *
     * @param vehicleType the type of vehicle to be created
     * @return true if the vehicle type is supported and creation process is initiated, false otherwise
     */
    public boolean getVehicle(String vehicleType){
        if (vehicleType.equalsIgnoreCase("AMPHIBIOUS")) {
            createAmphibious(current_gui.add_to_agency_purpose);
        }
        return false;
    }
}
