
package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class ManualBicycle extends Bicycle implements NonMotorized, Serializable {
    private String powerSource;
    private char energeticGrade;
    /**
     *Constructs a Bicycle object with the given parameters.
     *@param vehicle_model the model of the bicycle
     *@param max_passengers the maximum number of passengers that the bicycle can carry
     *@param max_speed the maximum speed of the bicycle
     *@param road_type the type of road the bicycle is designed for
     *@param image_icon the ImageIcon representing the bicycle's image
     */
    public ManualBicycle(final String vehicle_model, final int max_passengers, final int max_speed, final String road_type, final ImageIcon image_icon){
        super(vehicle_model,max_passengers,max_speed, road_type, image_icon);
        energeticGrade = 'A';
        powerSource = "MANUAL";
    }
    /**
     *Returns the power source of the Bicycle, which is always manual.
     *@return the power source of the Bicycle
     */
    @Override
    public String getPowerSource() {
        return powerSource;
    }
    /**
     *Returns the energetic grade of the Bicycle, which is always 'A'.
     *@return the energetic grade of the Bicycle
     */
    @Override
    public char getEnergeticGrade() {
        return energeticGrade;
    }
    /**
     *Returns a String representation of the Bicycle object.
     *@return a String representation of the Bicycle object
     */
    public String toString(){
        String[] original_string = super.toString().split("\n");
        String result = "Manual Bicycle:";
        for (int i = 1; i < original_string.length; i++) {
            result += "\n" + original_string[i];
        }
        return result + "\nEnergetic Grade: " + energeticGrade  + "\nPower Source: " + powerSource;
    }
    /**
     *Checks if this Bicycle is equal to the specified object. Two bicycles are considered equal if their
     *properties are the same.
     *@param o the object to compare to
     *@return true if this Bicycle is equal to the specified object, false otherwise
     */
    public boolean equals(final Object o) {
        if(!(o instanceof ManualBicycle))
            return false;
        ManualBicycle comparedBicycle = (ManualBicycle) o;
        return (super.equals(comparedBicycle) &&
                energeticGrade == comparedBicycle.energeticGrade &&
                powerSource.equals(comparedBicycle.powerSource));
    }
}
