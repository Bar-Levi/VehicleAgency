package Graphics;

import Decorator.ColorDecorator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
/**
 *A custom JScrollPane for displaying the vehicles in the agency.
 *It can be used to display a panel with the vehicles and their information.
 *Extends JScrollPane and implements Serializable interface.
 */
public class AgencyScrollPane extends JScrollPane implements Serializable {
    private static String x = "1";
    private boolean withButtons;
    /**
     *Constructs an AgencyScrollPane with a panel, vertical and horizontal scrolling statuses.
     *Calls the super constructor with the given arguments.
     *@param comp the JComponent to be displayed
     *@param vertical_scrolling_status the vertical scrolling policy
     *@param horizontal_scrolling_status the horizontal scrolling policy
     */
    public AgencyScrollPane(JComponent comp, int vertical_scrolling_status, int horizontal_scrolling_status, boolean with_buttons) {
        super(comp, vertical_scrolling_status, horizontal_scrolling_status);
        withButtons = with_buttons;
    }
    /**
     *Fills the AgencyScrollPane with buttons that represent each vehicle in the agency.
     *The buttons are constructed with the appropriate images, text, and tooltips.
     *When clicked, they open a dialog with more information about the clicked vehicle.
     @param gui the VehicleAgencyGUI object associated with the AgencyScrollPane
     */
    public AgencyScrollPane fillScrollWithVehicles(VehicleAgencyGUI gui, Dimension dimension) {
        JPanel vehicles_info_panel = new JPanel();
        GridLayout layout = new GridLayout(1, gui.getAgency().getVehicles().length);
        layout.setHgap(10);
        layout.setVgap(10);
        vehicles_info_panel.setLayout(layout);

        if (withButtons)
        {
            for (int i = 0; i < gui.getAgency().getVehicles().length; i++)  // Building the scroll_pane.
            {
                JButton vehicle_button = MyShortcuts.imageIconToButton(gui, gui.getAgency().getVehicles()[i].getImage(), dimension.width*1/3, dimension.height/2);
                final int index = i;
                vehicle_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gui.setClickedVehicleIndex(index);
                        DialogThread t = new DialogThread(gui, gui.clicked_on_vehicle_code, index);
                        t.start();
                    }
                });
                vehicle_button.setBounds(i * (dimension.width/3), 40, dimension.width*2/3, dimension.height - 50);
                String edited_text = "<html>" + gui.getAgency().getVehicles()[i].toString().replace("\n", "<br>") + "</html>";
                vehicle_button.setToolTipText(edited_text);
                vehicle_button.setBackground(((ColorDecorator)gui.getAgency().getVehicles()[i]).pickedColor);
                vehicles_info_panel.add(vehicle_button);
            }
        }
        else { // Opening the vehicles report.
            for (int i = 0; i < gui.getAgency().getVehicles().length; i++)  // Building the scroll_pane.
            {
                JLabel vehicle_button = MyShortcuts.imageIconToLabel(gui, gui.getAgency().getVehicles()[i].getImage(), AgencyVehiclesReport.reportScrollDimension.height-50, AgencyVehiclesReport.reportScrollDimension.height-50);
                String edited_text = "<html>" + gui.getAgency().getVehicles()[i].toString().replace("\n", "<br>") + "</html>";
                vehicle_button.setToolTipText(edited_text);
                vehicle_button.setOpaque(true);
                vehicle_button.setBackground(((ColorDecorator)gui.getAgency().getVehicles()[i]).pickedColor);
                vehicles_info_panel.add(vehicle_button);
            }
        }

        vehicles_info_panel.setBounds(0, 40, gui.frameWidth, vehicles_info_panel.getPreferredSize().height);
        AgencyScrollPane result_scroll_pane = new AgencyScrollPane(vehicles_info_panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, withButtons);
        result_scroll_pane.setBounds(0, 40, gui.frameWidth, gui.frameHeight*2/3);
        return result_scroll_pane;
    }
}
