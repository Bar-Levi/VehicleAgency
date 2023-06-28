
package Vehicle;

import Decorator.IVehicle;

import javax.swing.*;
import java.io.Serializable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Vehicle implements Serializable, IVehicle
{
    private int currentDriversCount;
    public ReentrantLock vehicleLock = new ReentrantLock();
    public ReentrantLock sellingLock = new ReentrantLock();
    public Condition userConfirmedBuying = sellingLock.newCondition();
    public Condition userFinishedTesting = sellingLock.newCondition();
    private String vehicleModel;
    private ImageIcon image;
    private int maxPassengers;
    private double traveled;
    private int maxSpeed;
    /**
     *Constructs a new Vehicle object with default values for its fields.
     */
    public Vehicle() {
        vehicleModel = "";
        maxPassengers = 0;
        traveled = 0;
        maxSpeed = 0;
        image = null;
    }
    /**
     *Constructs a new Vehicle object with the given parameters.
     *@param vehicle_model the model of the vehicle
     *@param max_passengers the maximum number of passengers the vehicle can carry
     *@param max_speed the maximum speed of the vehicle in km/h
     *@param image_icon the image of the vehicle
     */
    public Vehicle(final String vehicle_model, final int max_passengers, final int max_speed, final ImageIcon image_icon)
    {
        vehicleModel = vehicle_model;
        maxPassengers = Math.max(0, max_passengers);
        traveled = 0;
        maxSpeed = Math.max(0, max_speed);
        image = image_icon;
    }
    public int getCurrentDriversCount() { return currentDriversCount; }
    public boolean setCurrentDriversCount(final int count) {  currentDriversCount = count;  return true; }
    /**
     *Returns the model of the vehicle.
     *@return a String representing the model of the vehicle
     */
    public String getVehicleModel() {
        return vehicleModel; }
    /**
     *Returns the maximum number of passengers the vehicle can carry.
     *@return an integer representing the maximum number of passengers the vehicle can carry
     */
    public int getMaxPassengers() {
        return maxPassengers; }
    /**
     *Returns the maximum speed of the vehicle in km/h.
     *@return an integer representing the maximum speed of the vehicle in km/h
     */
    public int getMaxSpeed() {

        return maxSpeed; }
    /**
     *Returns the distance traveled by the vehicle.
     *@return a double representing the distance traveled by the vehicle
     */
    public double getTraveled() {
        return traveled; }
    /**
     *Sets the distance traveled by the vehicle.
     *@param t a double representing the distance traveled by the vehicle
     *@return true if the distance traveled was successfully updated.
     */
    public boolean setTraveled(final double t) {
        if (t != 0) traveled = Math.max(traveled, t); else traveled = 0; return true; }
    /**
     *Compares this Vehicle object to the specified object to check for equality.
     *@param o the object to compare this Vehicle object against
     *@return true if the objects are equal, false otherwise
     */
    public boolean equals(final Object o)
    {
        if (!(o instanceof Vehicle))
            return false;
        Vehicle comparedVehicle = (Vehicle)o;
        if (maxSpeed == comparedVehicle.maxSpeed
                && vehicleModel.equals(comparedVehicle.vehicleModel)
                && traveled == comparedVehicle.traveled
                && maxPassengers == comparedVehicle.maxPassengers)
            return true;
        return false;
    }
    /**
     *Returns the image associated with this vehicle.
     *@return the image associated with this vehicle
     */
    public ImageIcon getImage()
    {
        return image;
    }
    /**
     *Returns a String representation of this Vehicle object.
     *@return a String containing information about the vehicle, including its model, distance traveled, maximum speed, and maximum number of passengers
     */
    @Override
    public String toString() {
        return "Model: " + vehicleModel +
                "\nTraveled: " + traveled + " Km" +
                "\nMax Speed: " + maxSpeed + " Mph" +
                "\nCan carry a maximum of " + maxPassengers + " people\n";
    }
    /**
     * Returns the ReentrantLock object for locking the vehicle.
     *
     * @return The ReentrantLock object for locking the vehicle.
     */
    public ReentrantLock getVehicleLock() {
        return vehicleLock;
    }
    /**
     * Returns the ReentrantLock object for locking the selling process.
     *
     * @return The ReentrantLock object for locking the selling process.
     */
    public ReentrantLock getSellingLock() {
        return sellingLock;
    }
    /**
     * Returns the Condition object for signaling when the user has finished testing.
     *
     * @return The Condition object for signaling when the user has finished testing.
     */
    public Condition getUserFinishedTesting() {
        return userFinishedTesting;
    }
    /**
     * Returns the Condition object for signaling when the user has confirmed buying.
     *
     * @return The Condition object for signaling when the user has confirmed buying.
     */
    public Condition getUserConfirmedBuying() {
        return userConfirmedBuying;
    }

    /**
     * Returns the type of the vehicle.
     *
     * @return The type of the vehicle.
     */
    @Override
    public String getVehicleType() {
        return getClass().getSimpleName();

    }
    /**
     * Returns the IVehicle object.
     *
     * @return The IVehicle object.
     */
    @Override
    public IVehicle getIvehicle() {
        return this;
    }
    /**
     * Returns the Vehicle object.
     *
     * @return The Vehicle object.
     */
    @Override
    public Vehicle getVehicle() {
        return this;
    }

}
