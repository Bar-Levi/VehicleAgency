package Factories;


import Graphics.VehicleAgencyGUI;

import java.awt.*;
import java.io.Serializable;

/**
 * An abstract factory for creating vehicles.
 */
public abstract class AbstractVehicleFactory implements Serializable {
    protected VehicleAgencyGUI current_gui;
    public final Dimension greatChoiceDimension = new Dimension(500, 45);
    public final int vehicle_was_added_code = -8;
    public final int add_to_agency_purpose = -12;

    protected int first_label_y = 100;

    /**
     * Constructs an AbstractVehicleFactory object.
     */
    public AbstractVehicleFactory() {}

    /**
     * Gets a vehicle of the specified type.
     *
     * @param type the type of vehicle to get
     * @return true if the vehicle is retrieved successfully, false otherwise
     */
    public abstract boolean getVehicle(String type);
}
