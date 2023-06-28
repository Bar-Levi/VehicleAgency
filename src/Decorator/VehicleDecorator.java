package Decorator;

import javax.swing.*;
import java.io.Serializable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents an abstract decorator for vehicles.
 */
public abstract class VehicleDecorator implements IVehicle, Serializable {
    protected IVehicle decoratedVehicle;

    /**
     * Constructs a VehicleDecorator object with the specified decorated vehicle.
     *
     * @param decoratedVehicle the vehicle to decorate
     */
    public VehicleDecorator(IVehicle decoratedVehicle) {
        this.decoratedVehicle = decoratedVehicle;
    }

    /**
     * Retrieves the distance traveled by the decorated vehicle.
     *
     * @return the distance traveled
     */
    @Override
    public double getTraveled() {
        return decoratedVehicle.getTraveled();
    }

    /**
     * Sets the distance traveled by the decorated vehicle.
     *
     * @param t the distance to set
     * @return true if the distance is set successfully, false otherwise
     */
    @Override
    public boolean setTraveled(double t) {
        return decoratedVehicle.setTraveled(t);
    }

    /**
     * Retrieves the condition for user confirmation of buying the vehicle.
     *
     * @return the condition for user confirmation
     */
    @Override
    public Condition getUserConfirmedBuying() {
        return decoratedVehicle.getUserConfirmedBuying();
    }

    /**
     * Retrieves the condition for user finishing the testing of the vehicle.
     *
     * @return the condition for user finishing the testing
     */
    @Override
    public Condition getUserFinishedTesting() {
        return decoratedVehicle.getUserFinishedTesting();
    }

    /**
     * Retrieves the vehicle lock used for synchronization.
     *
     * @return the vehicle lock
     */
    @Override
    public ReentrantLock getVehicleLock() {
        return decoratedVehicle.getVehicleLock();
    }

    /**
     * Retrieves the selling lock used for synchronization.
     *
     * @return the selling lock
     */
    @Override
    public ReentrantLock getSellingLock() {
        return decoratedVehicle.getSellingLock();
    }

    /**
     * Retrieves the image icon associated with the decorated vehicle.
     *
     * @return the image icon
     */
    @Override
    public ImageIcon getImage() {
        return decoratedVehicle.getImage();
    }

    /**
     * Retrieves the current count of drivers for the decorated vehicle.
     *
     * @return the current count of drivers
     */
    @Override
    public int getCurrentDriversCount() {
        return decoratedVehicle.getCurrentDriversCount();
    }

    /**
     * Sets the current count of drivers for the decorated vehicle.
     *
     * @param count the count of drivers to set
     * @return true if the count is set successfully, false otherwise
     */
    @Override
    public boolean setCurrentDriversCount(int count) {
        return decoratedVehicle.setCurrentDriversCount(count);
    }

    /**
     * Retrieves the underlying IVehicle object of the decorated vehicle.
     *
     * @return the underlying IVehicle object
     */
    @Override
    public IVehicle getIvehicle() {
        return decoratedVehicle;
    }
}
