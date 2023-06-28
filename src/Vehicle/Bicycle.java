
package Vehicle;

import javax.swing.*;

public abstract class Bicycle extends LandVehicle{
    /**
     * Constructs a Bicycle object.
     *
     * @param vehicle_model The model of the bicycle.
     * @param max_passengers The maximum number of passengers the bicycle can accommodate.
     * @param max_speed The maximum speed of the bicycle.
     * @param road_type The type of road the bicycle is designed for.
     * @param image_icon The image icon representing the bicycle.
     */
    public Bicycle (final String vehicle_model, final int max_passengers, final int max_speed, final String road_type, final ImageIcon image_icon){
        super(vehicle_model,max_passengers,max_speed,2, road_type, image_icon);
    }
    /**
     * Returns a string representation of the Bicycle object.
     *
     * @return A string representation of the Bicycle object.
     */
    public String toString(){
        return "Bicycle:\n" + super.toString();
    }
}
