package Vehicle;

import javax.swing.*;
import java.io.Serializable;
import java.util.Objects;

public class LandVehicle extends Vehicle implements Serializable {
    private int wheelsNum;
    private String roadType;
    /**
     *Constructs a new LandVehicle object with default values for model, maximum number of passengers, maximum speed,
     *traveled distance, number of wheels, and road type.
     */
    public LandVehicle() {
        super();
        wheelsNum = 0;
        roadType = "paved";
    }
    /**
     *Constructs a new LandVehicle object with the specified model, maximum number of passengers, maximum speed, number of
     *wheels, and road type.
     *@param vehicle_model the model of the vehicle
     *@param max_passengers the maximum number of passengers the vehicle can carry
     *@param max_speed the maximum speed of the vehicle
     *@param wheels_num the number of wheels the vehicle has
     *@param road_type the type of road the vehicle is designed to travel on
     *@param image_icon the image associated with this LandVehicle.
     */
    public LandVehicle(final String vehicle_model, final int max_passengers, final int max_speed, final int wheels_num, final String road_type, final ImageIcon image_icon){
        super(vehicle_model, max_passengers, max_speed, image_icon);
        setWheelsNum(wheels_num);
        setRoadType(road_type);
    }
    /**
     *Returns the number of wheels of this LandVehicle object.
     *@return the number of wheels of this LandVehicle object
     */
    public int getWheelsNum() { return wheelsNum; }
    /**
     *Sets the number of wheels of this LandVehicle object to the specified value.
     *@param wheels_num the new number of wheels for this LandVehicle object
     *@return true if the number of wheels is set successfully.
     */
    public boolean setWheelsNum(final int wheels_num) { wheelsNum = Math.max(wheels_num, 0); return true; }
    /**
     *Returns the type of road on which this LandVehicle object can travel.
     *@return the type of road on which this LandVehicle object can travel
     */
    public String getRoadType() { return roadType; }
    /**
     *Sets the type of road on which this LandVehicle object can travel to the specified value.
     *@param road_type the new type of road for this LandVehicle object
     *@return true if the road type is set successfully.
     */
    public boolean setRoadType(final String road_type) {
        if(!(road_type.toLowerCase().equals("paved") || road_type.toLowerCase().equals("dirt")))
            return false;
        roadType = road_type;
        return true;
    }
    /**
     *Returns a string representation of this LandVehicle object.
     *@return a string representation of this LandVehicle object, including the vehicle model, distance traveled,
     *maximum speed, maximum number of passengers, number of wheels, and type of road on which the vehicle can travel
     */
    public String toString()
    {
        return super.toString() + "Number of wheels: "+wheelsNum+"\nRoad type: "+roadType+'\n';
    }
    /**
     * Compares this LandVehicle to the specified object. The result is true if and only if the argument is not null and is a LandVehicle object that has the same vehicleModel, maxPassengers, traveled, maxSpeed, wheelsNum, and roadType as this object.
     *
     * @param o the object to compare this LandVehicle against
     * @return true if the given object represents a LandVehicle equivalent to this LandVehicle, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof LandVehicle))
            return false;
        LandVehicle comparedLandVehicle = (LandVehicle) o;
        if (super.equals(comparedLandVehicle) &&
                wheelsNum == comparedLandVehicle.wheelsNum &&
                roadType.equals(comparedLandVehicle.roadType))
            return true;
        return false;
    }

}
