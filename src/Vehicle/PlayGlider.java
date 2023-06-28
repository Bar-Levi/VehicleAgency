
package Vehicle;

import javax.swing.*;
import java.io.Serializable;

public class PlayGlider extends AirVehicle implements NonMotorized, Serializable {
    private char energeticGrade;
    private String powerSource;
    /**
     * Creates a PlayGlider object with default values.
     */
    public PlayGlider() {
        super("toy",0,10,"civilian", null);
        energeticGrade = 'A';
        powerSource = "MANUAL";
    }

    /**
     * Creates a new PlayGlider object with the specified image icon.
     * @param image_icon the image icon to use for the PlayGlider
     */
    public PlayGlider(final ImageIcon image_icon) {
        super("toy",0,10,"civilian", image_icon);
        energeticGrade = 'A';
        powerSource = "MANUAL";
    }
    /**
     * Returns the power source of the PlayGlider, which is always manual.
     * @return the power source of the PlayGlider
     */
    @Override
    public String getPowerSource() {
        return powerSource;
    }
    /**
     * Returns the energetic grade of the PlayGlider.
     * @return the energetic grade of the PlayGlider
     */
    @Override
    public char getEnergeticGrade() {
        return energeticGrade;
    }
    /**
     * Returns a string representation of the PlayGlider object, including its model, max altitude,
     * max speed, usage type, energetic grade, and power source.
     * @return a string representation of the PlayGlider object
     */
    @Override
    public String toString() {
        return "PlayGlider:\n" + super.toString() +
                "Energetic Grade: " + energeticGrade +
                "\nPower Source: " + powerSource;
    }
    /**
     * Indicates whether some other object is "equal to" this one. The PlayGlider objects are considered equal
     * if they have the same attributes (model, max altitude, max speed, usage type, energetic grade, and power source).
     * @param o the object to compare this PlayGlider object against
     * @return true if the given object represents a PlayGlider object equivalent to this PlayGlider object, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if(!(o instanceof PlayGlider))
            return false;
        PlayGlider comparedPlayGlider = (PlayGlider) o;
        if (super.equals(comparedPlayGlider) &&
                energeticGrade == comparedPlayGlider.energeticGrade &&
                powerSource.equals(comparedPlayGlider.powerSource))
            return true;
        return false;
    }
}
