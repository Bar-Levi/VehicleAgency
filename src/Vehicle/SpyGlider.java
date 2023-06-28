
package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class SpyGlider extends AirVehicle implements NonMotorized, Serializable {
    private char energeticGrade;
    private String powerSource;
    /**
     * Constructs a SpyGlider object with the specified power source and image.
     * @param power_source a String representing the power source of the SpyGlider
     * @param image_icon the image associated with this SpyGlider.
     */
    public SpyGlider(final String power_source, final ImageIcon image_icon) {
        super("confidential",1,50,"military", image_icon);
        energeticGrade = 'C';
        powerSource = power_source;
    }
    /**
     * Returns the power source of the SpyGlider.
     * @return a String representing the power source of the SpyGlider
     */
    @Override
    public String getPowerSource() {
        return powerSource;
    }
    /**
     * Returns the energetic grade of the SpyGlider.
     * @return a char representing the energetic grade of the SpyGlider
     */
    @Override
    public char getEnergeticGrade() {
        return energeticGrade;
    }
    /**
     * Returns a string representation of the SpyGlider object.
     * @return a string representation of the SpyGlider object
     */
    @Override
    public String toString() {
        return "SpyGlider:\n" + super.toString() +
                "Energetic Grade: " + energeticGrade  +
                "\nPower Source: " + powerSource;
    }
    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o the object with which to compare
     * @return true if this object is the same as the o argument; false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if(!(o instanceof SpyGlider))
            return false;
        SpyGlider comparedSpyGlider = (SpyGlider) o;
        if (super.equals(comparedSpyGlider) &&
                energeticGrade == comparedSpyGlider.energeticGrade &&
                powerSource.equals(comparedSpyGlider.powerSource))
            return true;
        return false;
    }
}

