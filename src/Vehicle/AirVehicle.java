package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class AirVehicle extends Vehicle implements Serializable {
    private String usageType;
    /**
     *Constructs a new AirVehicle object with the specified model, maximum number of passengers, maximum speed, and usage type.
     *@param vehicle_model a String representing the model of the air vehicle
     *@param max_passengers an integer representing the maximum number of passengers the air vehicle can carry
     *@param max_speed an integer representing the maximum speed of the air vehicle in km/h
     *@param usage_type a String representing the usage type of the air vehicle ("civilian", "military")
     *@param image_icon the image associated with this AirVehicle.
     */
    public AirVehicle(final String vehicle_model, final int max_passengers, final int max_speed, final String usage_type, final ImageIcon image_icon)
    {
        super(vehicle_model, max_passengers, max_speed, image_icon);
        setUsageType(usage_type);
    }
    /**
     *Returns the usage type of this AirVehicle object.
     *@return a String representing the usage type of the air vehicle
     */
    public String getUsageType() { return usageType; }
    /**
     *Sets the usage type of this AirVehicle object.
     *@param usage_type a String representing the usage type of the air vehicle ("civilian", "military")
     *@return true if the usage type was successfully set.
     */
    public boolean setUsageType(final String usage_type) {
        if(!(usage_type.toLowerCase().equals("military") || usage_type.toLowerCase().equals("civilian")))
            return false;
        usageType = usage_type;
        return true;
    }
    /**
     *Returns a String representation of this AirVehicle object, including its model, maximum number of passengers,
     *maximum speed, traveled distance, and usage type.
     *@return a String representation of the AirVehicle object
     */
    public String toString() {
        return super.toString() + "Used for " + usageType + " services\n";
    }
    /**
     *Compares this AirVehicle object to the specified object for equality. Returns true if the specified object is also
     *an AirVehicle object and has the same model, maximum number of passengers, maximum speed, traveled distance, and
     *usage type as this AirVehicle object.
     *@param o the object to compare this AirVehicle against
     *@return true if the two objects are equal, false otherwise
     */
    public boolean equals(final Object o) {
        if (!(o instanceof AirVehicle))
            return false;
        AirVehicle comparedAirTransport = (AirVehicle)o;
        if (super.equals(comparedAirTransport) && usageType == comparedAirTransport.usageType)
            return true;
        return false;
    }

}

