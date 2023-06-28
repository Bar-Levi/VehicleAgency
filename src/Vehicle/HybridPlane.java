package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class HybridPlane extends AmphibiousVehicle implements Serializable, AirVehicleInterface {
    private AirVehicle air_attributes;
    /**
     * Constructs a new HybridPlane object.
     * @param vehicle_model    the model of the vehicle
     * @param max_passengers   the maximum number of passengers the vehicle can carry
     * @param max_speed        the maximum speed of the vehicle
     * @param moving_with_wind specifies if the vehicle is moving with the wind
     * @param country_flag     the flag representing the country of origin
     * @param wheels_num       the number of wheels on the vehicle
     * @param fuel_usage       the fuel usage of the vehicle
     * @param engine_lifetime  the lifetime of the engine in years
     * @param image_icon       the image icon representing the vehicle
     */
    public HybridPlane(final String vehicle_model, final int max_passengers, final int max_speed, final boolean moving_with_wind, final String country_flag, final int wheels_num, final int fuel_usage, final int engine_lifetime, final ImageIcon image_icon){
        super(vehicle_model, max_passengers, max_speed, moving_with_wind, country_flag, wheels_num, fuel_usage, engine_lifetime, image_icon);
        air_attributes = new AirVehicle(vehicle_model, max_passengers, max_speed, "military", image_icon);
    }
    /**
     * @return the usage type of the air vehicle
     */
    @Override
    public String getUsageType() { return air_attributes.getUsageType(); }
    /**
     * @return a string representation of the HybridPlane object.
     */
    public String toString() {
        String[] original_string = super.toString().split("\n");
        String result = "Hybrid Plane:";
        for (int i = 1; i < original_string.length; i++) {
            result += "\n" + original_string[i];
        }
        return result + "\nUsage type: " + getUsageType();
    }
    /**
     * Checks if the given object is equal to this HybridPlane object.
     * @param o the object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof HybridPlane))
            return false;
        HybridPlane comparedHybridPlane = (HybridPlane)o;
        return super.equals(o) && getUsageType().equals(comparedHybridPlane.getUsageType());
    }
}
