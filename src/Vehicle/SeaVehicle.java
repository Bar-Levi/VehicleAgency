
package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public abstract class SeaVehicle extends Vehicle implements Serializable {
    private boolean movingWithWind;
    private String countryFlag;
    /**
     *Constructs a new SeaVehicle object with default values for its fields.
     *Calls the constructor of its superclass, Vehicle, to initialize its inherited fields.
     *Sets the value of the boolean field movingWithWind to false.
     */
    public SeaVehicle()
    {
        super();
        movingWithWind = false;
    }
    /**
     *Constructs a new SeaVehicle object with the given parameters.
     *@param vehicle_model the model of the sea vehicle
     *@param max_passengers the maximum number of passengers the sea vehicle can carry
     *@param max_speed the maximum speed of the sea vehicle in knots
     *@param moving_with_wind true if the sea vehicle moves with the wind, false otherwise
     *@param country_flag the country flag of the sea vehicle
     *@param image_icon the image associated with this SeaVehicle.
     */
    public SeaVehicle(final String vehicle_model, final int max_passengers, final int max_speed, final boolean moving_with_wind, final String country_flag, final ImageIcon image_icon)
    {
        super(vehicle_model, max_passengers, max_speed, image_icon);
        setIsMovingWithWind(moving_with_wind);
        setCountryFlag(country_flag);
    }
    /**
     * Returns a boolean indicating whether the sea vehicle is moving with the wind or not.
     * @return true if the sea vehicle is moving with the wind, false otherwise.
     */
    public boolean isMovingWithWind() { return movingWithWind; }
    /**
     *Sets whether the sea vehicle is moving with the wind or not.
     *@param answer a boolean indicating whether the vehicle is moving with the wind
     *@return true if the value was successfully set
     */
    public boolean setIsMovingWithWind(final boolean answer) { movingWithWind = answer; return true;}
    /**
     *Sets the country flag of the sea vehicle.
     *@param country_flag the country flag to be set
     *@return true if the country flag is valid and has been set successfully.
     */
    public boolean setCountryFlag(final String country_flag) { countryFlag = country_flag; return true; }
    /**
     * Returns a string representation of this SeaVehicle object.
     * @return a string representation of this object, including its vehicle model, max number of passengers,
     *         max speed, whether it is moving with the wind or not, and the country flag it is under.
     */

    @Override
    public String toString() {
        String text;
        if (movingWithWind)
            text = "Moving with the wind";
        else
            text = "Moving against the wind";
        text += "\nUnder "+countryFlag+ " flag\n";
        return super.toString() + text;
    }
    /**
     * Compares this SeaVehicle to another object to determine if they are equal.
     * @param o the object to compare this SeaVehicle against.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(final Object o) {
        if (!(o instanceof SeaVehicle))
            return false;
        SeaVehicle comparedSeaTransport = (SeaVehicle)o;
        if (super.equals(comparedSeaTransport) &&
                movingWithWind == comparedSeaTransport.movingWithWind &&
                countryFlag.equals(comparedSeaTransport.countryFlag))
            return true;
        return false;
    }
}
