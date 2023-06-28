package Decorator;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import Vehicle.Vehicle;

public class ColorDecorator extends VehicleDecorator implements Serializable {

    public Color pickedColor;
    /**
     * Constructs a ColorDecorator object.
     * @param decoratedVehicle The vehicle to be decorated.
     */
    public ColorDecorator(IVehicle decoratedVehicle) {
        super(decoratedVehicle);
        setVehicleColor();
    }
    /**
     * Sets the color for the decorated vehicle using the JColorChooser.
     */
    public void setVehicleColor() {
        pickedColor = JColorChooser.showDialog(null,"Choose your color!",Color.white);

    }
    /**
     * Retrieves the type of the decorated vehicle.
     * @return The type of the decorated vehicle.
     */
    @Override
    public String getVehicleType() {
        return decoratedVehicle.getVehicleType();
    }
    /**
     * Retrieves the underlying Vehicle object of the decorated vehicle.
     * @return The underlying Vehicle object.
     */
    @Override
    public Vehicle getVehicle() {
        return decoratedVehicle.getVehicle();
    }
    /**
     * Returns a string representation of the decorated vehicle.
     * @return A string representation of the decorated vehicle.
     */
    @Override
    public String toString() {
        return decoratedVehicle.toString();
    }

}
