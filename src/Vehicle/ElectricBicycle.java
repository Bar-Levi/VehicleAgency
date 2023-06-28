
package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class ElectricBicycle extends Bicycle implements Motorized, Serializable {
    private int fuelUsage = 20;
    private int engineLifetime;
    /**
     * Constructs an ElectricBicycle object.
     *
     * @param vehicle_model The model of the electric bicycle.
     * @param max_passengers The maximum number of passengers the electric bicycle can accommodate.
     * @param max_speed The maximum speed of the electric bicycle.
     * @param road_type The type of road the electric bicycle is designed for.
     * @param engine_lifetime The lifetime of the electric bicycle's engine.
     * @param image_icon The image icon representing the electric bicycle.
     */
    public ElectricBicycle(final String vehicle_model, final int max_passengers, final int max_speed, final String road_type, final int engine_lifetime, final ImageIcon image_icon){
            super(vehicle_model,max_passengers,max_speed, road_type, image_icon);
            engineLifetime = engine_lifetime;
        }
    /**
     * Sets the fuel usage of the electric bicycle.
     *
     * @param fuel_usage The fuel usage to set.
     * @return True if the fuel usage was set successfully, false otherwise.
     */
    @Override
    public boolean setFuelUsage(int fuel_usage) {
        return false;
    }
    /**
     * Returns the fuel usage of the electric bicycle.
     *
     * @return The fuel usage of the electric bicycle.
     */
    @Override
    public int getFuelUsage() {
        return fuelUsage;
    }
    /**
     * Returns the engine lifetime of the electric bicycle.
     *
     * @return The engine lifetime of the electric bicycle.
     */
    @Override
    public int getEngineLifetime() {
        return engineLifetime;
    }
    /**
     * Returns a string representation of the ElectricBicycle object.
     *
     * @return A string representation of the ElectricBicycle object.
     */
    public String toString() {
        String[] original_string = super.toString().split("\n");
        String result = "Electric Bicycle:";
        for (int i = 1; i < original_string.length; i++) {
            result +=  "\n" + original_string[i];
        }
        return result + "\nFuel usage: " + fuelUsage + "[L]\nEngine lifetime: " + engineLifetime + " years";
    }
    /**
     * Checks if the ElectricBicycle object is equal to another object.
     *
     * @param o The object to compare.
     * @return True if the ElectricBicycle object is equal to the other object, false otherwise.
     */
    public boolean equals(final Object o) {
        if (!(o instanceof ElectricBicycle))
            return false;
        ElectricBicycle comparedMotorizedBicycle = (ElectricBicycle) o;
        return super.equals(comparedMotorizedBicycle)
                && fuelUsage == comparedMotorizedBicycle.getFuelUsage()
                && engineLifetime == comparedMotorizedBicycle.getEngineLifetime();
    }
}
