package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class Frigate extends SeaVehicle implements Motorized, Serializable {
    private int fuelUsage, engineLifetime;
    /**
     * Creates a new Frigate object with the given parameters.
     * @param vehicle_model The model of the frigate.
     * @param max_passengers The maximum number of passengers the frigate can carry.
     * @param max_speed The maximum speed the frigate can reach.
     * @param moving_with_wind A boolean that indicates whether the frigate can move with the wind or not.
     * @param image_icon the image associated with this frigate.
     */
    public Frigate(final String vehicle_model, final int max_passengers, final int max_speed, final boolean moving_with_wind, final ImageIcon image_icon){
        super(vehicle_model, max_passengers, max_speed, moving_with_wind, "Israel", image_icon);
        engineLifetime = 4;
        setFuelUsage(500);
    }
    /**
     * Sets the fuel usage of the frigate.
     * @param fuel_usage The fuel usage in liters.
     * @return true if the fuel usage was set successfully.
     */
    @Override
    public boolean setFuelUsage(final int fuel_usage) {
        fuelUsage = Math.max(0, fuel_usage);
        return true;
    }
    /**
     * Returns the fuel usage of the frigate.
     * @return The fuel usage in liters.
     */
    @Override
    public int getFuelUsage() {
        return fuelUsage;
    }
    /**
     * Returns the engine lifetime of the frigate.
     * @return The engine lifetime in years.
     */
    @Override
    public int getEngineLifetime() {
        return engineLifetime;
    }
    /**
     * Returns a string representation of the frigate object.
     * @return A string representation of the frigate object.
     */
    @Override
    public String toString() {
        return "Frigate:\n" + super.toString() +
                "Fuel usage: " + fuelUsage +" [L]" +
                "\nEngine lifetime: " + engineLifetime + " years.";
    }
    /**
     * Checks if the given object is equal to this frigate object.
     * @param o The object to compare with this frigate object.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals (final Object o) {
        if(!(o instanceof Frigate))
            return false;
        Frigate comparedFrigate = (Frigate) o;
        if ( super.equals(comparedFrigate) &&
                fuelUsage == comparedFrigate.fuelUsage &&
                engineLifetime == comparedFrigate.engineLifetime)
            return true;
        return false;
    }
}
