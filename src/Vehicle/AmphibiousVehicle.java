
package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class AmphibiousVehicle extends SeaVehicle implements LandVehicleInterface,Motorized, Serializable {
    private LandVehicle land_amphibious;
    private int fuelUsage, engineLifetime;
    /**
     *Constructs a new AmphibiousVehicle object with the given parameters.
     *@param vehicle_model a String representing the model of the vehicle
     *@param max_passengers an int representing the maximum number of passengers the vehicle can carry
     *@param max_speed an int representing the maximum speed the vehicle can reach
     *@param moving_with_wind a boolean representing if the vehicle can move with the wind
     *@param country_flag a String representing the country flag of the vehicle
     *@param wheels_num an int representing the number of wheels of the vehicle
     *@param fuel_usage an int representing the fuel usage of the vehicle
     *@param engine_lifetime an int representing the engine lifetime of the vehicle
     *@param image_icon an ImageIcon object representing the image of the vehicle
     */
    public AmphibiousVehicle(final String vehicle_model, final int max_passengers, final int max_speed, final boolean moving_with_wind, final String country_flag, final int wheels_num, final int fuel_usage, final int engine_lifetime, final ImageIcon image_icon){
        super(vehicle_model, max_passengers, max_speed, moving_with_wind, country_flag, image_icon);
        land_amphibious = new LandVehicle(vehicle_model,max_passengers,max_speed,wheels_num,"paved", image_icon);
        setFuelUsage(fuel_usage);
        engineLifetime = Math.max(0,engine_lifetime);

    }
    /**
     *Returns the number of wheels of the land vehicle part of the amphibious vehicle.
     *@return an int representing the number of wheels of the land vehicle
     */
    @Override
    public int getWheelsNum() {
        return land_amphibious.getWheelsNum();
    }
    /**
     *Returns the road type of the land vehicle part of the amphibious vehicle.
     *@return a String representing the road type of the land vehicle
     */
    @Override
    public String getRoadType() {
        return land_amphibious.getRoadType();
    }
    /**
     *Sets the number of wheels of the land vehicle part of the amphibious vehicle.
     *@param wheels_num an int representing the number of wheels of the land vehicle
     @return true if the operation succeeded.
     */
    @Override
    public boolean setWheelsNum(final int wheels_num) {
        return land_amphibious.setWheelsNum(wheels_num);
    }
    /**
     *Sets the road type of the land vehicle part of the amphibious vehicle.
     *@param road_type a String representing the road type of the land vehicle
     *@return true if the operation succeeded, false otherwise
     */
    @Override
    public boolean setRoadType(String road_type) {
        return land_amphibious.setRoadType(road_type);
    }
    /**
     *Sets the fuel usage of the amphibious vehicle.
     *@param fuel_usage an int representing the fuel usage of the vehicle
     *@return true if the operation succeeded, false otherwise
     */
    @Override
    public boolean setFuelUsage(final int fuel_usage) {
        fuelUsage = Math.max(0,fuel_usage);
        return true;
    }
    /**
     *Returns the fuel usage of the AmphibiousVehicle instance.
     *@return the fuel usage value in liters
     */
    @Override
    public int getFuelUsage() {
        return fuelUsage;
    }
    /**
     *Returns the engine lifetime of the AmphibiousVehicle instance.
     *@return the engine lifetime value in years
     */
    @Override
    public int getEngineLifetime() {
        return engineLifetime;
    }
    /**
     *Returns a string representation of the AmphibiousVehicle instance.
     *@return a string representation of the AmphibiousVehicle instance
     */
    public String toString(){
        return "Amphibious:\n" + super.toString() +
                "Road Type: " + land_amphibious.getRoadType() +
                "\nNumber Of Wheels:" + land_amphibious.getWheelsNum() +
                "\nFuel usage: " + fuelUsage +" [L]" +
                "\nEngine lifetime: " + engineLifetime + " years.";
    }
    /**
     *Checks if the specified object is equal to the AmphibiousVehicle instance.
     *@param o the object to compare to
     *@return true if the specified object is equal to the AmphibiousVehicle instance, false otherwise
     */
    public boolean equals(Object o){
        if(!(o instanceof AmphibiousVehicle))
            return false;
        AmphibiousVehicle amphibiousVehicle = (AmphibiousVehicle) o;
        return super.equals(amphibiousVehicle) && engineLifetime == amphibiousVehicle.engineLifetime
                && fuelUsage == amphibiousVehicle.fuelUsage && land_amphibious.equals(amphibiousVehicle.land_amphibious);
    }
}
