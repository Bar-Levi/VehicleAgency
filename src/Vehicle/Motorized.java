
package Vehicle;

public interface Motorized {
    /**
     * Sets the fuel usage for the vehicle.
     * @param fuel_usage the fuel usage in liters per 100 kilometers.
     * @return true if the fuel usage was successfully set.
     */
    boolean setFuelUsage(final int fuel_usage);
    /**
     * Returns the fuel usage of the vehicle.
     * @return the fuel usage.
     */
    int getFuelUsage();
    /**
     * Returns the expected lifetime of the vehicle's engine.
     * @return the expected lifetime of the engine in years.
     */
    int getEngineLifetime();
}
