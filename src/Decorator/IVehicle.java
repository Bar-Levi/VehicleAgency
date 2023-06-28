package Decorator;

import javax.swing.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import Vehicle.*;

public interface IVehicle{
    /**
     * Retrieves the ReentrantLock object associated with the vehicle.
     * @return The ReentrantLock object.
     */
    ReentrantLock getVehicleLock();
    /**
     * Retrieves the ReentrantLock object associated with the vehicle selling process.
     * @return The ReentrantLock object.
     */
    ReentrantLock getSellingLock();

    /**
     * Retrieves the Condition object indicating user finished testing the vehicle.
     * @return The Condition object.
     */
    Condition getUserFinishedTesting();

    /**
     * Retrieves the Condition object indicating user confirmed buying the vehicle.
     * @return The Condition object.
     */
    Condition getUserConfirmedBuying();

    /**
     * Returns a string representation of the vehicle.
     * @return A string representation of the vehicle.
     */
    String toString();

    /**
     * Sets the distance traveled by the vehicle.
     * @param t The distance traveled.
     * @return True if the distance is set successfully, false otherwise.
     */
    boolean setTraveled(final double t);

    /**
     * Retrieves the distance traveled by the vehicle.
     * @return The distance traveled by the vehicle.
     */
    double getTraveled();

    /**
     * Retrieves the current count of drivers in the vehicle.
     * @return The current count of drivers.
     */
    int getCurrentDriversCount();

    /**
     * Sets the current count of drivers in the vehicle.
     * @param count The current count of drivers.
     * @return True if the count is set successfully, false otherwise.
     */
    boolean setCurrentDriversCount(final int count);

    /**
     * Checks if the vehicle is equal to the given object.
     * @param o The object to compare.
     * @return True if the vehicle is equal to the given object, false otherwise.
     */
    boolean equals(final Object o);

    /**
     * Retrieves the image icon associated with the vehicle.
     * @return The image icon.
     */
    ImageIcon getImage();

    /**
     * Retrieves the type of the vehicle.
     * @return The type of the vehicle.
     */
    String getVehicleType();

    /**
     * Retrieves the underlying Vehicle object of the vehicle.
     * @return The underlying Vehicle object.
     */
    Vehicle getVehicle();

    /**
     * Retrieves the IVehicle object itself.
     * @return The IVehicle object.
     */
    IVehicle getIvehicle();
}
