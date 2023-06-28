package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class CruiseShip extends SeaVehicle implements Motorized, Commercial, Serializable {

    private String licenseType;
    private int fuelUsage,engineLifetime;
    /**
     *Constructs a new CruiseShip object with the specified parameters.
     *@param vehicle_model the model of the cruise ship
     *@param max_passengers the maximum number of passengers the cruise ship can carry
     *@param max_speed the maximum speed of the cruise ship in kilometers per hour
     *@param country_flag the country flag of the cruise ship
     *@param fuel_usage the fuel usage of the cruise ship in liters
     *@param engine_lifetime the engine lifetime of the cruise ship in years
     *@param image_icon the image icon of the cruise ship
     */
    public CruiseShip(final String vehicle_model, final int max_passengers, final int max_speed, final String country_flag,final int fuel_usage, final int engine_lifetime, final ImageIcon image_icon){
        super(vehicle_model,max_passengers,max_speed,true,country_flag, image_icon);
        setFuelUsage(fuel_usage);
        engineLifetime = Math.max(0,engine_lifetime);
        licenseType = "Unlimited";
    }
    /**
     *Returns the license type of the cruise ship.
     *@return the license type of the cruise ship
     */
    @Override
    public String getLicenseType() {
        return licenseType;
    }
    /**
     *Sets the fuel usage of the cruise ship to the specified value.
     *@param fuel_usage the fuel usage of the cruise ship in liters
     @return true if the fuel usage is successfully set.
     */
    @Override
    public boolean setFuelUsage(final int fuel_usage) {
        fuelUsage = Math.max(0,fuel_usage);
        return true;
    }
    /**
     *Returns the fuel usage of the cruise ship in liters.
     *@return the fuel usage of the cruise ship
     */
    @Override
    public int getFuelUsage() {
        return fuelUsage;
    }
    /**
     *Returns the engine lifetime of the cruise ship in years.
     *@return the engine lifetime of the cruise ship
     */
    @Override
    public int getEngineLifetime() {
        return engineLifetime;
    }
    /**
     *Returns a string representation of the CruiseShip object.
     *@return a string representation of the CruiseShip object
     */

    public String toString(){
        return "Cruise Ship:\n" + super.toString() +
                "Fuel usage: " + fuelUsage +" [L]" +
                "\nEngine lifetime: " + engineLifetime + " years." +
                "\nLicense type: " + licenseType;
    }
    /**
     *Checks whether the specified object is equal to the current CruiseShip object.
     *@param o the object to compare
     *@return true if the objects are equal, false otherwise
     */

    public boolean equals(Object o){
        if(!(o instanceof CruiseShip))
            return false;
        CruiseShip cruiseShip = (CruiseShip) o;
        return super.equals(cruiseShip) && engineLifetime == cruiseShip.engineLifetime && fuelUsage == cruiseShip.fuelUsage
                && licenseType.equals(cruiseShip.licenseType);
    }
}
