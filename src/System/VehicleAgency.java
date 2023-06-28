package System;


import Decorator.IVehicle;
import Graphics.VehicleAgencyGUI;
import Vehicle.*;

import java.io.*;
import java.util.Scanner;

public class VehicleAgency implements Serializable {
    public int totalTraveledDistance = 0;
    private String last_bought_model;
    private IVehicle[] vehicles;
    private static Scanner sc = new Scanner(System.in);
    private final int add_to_agency_purpose;
    private final int searching_to_buy_purpose;
    private final int test_drive_purpose;
    /**
     * Creates a new instance of the VehicleAgency class.
     * This constructor initializes the vehicles array to an empty array, and sets the
     * add_to_agency_purpose, searching_to_buy_purpose, and test_drive_purpose variables
     * to 1, 2, and 3.
     */
    public VehicleAgency() {

        vehicles = new Vehicle[0];
        add_to_agency_purpose = 1;
        searching_to_buy_purpose = 2;
        test_drive_purpose = 3;
    }
    /**
     * Creates a new CruiseShip object based on user input.
     *
     * @param purpose The purpose of creating the CruiseShip (add_to_agency_purpose, searching_to_buy_purpose, test_drive_purpose).
     * @return The created CruiseShip object.
     */
    private CruiseShip createCruiseShip(final int purpose){
        if (purpose == add_to_agency_purpose)
            System.out.println("Great choice!\nPlease tell me more about the new Cruise Ship:");
        else if (purpose == searching_to_buy_purpose)
            System.out.println("Please tell me more about the Cruise Ship you're looking for -");
        else if (purpose == test_drive_purpose)
            System.out.println("Please tell me more about the Cruise Ship you want to test -");
        System.out.println("Cruise Ship model: ");
        String vehicle_model = sc.nextLine();
        vehicle_model = sc.nextLine();
        System.out.println("Max passengers: ");
        int max_passengers = sc.nextInt();
        System.out.println("Max speed: ");
        int max_speed = sc.nextInt();
        System.out.println("What flag it is under?: ");
        String country_flag = sc.nextLine();
        country_flag = sc.nextLine();
        System.out.println("Fuel usage: ");
        int fuel_usage = sc.nextInt();
        System.out.println("Engine lifetime: ");
        int engine_lifetime = sc.nextInt();
        country_flag = sc.nextLine();
        if (purpose == add_to_agency_purpose)
            System.out.println("[DONE] Great! Your new Cruise Ship is ready!\n");
        return new CruiseShip(vehicle_model,max_passengers,max_speed,country_flag,fuel_usage,engine_lifetime, null);
    }
    /**
     * Creates a new ManualBicycle object based on user input.
     *
     * @param purpose The purpose of creating the Bicycle (add_to_agency_purpose, searching_to_buy_purpose, test_drive_purpose).
     * @return The created ManualBicycle object.
     */
    private ManualBicycle createBicycle(final int purpose){
        if (purpose == add_to_agency_purpose)
            System.out.println("Great choice!\nPlease tell me more about the new Bicycle:");
        else if (purpose == searching_to_buy_purpose)
            System.out.println("Please tell me more about the Bicycle you're looking for -");
        else if (purpose == test_drive_purpose)
            System.out.println("Please tell me more about the Bicycle you want to test -");
        System.out.println("Bicycle model: ");
        String vehicle_model = sc.nextLine();
        vehicle_model = sc.nextLine();
        System.out.println("Max passengers: ");
        int max_passengers = sc.nextInt();
        System.out.println("Max speed: ");
        int max_speed = sc.nextInt();
        System.out.println("Road type (paved/dirt): ");
        String road_type = sc.nextLine();
        road_type = sc.nextLine();
        if (purpose == add_to_agency_purpose)
            System.out.println("[DONE] Great! Your new Bicycle is ready!\n");
        return new ManualBicycle(vehicle_model,max_passengers,max_speed,road_type, null);
    }
    /**
     * Creates a new AmphibiousVehicle object based on user input.
     *
     * @param purpose The purpose of creating the AmphibiousVehicle (add_to_agency_purpose, searching_to_buy_purpose, test_drive_purpose).
     * @return The created AmphibiousVehicle object.
     */
    private AmphibiousVehicle createAmphibiousvehicle(final int purpose){
        if (purpose == add_to_agency_purpose)
            System.out.println("Great choice!\nPlease tell me more about the new Amphibious Vehicle -");
        else if (purpose == searching_to_buy_purpose)
            System.out.println("Please tell me more about the Amphibious vehicle you're looking for -");
        else if (purpose == test_drive_purpose)
            System.out.println("Please tell me more about the Amphibious vehicle you want to test -");
        System.out.println("Amphibious vehicle model: ");
        String vehicle_model = sc.nextLine();
        vehicle_model = sc.nextLine();
        System.out.println("Max passengers: ");
        int max_passengers = sc.nextInt();
        System.out.println("Max speed: ");
        int max_speed = sc.nextInt();
        System.out.println("Is it moving with the wind? (true/false): ");
        boolean moving_with_wind = sc.nextBoolean();
        System.out.println("What is the flag it is under? : ");
        String country_flag = sc.nextLine();
        country_flag = sc.nextLine();
        System.out.println("Number of wheels: ");
        int num_wheels = sc.nextInt();
        System.out.println("Fuel usage: ");
        int fuel_usage = sc.nextInt();
        System.out.println("Engine lifetime: ");
        int engine_lifetime = sc.nextInt();
        if (purpose == add_to_agency_purpose)
            System.out.println("[DONE] Great! Your new Amphibious vehicle is ready!\n");
        return new AmphibiousVehicle(vehicle_model,max_passengers,max_speed,moving_with_wind,country_flag,num_wheels,fuel_usage,engine_lifetime, null);
    }
    /**
     * Creates a new Jeep with the given purpose.
     *
     * @param purpose the purpose of creating the Jeep. Should be one of the constants
     *                add_to_agency_purpose, searching_to_buy_purpose, or test_drive_purpose.
     * @return a new Jeep object with the specified properties.
     */
    private Jeep createJeep(final int purpose) {
        if (purpose == add_to_agency_purpose)
            System.out.println("Great choice!\nPlease tell me more about the new jeep -");
        else if (purpose == searching_to_buy_purpose)
            System.out.println("Please tell me more about the jeep you're looking for -");
        else if (purpose == test_drive_purpose)
            System.out.println("Please tell me more about the jeep you want to test -");
        System.out.println("Jeep model: ");
        String vehicle_model = sc.nextLine();
        vehicle_model = sc.nextLine();
        System.out.println("Max speed: ");
        int max_speed = sc.nextInt();
        System.out.println("Fuel usage: ");
        int fuel_usage = sc.nextInt();
        System.out.println("Engine lifetime: ");
        int engine_lifetime = sc.nextInt();
        if (purpose == add_to_agency_purpose)
            System.out.println("[DONE] Great! Your new jeep is ready!\n");
        return new Jeep(vehicle_model, max_speed, fuel_usage, engine_lifetime, null);
    }
    /**
     * Creates a new Frigate with the given purpose.
     *
     * @param purpose the purpose of creating the Frigate. Should be one of the constants
     *                add_to_agency_purpose, searching_to_buy_purpose, or test_drive_purpose.
     * @return a new Frigate object with the specified properties.
     */
    private Frigate createFrigate(final int purpose) {
        if (purpose == add_to_agency_purpose) {
            System.out.println("Great choice!\nPlease tell me more about the new frigate -");
        }
        else if (purpose == searching_to_buy_purpose)
            System.out.println("Please tell me more about the frigate you're looking for -");
        else if (purpose == test_drive_purpose)
            System.out.println("Please tell me more about the frigate you want to test -");
        System.out.println("Frigate model: ");
        String vehicle_model = sc.nextLine();
        vehicle_model = sc.nextLine();
        System.out.println("Max speed: ");
        int max_speed = sc.nextInt();
        System.out.println("Max passengers: ");
        int max_passengers = sc.nextInt();
        System.out.println("Is it moving with the wind? (true/false): ");
        boolean moving_with_wind = sc.nextBoolean();
        if (purpose == add_to_agency_purpose)
            System.out.println("[DONE] Great! Your new frigate is ready!\n");
        return new Frigate(vehicle_model, max_passengers, max_speed, moving_with_wind, null);
    }
    /**
     * Creates a new SpyGlider with the given purpose.
     *
     * @param purpose the purpose of creating the SpyGlider. Should be one of the constants
     *                add_to_agency_purpose, searching_to_buy_purpose, or test_drive_purpose.
     * @return a new SpyGlider object with the specified properties.
     */
    private SpyGlider createSpyGlider(final int purpose) {
        if (purpose == add_to_agency_purpose)
            System.out.println("Great choice!\nPlease tell me more about the new spy-glider:");
        else if (purpose == searching_to_buy_purpose)
            System.out.println("Please tell me more about the spy-glider you're looking for -");
        else if (purpose == test_drive_purpose)
            System.out.println("Please tell me more about the spy-glider you want to test -");
        System.out.println("Power source: ");
        String power_source = sc.nextLine();
        power_source = sc.nextLine();
        if (purpose == add_to_agency_purpose)
            System.out.println("[DONE] Great! Your new spy-glider is ready!\n");
        return new SpyGlider(power_source, null);
    }
    /**
     * Creates a new PlayGlider with the given purpose.
     *
     * @param purpose the purpose of creating the PlayGlider. Should be one of the constants
     *                add_to_agency_purpose, searching_to_buy_purpose, or test_drive_purpose.
     * @return a new PlayGlider object.
     */
    private PlayGlider createPlayGlider(final int purpose) {
        if (purpose == add_to_agency_purpose)
        {
            System.out.println("Great choice!");
            System.out.println("[DONE] Great! Your new play-glider is ready!\n");
        }
        return new PlayGlider();
    }
    /**
     * Builds the vehicle agency and adds vehicles to it based on user input.
     * @return boolean indicating if the agency was successfully built
     */
    public boolean buildAgency() {
        System.out.println(",-----------------------------------------------,\n|   Hello and welcome to our Vehicle Agency!    |\n|We would like you to start building the agency.|\n'-----------------------------------------------'");
        System.out.println("How many vehicles do you want in the agency?");
        vehicles = new Vehicle[sc.nextInt()];
        for (int i = 0; i < vehicles.length; i++)
        {
            boolean wrong_type;
            do {
                wrong_type = false;
                System.out.println("Which vehicle type do you want to add?\nOptions: Jeep / Frigate / PlayGlider / SpyGlider / Amphibious / Bicycle / CruiseShip.");
                String type = sc.next();
                if (type.toLowerCase().equals("jeep"))
                    vehicles[i] = createJeep(add_to_agency_purpose);
                else if (type.toLowerCase().equals("frigate"))
                    vehicles[i] = createFrigate(add_to_agency_purpose);
                else if (type.toLowerCase().equals("spyglider"))
                    vehicles[i] = createSpyGlider(add_to_agency_purpose);
                else if (type.toLowerCase().equals("playglider"))
                    vehicles[i] = createPlayGlider(add_to_agency_purpose);
                else if (type.toLowerCase().equals("amphibious"))
                    vehicles[i] = createAmphibiousvehicle(add_to_agency_purpose);
                else if (type.toLowerCase().equals("bicycle"))
                    vehicles[i] = createBicycle(add_to_agency_purpose);
                else if (type.toLowerCase().equals("cruiseship"))
                    vehicles[i] = createCruiseShip(add_to_agency_purpose);
                else
                {
                    wrong_type = true;
                    System.out.println("Invalid type chosen, try again!\n");
                }
            } while(wrong_type);
        }
        return true;
    }
    /**
     * Sells a vehicle from the vehicle agency based on user input.
     * @return boolean indicating if the sale was successful
     */
    private boolean sellVehicle(){
        if (vehicles.length == 0){
            System.out.println("WE ARE SOLD OUT!");
            return false;
        }
        Vehicle selling_vehicle;
        System.out.println("\n\nOur agency's optional vehicles:\n");
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println(vehicles[i].toString());
        }
        System.out.println("Which vehicle type do you want to buy?\nOptions: Jeep / Frigate / PlayGlider / SpyGlider / Amphibious / Bicycle / CruiseShip.");
        String type = sc.next();
        System.out.println("What is the maximum KM traveled you're looking for?");
        double max_km_traveled = sc.nextInt();
        if (type.toLowerCase().equals("jeep"))
            selling_vehicle = createJeep(searching_to_buy_purpose);
        else if (type.toLowerCase().equals("frigate"))
            selling_vehicle = createFrigate(searching_to_buy_purpose);
        else if (type.toLowerCase().equals("spyglider"))
            selling_vehicle = createSpyGlider(searching_to_buy_purpose);
        else if (type.toLowerCase().equals("playglider"))
            selling_vehicle = createPlayGlider(searching_to_buy_purpose);
        else if (type.toLowerCase().equals("amphibious"))
            selling_vehicle = createAmphibiousvehicle(searching_to_buy_purpose);
        else if (type.toLowerCase().equals("bicycle"))
            selling_vehicle = createBicycle(searching_to_buy_purpose);
        else if (type.toLowerCase().equals("cruiseship"))
            selling_vehicle = createCruiseShip(searching_to_buy_purpose);
        else
        {
            System.out.println("Invalid type chosen, buying failed.\n");
            return false;
        }
        if(checkIfVehicleInAgency(selling_vehicle, max_km_traveled) == null) {
            System.out.println("This vehicle type isn't in our agency.");
            return false;
        }
        IVehicle[] temp_arr;
        try {
            temp_arr = new Vehicle[vehicles.length - 1];
        }
        catch (Exception e){
            temp_arr = null;
        }
        int j = 0, count_vehicle_found = 0;
        for (int i = 0; i < vehicles.length; i++) {
            if ((!selling_vehicle.equals(vehicles[i]))) {
                temp_arr[j++] = vehicles[i];
            }
            else { count_vehicle_found++; }
            if (count_vehicle_found > 1) {
                temp_arr[j++] = vehicles[i];
            }
        }
        if(count_vehicle_found == 0) {
            System.out.println("Sorry! We don't have that vehicle in our agency.");
            return false;
        }
        vehicles = temp_arr;
        if (vehicles.length == 0) System.out.println("Great! Buying Succeeded! OUR AGENCY IS NOW SOLD OUT!");
        else System.out.println("Great! Buying succeeded! We now have " + vehicles.length +" vehicles left!");
        return true;

    }
    /**
     * Helper function to check if a given vehicle is in the vehicle agency and has not traveled more than the specified maximum km.
     * @param v the vehicle to check for
     * @param max_km_traveled the maximum km the vehicle can have traveled
     * @return the vehicle if it is in the agency and has not traveled more than the specified maximum km, null otherwise
     */
    private IVehicle checkIfVehicleInAgency(final IVehicle v, final double max_km_traveled) {
        for (int i = 0; i < vehicles.length; i++) {
            double temp_KM = vehicles[i].getTraveled();
            v.setTraveled(0);
            v.setTraveled(temp_KM);
            if (temp_KM <= max_km_traveled && v.equals(vehicles[i])) {
                return vehicles[i];
            }

        }
        return null;
    }
    /**
     * Allows the user to test drive a vehicle from the vehicle agency based on user input.
     * @return boolean indicating if the test drive was successful
     */
    private boolean testDrive(){
        System.out.println("Choose which kind of vehicle you want:\n1-Jeep 2-Frigate 3-SpyGlider 4-PlayGlider 5-Amphibious 6-Bicycle 7-CruiseShip");
        String type = sc.nextLine();
        type = sc.nextLine();
        System.out.println("What is the maximum KM traveled you're looking for?");
        double max_km_traveled = sc.nextInt();
        if (!(type.toLowerCase().equals("jeep") || type.toLowerCase().equals("frigate") || type.toLowerCase().equals("spyglider") || type.toLowerCase().equals("playglider") || type.toLowerCase().equals("amphibious") || type.toLowerCase().equals("bicycle") || type.toLowerCase().equals("cruiseship") )) {
            System.out.println("Sorry! We don't have this vehicle in our agency.");
            return false;
        }
        if (type.toLowerCase().equals("jeep")) {
            Jeep testing_jeep = createJeep(test_drive_purpose);
            Jeep v = (Jeep)checkIfVehicleInAgency(testing_jeep, max_km_traveled);
            if (v == null) {
                System.out.println("This Jeep is not in our agency!");
                return false;
            }
            else{
                System.out.println("How many KM's is the test drive?");
                int km_drived = sc.nextInt();
                if (km_drived < 1) {
                    System.out.println("Can't update.");
                    return false;
                }
                v.setTraveled(v.getTraveled() + km_drived);
                return true;
            }
        }
        else if (type.toLowerCase().equals("frigate")){
            Frigate testing_frigate = createFrigate(test_drive_purpose);
            Vehicle v = (Frigate)checkIfVehicleInAgency(testing_frigate, max_km_traveled);
            if (v == null) {
                System.out.println("This Frigate is not in our agency! ");
                return false;
            }
            else{
                System.out.println("How many KM's is the test drive?");
                int km_drived = sc.nextInt();
                if (km_drived < 1) {
                    System.out.println("Can't update.");
                    return false;
                }
                v.setTraveled(v.getTraveled() + km_drived);
                System.out.println("Testing over.\nHope you enjoyed the ride!");
                return true;
            }
        }
        else if (type.toLowerCase().equals("spyglider")){
            SpyGlider testing_spyglider = createSpyGlider(test_drive_purpose);
            Vehicle v = (SpyGlider)checkIfVehicleInAgency(testing_spyglider, max_km_traveled);
            if (v == null) {
                System.out.println("This SpyGlider is not in our agency! ");
                return false;
            }
            else{
                System.out.println("How many KM's is the test drive? ");
                int km_drived = sc.nextInt();
                if (km_drived < 1) {
                    System.out.println("Can't update. ");
                    return false;
                }
                v.setTraveled(v.getTraveled() + km_drived);
                return true;
            }
        }
        else if (type.toLowerCase().equals("playglider")){
            PlayGlider testing_playglider = createPlayGlider(test_drive_purpose);
            Vehicle v = (PlayGlider)checkIfVehicleInAgency(testing_playglider, max_km_traveled);
            if (v == null){
                System.out.println("This PlayGlider is not in our agency! ");
                return false;
            }
            System.out.println("How many KM's is the test drive? ");
            int km_drived = sc.nextInt();
            if (km_drived < 1) {
                System.out.println("Can't update. ");
                return false;
            }
            v.setTraveled(v.getTraveled() + km_drived);
            return true;
        }
        else if (type.toLowerCase().equals("amphibious")){
            AmphibiousVehicle testing_amphibious = createAmphibiousvehicle(test_drive_purpose);
            Vehicle v = (AmphibiousVehicle)checkIfVehicleInAgency(testing_amphibious, max_km_traveled);
            if (v == null){
                System.out.println("This Amphibious is not in our agency! ");
                return false;
            }
            System.out.println("How many KM's is the test drive? ");
            int km_drived = sc.nextInt();
            if (km_drived < 1) {
                System.out.println("Can't update. ");
                return false;
            }
            v.setTraveled(v.getTraveled() + km_drived);
            return true;
        }
        else if (type.toLowerCase().equals("bicycle")) {
            ManualBicycle testing_bicycle = createBicycle(test_drive_purpose);
            Vehicle v = (ManualBicycle) checkIfVehicleInAgency(testing_bicycle, max_km_traveled);
            if (v == null) {
                System.out.println("This Bicycle is not in our agency! ");
                return false;
            } else {
                System.out.println("How many KM's is the test drive?");
                int km_drived = sc.nextInt();
                if (km_drived < 1) {
                    System.out.println("Can't update.");
                    return false;
                }
                v.setTraveled(v.getTraveled() + km_drived);
                System.out.println("Testing over.\nHope you enjoyed the ride!");
                return true;
            }


        }
        else if (type.toLowerCase().equals("cruiseship")) {
            CruiseShip testing_cruiseship = createCruiseShip(test_drive_purpose);
            Vehicle v = (CruiseShip) checkIfVehicleInAgency(testing_cruiseship, max_km_traveled);
            if (v == null) {
                System.out.println("This Cruise Ship is not in our agency! ");
                return false;
            } else {
                System.out.println("How many KM's is the test drive?");
                int km_drived = sc.nextInt();
                if (km_drived < 1) {
                    System.out.println("Can't update.");
                    return false;
                }
                v.setTraveled(v.getTraveled() + km_drived);
                System.out.println("Testing over.\nHope you enjoyed the ride!");
                return true;
            }


        }
        return true;
    }
    /**
     * Allows the user to change the country flag for all sea vehicles in the agency.
     * Prompts the user to enter the new country flag, and sets the flag for all sea vehicles.
     *
     * @return true if the country flag was changed successfully, false otherwise.
     */
    private boolean changeCountryFlag() {
        System.out.println("Please enter new country flag for all sea vehicles:");
        int count_changes = 0;
        String new_flag = sc.nextLine();
        new_flag = sc.nextLine();
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] instanceof SeaVehicle){
                ((SeaVehicle) vehicles[i]).setCountryFlag(new_flag);
                count_changes++;
            }
        }
        if (count_changes == 0) {
            System.out.println("We currently don't have Sea Vehicles in our agency!");
            return false;
        }
        System.out.println("Country flags changed successfully!");
        return true;
    }
    /**
     * Displays the menu for the vehicle agency and allows the user to choose an option. The options are:
     * 1 - Buy a vehicle
     * 2 - Test drive
     * 3 - Initialize traveled destination
     * 4 - Change country flag
     * 5 - Exit agency
     *
     * @return true if the user exits the agency
     */
    public boolean showMenu() {
        boolean exit_agency = false;
        int choice;
        while (!exit_agency){
            System.out.println("\nPlease choose an option:\n");
            System.out.println("1-Buy a vehicle\n2-Test Drive\n3-Initialize Traveled Destination\n4-Change Country Flag\n5-Exit Agency");
            System.out.println("options : 1/2/3/4/5");
            choice = sc.nextInt();
            while (choice < 1 || choice > 5){
                System.out.println("Invalid choice. Please choose again!");
                choice = sc.nextInt();
            }
            if (choice == 5) {
                exit_agency = true;
            }
            else if (choice == 1){
                sellVehicle();
            }
            else if (choice == 2){
                testDrive();
            }
            else if (choice == 3){
                for (int i = 0; i < vehicles.length; i++) {
                    vehicles[i].setTraveled(0);
                }
                System.out.println("Initialized all KM's traveled successfully!");
            }
            else {
             changeCountryFlag();
                for (int i = 0; i < vehicles.length; i++) {
                    System.out.println(vehicles[i].toString());
                }
            }
        }
        System.out.println("Thanks for choosing our agency!\nGoodbye!");
        return true;
    }
    /**
     * Retrieves an array of IVehicle objects representing the vehicles.
     *
     * @return An array of IVehicle objects representing the vehicles.
     */
    public IVehicle[] getVehicles() { return vehicles; }
    /**
     * Sets the array of IVehicle objects representing the vehicles.
     *
     * @param new_vehicles The new array of IVehicle objects representing the vehicles.
     * @return True if the vehicles are successfully set, false otherwise.
     */
    public boolean setVehicles(IVehicle[] new_vehicles) {  vehicles = new_vehicles; return true; }
    /**
     * Adds a new vehicle to the agency.
     *
     * @param v The IVehicle object representing the vehicle to be added.
     * @return True if the vehicle is successfully added, false otherwise.
     */
    public boolean addVehicleToAgency(IVehicle v) {
        if (v.getImage() == null)
            return false;

        IVehicle[] vehicles = this.getVehicles();
        IVehicle[] temp = new IVehicle[vehicles.length + 1];
        for (int i = 0; i < vehicles.length; i++) {
            temp[i] = vehicles[i];
        }
        temp[vehicles.length] = v;
        this.setVehicles(temp);
        return true;
    }
    /**
     * Main method that creates a VehicleAgency object, builds the agency, displays the menu and then closes the scanner object.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        VehicleAgency agency = new VehicleAgency();
        agency.buildAgency();
        agency.showMenu();
        sc.close();
    }

}