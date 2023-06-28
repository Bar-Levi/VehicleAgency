package MementoPackage;

import Graphics.VehicleAgencyGUI;
import System.VehicleAgency;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Caretaker implements Serializable {
    private VehicleAgencyGUI current_gui;
    public int counter;
    /**
     * Constructs a Caretaker object with the provided VehicleAgencyGUI instance.
     *
     * @param gui The VehicleAgencyGUI instance associated with the Caretaker.
     */
    public Caretaker(VehicleAgencyGUI gui) { current_gui = gui; counter=0;}
    /**
     * Retrieves the memento file and restores the agency state.
     * If the memento file exists, it reads the serialized agency object,
     * sets it as the current agency in the VehicleAgencyGUI, and deletes the file.
     * It also decrements the counter indicating the number of memento files.
     * Finally, it displays a dialog indicating the successful loading of the agency
     * and shows the menu in the VehicleAgencyGUI.
     * If the memento file does not exist or an error occurs during deserialization,
     * it displays an appropriate dialog, creates a new empty agency, and builds it.
     */
    public void getMemento() {
        try {
            FileInputStream fileIn = new FileInputStream("mementoFile"+counter+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            current_gui.setAgency((VehicleAgency) in.readObject());
            in.close();
            fileIn.close();
            File file = new File("mementoFile"+counter+".ser");
            file.delete();
            counter--;
            current_gui.showDialog("Agency Loaded","Agency Loaded Successfully!");
            current_gui.showMenu();
        } catch (IOException e) {
            current_gui.createDialogThread(current_gui.loading_empty_agency_code);
            current_gui.buildAgency();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
