package Vehicle;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;

public class Jeep extends LandVehicle implements Motorized, Commercial, Serializable {
    private String licenseType;
    private int fuelUsage, engineLifetime;
    /**
     * Constructs a Jeep object with the specified vehicle model, maximum speed, fuel usage, and engine lifetime.
     *
     * @param vehicle_model the model of the Jeep
     * @param max_speed the maximum speed of the Jeep
     * @param fuel_usage the fuel usage of the Jeep
     * @param engine_lifetime the engine lifetime of the Jeep
     * @param image_icon the image associated with this Jeep.
     */
    public Jeep(final String vehicle_model, final int max_speed, final int fuel_usage, final int engine_lifetime, final ImageIcon image_icon) {
        super(vehicle_model, 5, max_speed, 4, "dirt", image_icon);
        licenseType = "MINI";
        setFuelUsage(fuel_usage);
        engineLifetime = Math.max(0,engine_lifetime);
    }
    /**
     * Returns the license type of the Jeep.
     * @return the license type of the Jeep
     */
    @Override
    public String getLicenseType() { return licenseType; }
    /**
     * Sets the fuel usage of the Jeep.
     *
     * @param fuel_usage the fuel usage of the Jeep
     * @return true if the fuel usage is set successfully
     */
    @Override
    public boolean setFuelUsage(final int fuel_usage) {
        fuelUsage = Math.max(0, fuel_usage);
        return true;
    }
    /**
     * Returns the fuel usage of the Jeep.
     *
     * @return the fuel usage of the Jeep
     */
    @Override
    public int getFuelUsage() {
        return fuelUsage;
    }
    /**
     * Returns the engine lifetime of the Jeep.
     *
     * @return the engine lifetime of the Jeep
     */
    @Override
    public int getEngineLifetime() {
        return engineLifetime;
    }
    /**
     * Returns a string representation of the Jeep, including its license type, fuel usage, and engine lifetime.
     *
     * @return a string representation of the Jeep
     */
    @Override
    public String toString() {
        return "Jeep:\n" + super.toString() +
                "License type: " + licenseType +
                "\nFuel usage: " + fuelUsage +" [L]" +
                "\nEngine lifetime: " + engineLifetime + " years.";
    }
    /**
     * Compares this Jeep to the specified object. The result is true if and only if the argument is not null and is a Jeep object that has the same properties as this object.
     *
     * @param o the object to compare this Jeep against
     * @return true if the given object represents a Jeep equivalent to this Jeep, false otherwise
     */
    public boolean equals(final Object o) {
        if(!(o instanceof Jeep))
            return false;
        Jeep comparedJeep = (Jeep)o;
        if ( super.equals(comparedJeep) &&
        licenseType.equals(comparedJeep.licenseType) &&
        fuelUsage == comparedJeep.fuelUsage &&
        engineLifetime == comparedJeep.engineLifetime)
            return true;
        return false;
    }
}
