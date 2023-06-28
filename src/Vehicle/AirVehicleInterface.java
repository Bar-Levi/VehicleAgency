
package Vehicle;


public interface AirVehicleInterface {
    /**
     *Returns the usage type of this AirVehicle object.
     *@return a String representing the usage type of the air vehicle
     */
    String getUsageType();
    /**
     *Returns a String representation of this AirVehicle object, including its model, maximum number of passengers,
     *maximum speed, traveled distance, and usage type.
     *@return a String representation of the AirVehicle object
     */
    String toString();
    /**
     *Compares this AirVehicle object to the specified object for equality. Returns true if the specified object is also
     *an AirVehicle object and has the same model, maximum number of passengers, maximum speed, traveled distance, and
     *usage type as this AirVehicle object.
     *@param o the object to compare this AirVehicle against
     *@return true if the two objects are equal, false otherwise
     */
    boolean equals(final Object o);
}
