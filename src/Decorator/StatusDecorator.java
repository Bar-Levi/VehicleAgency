package Decorator;

import Graphics.VehicleAgencyGUI;
import Vehicle.Vehicle;

/**
 * Represents a decorator that adds a status to a decorated vehicle.
 */
public class StatusDecorator extends VehicleDecorator {
    private String status = "Available";

    /**
     * Constructs a StatusDecorator object with the specified decorated vehicle.
     *
     * @param decoratedVehicle the vehicle to decorate with status
     */
    public StatusDecorator(IVehicle decoratedVehicle) {
        super(decoratedVehicle);
    }

    /**
     * Gets the status of the decorated vehicle.
     *
     * @return the status of the vehicle
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the decorated vehicle and shows the menu in the GUI.
     *
     * @param newStatus the new status to set
     * @param gui       the GUI object to show the menu
     */
    public void setStatus(final String newStatus, VehicleAgencyGUI gui) {
        status = newStatus;
        gui.showMenu();
    }

    /**
     * Retrieves the type of the decorated vehicle.
     *
     * @return the type of the vehicle
     */
    @Override
    public String getVehicleType() {
        return decoratedVehicle.getVehicleType();
    }

    /**
     * Retrieves the underlying vehicle object of the decorated vehicle.
     *
     * @return the underlying vehicle object
     */
    @Override
    public Vehicle getVehicle() {
        return decoratedVehicle.getVehicle();
    }

    /**
     * Returns a string representation of the decorated vehicle including the status.
     *
     * @return a string representation of the vehicle
     */
    @Override
    public String toString() {
        return decoratedVehicle.toString() + "\nStatus: " + getStatus();
    }
}
