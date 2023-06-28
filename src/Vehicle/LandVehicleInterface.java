
package Vehicle;

public interface LandVehicleInterface {
    /**
     * Returns the number of wheels of the land vehicle.
     *
     * @return The number of wheels of the land vehicle.
     */
    int getWheelsNum();
    /**
     * Returns the road type of the land vehicle.
     *
     * @return The road type of the land vehicle.
     */
    String getRoadType();
    /**
     * Sets the number of wheels of the land vehicle.
     *
     * @param wheels_num The number of wheels to set.
     * @return True if the number of wheels was set successfully, false otherwise.
     */
    boolean setWheelsNum(final int wheels_num);
    /**
     * Sets the road type of the land vehicle.
     *
     * @param road_type The road type to set.
     * @return True if the road type was set successfully, false otherwise.
     */
    boolean setRoadType(final String road_type);
}
