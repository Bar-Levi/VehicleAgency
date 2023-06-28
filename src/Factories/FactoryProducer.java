package Factories;

import Graphics.VehicleAgencyGUI;

import java.io.Serializable;

public class FactoryProducer implements Serializable {
    /**
     * Retrieves the corresponding factory based on the specified type.
     *
     * @param gui  the VehicleAgencyGUI instance to be associated with the factory
     * @param type the type of factory to be retrieved
     * @return the appropriate AbstractVehicleFactory instance based on the type
     */
    public static AbstractVehicleFactory getFactory(VehicleAgencyGUI gui, String type) {
        if (type.toLowerCase().equals("land"))
            return new LandVehicleFactory(gui);
        else if (type.toLowerCase().equals("air"))
            return new AirVehicleFactory(gui);
        else if (type.toLowerCase().equals("sea"))
            return new SeaVehicleFactory(gui);
        else if (type.toLowerCase().equals("amphibious"))
            return new AmphibiousVehicleFactory(gui);
        else  // Stands for "hybrid"
            return new HybridVehicleFactory(gui);
    }
}
