package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
/**
 *This class provides static utility methods that can be used in the VehicleAgencyGUI class.
 */
public abstract class MyShortcuts implements Serializable {
    /**
     *Creates a JLabel from the image file path provided, scales it to the specified width and height, and returns it.
     *@param gui the VehicleAgencyGUI object
     *@param path the image file path
     *@param width the desired width of the image
     *@param height the desired height of the image
     *@return a JLabel with the scaled image
     */
    public static JLabel pathToLabel(VehicleAgencyGUI gui, final String path, final int width, final int height) {
        ImageIcon result_image = new ImageIcon(gui.getClass().getResource(path));
        Image scaled_image = result_image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled_image));
    }
    /**
     *Creates a JButton from the image file path provided, scales it to the specified width and height, and returns it.
     *The button also sets the temp image of the VehicleAgencyGUI object when clicked.
     *@param gui the VehicleAgencyGUI object
     *@param path the image file path
     *@param width the desired width of the image
     *@param height the desired height of the image
     *@return a JButton with the scaled image
     */
    public static JButton pathToButton(VehicleAgencyGUI gui, final String path, final int width, final int height) {
        ImageIcon result_image = new ImageIcon(gui.getClass().getResource(path));
        Image scaled_image = result_image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JButton result_button = new JButton(new ImageIcon(scaled_image));
        result_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setTempImage(new ImageIcon(scaled_image));
            }
        });
        return result_button;
    }
    /**
     *Creates a JButton from the provided ImageIcon, scales it to the specified width and height, and returns it.
     *The button also sets the temp image of the VehicleAgencyGUI object when clicked.
     *@param gui the VehicleAgencyGUI object
     *@param image_icon the ImageIcon object
     *@param width the desired width of the image
     *@param height the desired height of the image
     *@return a JButton with the scaled image
     */
    public static JButton imageIconToButton(VehicleAgencyGUI gui, final ImageIcon image_icon, final int width, final int height) {
        Image scaled_image = image_icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JButton result_button = new JButton(new ImageIcon(scaled_image));
        result_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setTempImage(new ImageIcon(scaled_image));
            }
        });
        return result_button;
    }
    /**
     * Converts an ImageIcon to a JLabel with a scaled image.
     *
     * @param gui         The VehicleAgencyGUI object.
     * @param image_icon  The ImageIcon to be converted.
     * @param width       The desired width of the scaled image.
     * @param height      The desired height of the scaled image.
     * @return            A JLabel with the scaled image.
     */
    public static JLabel imageIconToLabel(VehicleAgencyGUI gui, final ImageIcon image_icon, final int width, final int height) {
        Image scaled_image = image_icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel result_label = new JLabel(new ImageIcon(scaled_image));
        return result_label;
    }
    /**
     *Centers a JComponent horizontally in the VehicleAgencyGUI object with the specified y-coordinate, and returns true.
     *@param frame the specified frame
     *@param component the JComponent object
     *@param y the y-coordinate
     *@return true
     */
    public static boolean centerComponent(JFrame frame, JComponent component, final int y) {
        int labelWidth = component.getPreferredSize().width;
        int labelHeight = component.getPreferredSize().height;
        int x = frame.getWidth() / 2 - labelWidth / 2;
        component.setBounds(x, y, labelWidth, labelHeight);
        return true;
    }
    /**
     * Adds a JComponent to a JPanel with specified layout and Y-position.
     * @param gui the GUI object to which the panel belongs.
     * @param component the component to add.
     * @param p the JPanel to add the component to.
     * @param y the Y position of the component.
     * @return true if the component was successfully added.
     */
    public static boolean addComponentToPanel(VehicleAgencyGUI gui, final JComponent component, JPanel p, final int y) {
        if (component instanceof JLabel || component instanceof JButton )
        {
            component.setFont(gui.getFont());
            component.setBounds(p.getX()+p.getPreferredSize().width/2-component.getWidth()/2,y,component.getPreferredSize().width,component.getPreferredSize().height);
            centerComponent(gui.getFrame(), component, y);
            if (y == gui.NORTH)
                p.add(component, BorderLayout.NORTH);
            else if (y == gui.CENTER)
                p.add(component, BorderLayout.CENTER);
            else
                p.add(component);
            return true;
        }
        return false;
    }
}
