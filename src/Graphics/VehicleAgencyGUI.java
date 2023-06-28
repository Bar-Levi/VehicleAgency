
package Graphics;

import Decorator.IVehicle;
import Factories.*;
import MementoPackage.Caretaker;
import ObserverPackage.Observer;
import System.VehicleAgency;
import Vehicle.SeaVehicle;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class VehicleAgencyGUI implements Serializable, Observer {
    public static VehicleAgencyGUI instance;
    public Caretaker careTaker;
    public final Dimension vehicleButtonDimension = new Dimension(300,45);
    public ReentrantLock lock = new ReentrantLock();
    public final int frameWidth = 800;
    public final int frameHeight = 600;
    public LinkedList<Container> openFrames = new LinkedList<>();
    public LinkedList<DialogThread> openDialogs = new LinkedList<>();
    private VehicleAgency agency;
    public ChangeFlagFrame flagsFrame;
    AgencyVehiclesReport agency_vehicles_report = null;
    private JTextField upload_path_field = new JTextField();
    private JLabel agency_image;
    public ImageIcon temp_image = null;
    AbstractVehicleFactory landVehicleFactory;
    AbstractVehicleFactory airVehicleFactory;
    AbstractVehicleFactory seaVehicleFactory;
    AbstractVehicleFactory amphibiousVehicleFactory;
    AbstractVehicleFactory hybridVehicleFactory;
    public final int NORTH = -1;
    public final int CENTER = -2;
    public final int init_destination_code = -3;
    public final int show_chosen_image_code = -4;
    public final int show_error_code = -5;
    public final int clicked_on_vehicle_code = -6;
    public final int vehicle_was_added_code = -8;
    public final int loading_empty_agency_code = -11;
    public final int add_to_agency_purpose = -12;
    int first_label_y = 100;
    private boolean invalid_path_input = false;
    public boolean invalid_input = false;
    public JFrame frame;
    private AgencyScrollPane scroll_pane;
    public JPanel panel;
    public JDialog dialog;
    private Timer timer;
    private int testing_km;
    public String current_method = "";
    private String previous_method = "";
    public int clicked_vehicle_index;
    private int title_y = 50;
    public Font font;
    /**
     * Free the memory allocations
     */
    void freeMemory(){
        agency = null;
        agency_image = null;
        frame = null;
        panel = null;
        scroll_pane = null;
        upload_path_field = null;
        timer = null;
        dialog = null;
        temp_image = null;
        font = null;
    }
    /**
     *Constructs a new instance of the VehicleAgencyGUI class.
     *Initializes the vehicle agency by calling the private method initializeAgency().
     */
    private VehicleAgencyGUI() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            try {
                UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
            } catch (Exception e) {
            }
        }
        initializeAgency();
    }
    /**
     * Returns the singleton instance of the VehicleAgencyGUI.
     * If the instance does not exist, it creates a new one.
     *
     * @return The singleton instance of VehicleAgencyGUI.
     */
    public static VehicleAgencyGUI getInstance(){
        if(instance == null)
            instance = new VehicleAgencyGUI();
        return instance;
    }
    /**
     * Adds a memento of the current state of the agency to the careTaker.
     * If the counter is equal to 3, it performs a shift operation to store the previous mementos.
     * The counter keeps track of the number of mementos stored.
     * The mementos are stored in files named "mementoFile1.ser", "mementoFile2.ser", and "mementoFile3.ser".
     * The method serializes the agency object and saves it to the appropriate file.
     * If an error occurs during the serialization process, the method catches the exception silently.
     * After saving the memento, a dialog is displayed to inform the user that the agency was saved successfully.
     */
    void addMemento(){  // Originator
        try {
            if (careTaker.counter == 3)
            {
                FileInputStream originelFile3 = new FileInputStream("mementoFile3.ser");
                ObjectInputStream in3 = new ObjectInputStream(originelFile3);
                VehicleAgency tempAgency3 = (VehicleAgency) in3.readObject();
                FileInputStream originelFile2 = new FileInputStream("mementoFile2.ser");
                ObjectInputStream in2 = new ObjectInputStream(originelFile2);
                VehicleAgency tempAgency2 = (VehicleAgency) in2.readObject();
                FileOutputStream fileOut3 = new FileOutputStream("mementoFile2.ser");
                ObjectOutputStream out3 = new ObjectOutputStream(fileOut3);
                out3.writeObject(tempAgency3);
                out3.close();
                fileOut3.close();
                FileOutputStream fileOut2 = new FileOutputStream("mementoFile1.ser");
                ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
                out2.writeObject(tempAgency2);
                out2.close();
                fileOut2.close();
            }
            // 1 - empty, 2- only car, 3 - km ,
            if(careTaker.counter<3) {
                careTaker.counter++;
            }
            FileOutputStream fileOut = new FileOutputStream("mementoFile" + careTaker.counter + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(agency);
            out.close();
            fileOut.close();
            if (!current_method.startsWith("create"))
                showMenu();
            showDialog("Agency Saved", "Agency was saved successfully");
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }
    /**
     * Sets the agency object to the specified VehicleAgency.
     *
     * @param agency The VehicleAgency object to set.
     */
    public void setAgency(VehicleAgency agency) { this.agency = agency; }
    /**
     * Returns the JFrame object associated with the VehicleAgencyGUI instance.
     *
     * @return The JFrame object.
     */
    public JFrame getFrame() { return frame; }
    /**
     *Sets the font size for the current font to the specified size.
     *Creates a new Font object with the font family "Arial", center baseline alignment,
     *and the specified font size. Assigns the new font object to the instance variable font.
     *Returns a boolean value of true to indicate that the font size has been successfully set.
     *@param size an integer value representing the desired font size in points
     *@return true to indicate that the font size has been successfully set
     */
    public boolean setFontSize(final int size) {
        font = new Font("Arial", Font.CENTER_BASELINE, size*4/5); return true;}

    /**
     *Sets the temporary image to the specified ImageIcon object.
     *Assigns the new_image parameter to the instance variable temp_image.
     *Returns a boolean value of true to indicate that the image has been successfully set.
     *@param new_image an ImageIcon object representing the new temporary image
     *@return true to indicate that the image has been successfully set
     */
    public boolean setTempImage(ImageIcon new_image) { temp_image = new_image; return true;}

    /**
     *Updates the previous and current method names.
     *Assigns the value of the current_method parameter to the instance variable current_method,
     *and assigns the value of the previous current_method to the instance variable previous_method.
     @param method_name a string representing the name of the current method
     */
    public void updateCurrentMethod(String method_name){
        previous_method = current_method;
        current_method = method_name;
    }

    /**
     *Initializes the vehicle agency gui.
     *Creates a new JPanel object and sets its layout to null.
     *Creates a new AgencyScrollPane object and assigns it to the instance variable scroll_pane.
     *Creates a new VehicleAgency object and assigns it to the instance variable agency.
     *Sets the font size to 30 using the setFontSize() method.
     *If this is the first initialization, sets the initial values for the title_y, current_method,
     *clicked_vehicle_index, and previous_method instance variables, and creates a new JFrame object
     *with the title "Vehicle Agency", sets its size, location, and layout, and sets the icon image.
     *Returns a boolean value of true to indicate that the agency has been successfully initialized.
     *@return true to indicate that the agency has been successfully initialized
     */
    public boolean initializeAgency() {
        panel = new JPanel();
        panel.setLayout(null);
        scroll_pane = new AgencyScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, true);
        agency = new VehicleAgency();
        setFontSize(30);
        if (previous_method.equals("")) // First initialization.
        {
            careTaker = new Caretaker(this);
            landVehicleFactory = FactoryProducer.getFactory(this, "land");
            airVehicleFactory = FactoryProducer.getFactory(this, "air");
            seaVehicleFactory = FactoryProducer.getFactory(this, "sea");
            amphibiousVehicleFactory = FactoryProducer.getFactory(this, "amphibious");
            hybridVehicleFactory = FactoryProducer.getFactory(this, "hybrid");
            title_y = 50;
            current_method = "";
            frame = new JFrame("Vehicle Agency");
            frame.setSize(frameWidth, frameHeight);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            WindowListener windowListener = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (DialogThread.openMissionsCounter!=0 ) {
                        showDialog("Didn't Finish all tasks","Can't close the program without finishing all tasks first.");
                        Timer timer1 = new Timer(3000, g -> {dialog.dispose(); if (!current_method.startsWith("create"))
                            showMenu();});
                        timer1.setRepeats(false);
                        timer1.start();
                    }
                    else {
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
            };

            frame.addWindowListener(windowListener);

            frame.setResizable(false);
            frame.setLocationRelativeTo(null); // Center the window on the screen
            frame.setLayout(new BorderLayout(10, 10));
            ImageIcon icon = new ImageIcon(getClass().getResource("Assets/Agency.jpg"));
            // Change the path to the location of your icon file
            frame.setIconImage(icon.getImage());
            clicked_vehicle_index = 0;
        }
        return true;
    }

    /**
     *Adds a JLabel to the JPanel and sets its position, size, and font.
     *Sets the position (x, y), width, and height of the JLabel using the setBounds() method.
     *Sets the font of the JLabel using the font instance variable.
     *Adds the JLabel to the JPanel using the add() method.
     *Returns a boolean value of true to indicate that the label has been successfully added to the panel.
     *@param l a JLabel object to be added to the panel
     *@param x an integer value representing the x-coordinate of the label's position
     *@param y an integer value representing the y-coordinate of the label's position
     *@param width an integer value representing the width of the label
     *@param height an integer value representing the height of the label
     *@return true to indicate that the label has been successfully added to the panel
     */
    public boolean addLabelToPanel(JLabel l, int x, int y, int width, int height) {
        l.setBounds(x, y, width, height);
        l.setFont(font);
        panel.add(l);
        return true;
    }
    /**
     * Creates a dialog thread for the specified code.
     * @param code The code to be used for creating the dialog thread.
     * @return {@code true} if the dialog thread was successfully created and started.
     */
    public boolean createDialogThread(int code) {
        DialogThread t = new DialogThread(this, code, clicked_vehicle_index);
        t.start();
        return true;
    }

    /**
     *Handles the image selection/uploading process for creating or updating a vehicle.
     *@param purpose an integer indicating the purpose of the image handling process.
     * 1: creating a new vehicle, 2: updating an existing vehicle's image
     * @param path1 a string representing the file path of the first image
     * @param path2 a string representing the file path of the second image
     * @param path3 a string representing the file path of the third image
     */
    public void handleImages(int purpose, String path1, String path2, String path3) {
        JLabel invalid_label = new JLabel();
        addLabelToPanel(new JLabel("Choose or upload an image:"), 375, first_label_y, 300, font.getSize()*2);
        showImageButton(path1, 250, 150);
        showImageButton(path2, 425, 150);
        showImageButton(path3, 600, 150);

        addLabelToPanel(new JLabel("Enter the image's path:"), 375, first_label_y + 3*70, 250, 30);
        upload_path_field = new JTextField();
        panel.add(upload_path_field);
        upload_path_field.setBounds(300, first_label_y + 3*70 + 30, 400, font.getSize());

        JButton search_image_button = new JButton("Search in desktop");
        search_image_button.setBounds(300, first_label_y + 3*70 + 80, 150, font.getSize() * 2);

        search_image_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser file_chooser = new JFileChooser();
                int result = file_chooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    try {
                        File selected_file = file_chooser.getSelectedFile();
                        String path = selected_file.getAbsolutePath();
                        BufferedImage image = ImageIO.read(selected_file);
                        if (!(image==null)) {
                            ImageIcon result_image = new ImageIcon(path);
                            Image scaled_image = result_image.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                            temp_image = new ImageIcon(scaled_image);
                            invalid_label.setText("");
                            panel.repaint();
                            createDialogThread(show_chosen_image_code);
                            invalid_input = false;
                        } else
                            throw new Exception();
                    } catch (Exception exception) {
                        invalid_input = true;
                        if (current_method.equals("createJeep")) landVehicleFactory.getVehicle("JEEP");
                        else if (current_method.equals("createFrigate")) seaVehicleFactory.getVehicle("FRIGATE");
                        else if (current_method.equals("createSpyGlider")) airVehicleFactory.getVehicle("SPY GLIDER");
                        else if (current_method.equals("createPlayGlider")) ((AirVehicleFactory)airVehicleFactory).createPlayGlider(purpose);
                        else if (current_method.equals("createAmphibious")) ((AmphibiousVehicleFactory)amphibiousVehicleFactory).createAmphibious(purpose);
                        else if (current_method.equals("createManualBicycle")) ((LandVehicleFactory)landVehicleFactory).createManualBicycle(purpose);
                        else if (current_method.equals("createCruiseShip")) ((SeaVehicleFactory)seaVehicleFactory).createCruiseShip(purpose);
                        else if (current_method.equals("createHybridPlane")) ((HybridVehicleFactory)hybridVehicleFactory).createHybridPlane(purpose);
                        else if (current_method.equals("createElectricBicycle")) ((LandVehicleFactory)landVehicleFactory).createElectricBicycle(purpose);
                    }
                }
            }
        });
        if (invalid_path_input) {
            invalid_label.setText("Invalid path.");
            invalid_label.setForeground(Color.red);
            addLabelToPanel(invalid_label, 400, first_label_y + 3*70 + 40, 200, font.getSize()*2);
        }
        panel.add(search_image_button);


        JButton upload_image_button = new JButton("Upload by full path");
        upload_image_button.setBounds(530, first_label_y + 3*70 + 80, 170, font.getSize() * 2);
        upload_image_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = upload_path_field.getText();
                try {
                    File file = new File(path);
                    BufferedImage image = ImageIO.read(file);
                    if (image==null) { throw new Exception(); }
                    ImageIcon result_image = new ImageIcon(path);
                    Image scaled_image = result_image.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                    temp_image = new ImageIcon(scaled_image);
                    invalid_input = false;
                    invalid_label.setText("");
                    panel.repaint();
                    createDialogThread(show_chosen_image_code);
                }
                catch(Exception exception) {
                    invalid_input = true;
                    if (current_method.equals("createJeep")) ((LandVehicleFactory)landVehicleFactory).createJeep(purpose);
                    else if (current_method.equals("createFrigate")) ((SeaVehicleFactory)seaVehicleFactory).createFrigate(purpose);
                    else if (current_method.equals("createSpyGlider")) ((AirVehicleFactory)airVehicleFactory).createSpyGlider(purpose);
                    else if (current_method.equals("createPlayGlider")) ((AirVehicleFactory)airVehicleFactory).createPlayGlider(purpose);
                    else if (current_method.equals("createAmphibious")) ((AmphibiousVehicleFactory)amphibiousVehicleFactory).createAmphibious(purpose);
                    else if (current_method.equals("createManualBicycle")) ((LandVehicleFactory)landVehicleFactory).createManualBicycle(purpose);
                    else if (current_method.equals("createCruiseShip")) ((SeaVehicleFactory)seaVehicleFactory).createCruiseShip(purpose);
                    else if (current_method.equals("createHybridPlane")) ((HybridVehicleFactory)hybridVehicleFactory).createHybridPlane(purpose);
                    else if (current_method.equals("createElectricBicycle")) ((LandVehicleFactory)landVehicleFactory).createElectricBicycle(purpose);
                }
            }
        });
        panel.add(upload_image_button);

    }
    /**
     *Validates user input and displays an error message if necessary.
     *If invalid_input flag is true, a dialog box will be displayed showing an error message.
     */
    public void inputValidation(){
        if (invalid_input) createDialogThread(show_error_code);
    }

    /**
     *Displays an image button on the panel at the given x and y coordinates.
     *The button is created using the path provided and set to a fixed size of 150 x 100 pixels.
     *Clicking the button sets the image to be displayed in the temporary image variable and opens a dialog box to display it.
     *@param path the path to the image file
     *@param x the x-coordinate of the button's position on the panel
     *@param y the y-coordinate of the button's position on the panel
     */
    private void showImageButton(String path, int x, int y) {
        JButton button = MyShortcuts.pathToButton(this, path, 150, 100);
        button.setBounds(x, y, 150, 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon result_image = new ImageIcon(getClass().getResource(path));
                Image scaled_image = result_image.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                temp_image = new ImageIcon(scaled_image);
                createDialogThread(show_chosen_image_code);
            }
        });
        panel.add(button);

    }

    /**
     *Adds a JComponent to a JLayeredPane at the specified position and layer.
     *@param component the JComponent to add
     *@param layered_pane the JLayeredPane to add the component to
     *@param x the x-coordinate of the component's position
     *@param y the y-coordinate of the component's position
     *@param layer the layer to add the component to
     */
    private void addComponentToLayered(JComponent component, JLayeredPane layered_pane, int x, int y, int layer) {
        component.setBounds(x, y, component.getPreferredSize().width, component.getPreferredSize().height);
        layered_pane.add(component, layer);
    }
    /**
     *Displays the welcome window of the B&R Agency gui. This method initializes the agency, clears the frame, and adds
     *various JLabels, JButtons, and images to a layered pane. The layered pane is then added to the frame and displayed.
     *@return true if the welcome window is successfully displayed.
     */
    private boolean welcomeWindow() {
        initializeAgency();
        clearFrame();
        setFontSize(30);
        updateCurrentMethod("welcomeWindow");

        JLabel label1 = new JLabel("Hello and welcome to B&R Agency");
        label1.setBackground(Color.black);
        label1.setForeground(new Color(203, 213, 223));
        label1.setOpaque(true);
        label1.setPreferredSize(new Dimension(400,font.getSize()));
        MyShortcuts.addComponentToPanel(this, label1, panel, 20);


        JLabel label2 = new JLabel("We've got plenty of vehicles for you!");
        label2.setBackground(Color.black);
        label2.setForeground(new Color(193, 203, 213));
        label2.setOpaque(true);
        MyShortcuts.addComponentToPanel(this, label2, panel, 60);

        JLabel label3 = new JLabel("Own your agency TODAY!");
        label3.setBackground(Color.black);
        label3.setForeground(new Color(213, 223, 233));
        label3.setOpaque(true);
        MyShortcuts.addComponentToPanel(this, label3, panel, 100);

        agency_image = MyShortcuts.pathToLabel(this, "Assets/Dealership.jpg", frameWidth, frameHeight);
        agency_image.setBounds(0, 0, frameWidth, frameHeight);
        setFontSize(20);

        JButton start_choosing_button = new JButton("Start building the agency!");
        setFontSize(30);
        start_choosing_button.setFont(font);
        start_choosing_button.setPreferredSize(new Dimension(370,30));
        start_choosing_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                buildAgency();
            }
        });


        // Creating Bar and Rony's images.
        setFontSize(20);
        JLabel bar_image = MyShortcuts.pathToLabel(this, "Assets/BarImage.jpeg", 150, 175);
        JLabel bar_label = new JLabel("Bar Levi");
        bar_label.setFont(font);
        bar_label.setBackground(Color.black);
        bar_label.setForeground(new Color(213, 223, 233));
        bar_label.setOpaque(true);
        bar_label.setBounds(bar_image.getX() + bar_image.getWidth()/2 - bar_label.getPreferredSize().width/2, bar_image.getY() - 50, 200, font.getSize()*2);


        JLabel rony_image = MyShortcuts.pathToLabel(this, "Assets/RonyImage.png", 150, 175);
        JLabel rony_label = new JLabel("Rony Bubnovsky");
        rony_label.setBackground(Color.black);
        rony_label.setForeground(new Color(213, 223, 233));
        rony_label.setOpaque(true);
        rony_label.setFont(font);
        rony_label.setBounds(rony_image.getX() + rony_image.getWidth()/2 - rony_label.getPreferredSize().width/2, rony_image.getY() - 50, 1000, font.getSize()*2);


        // Adding the objects to the screen.
        JLayeredPane layered_pane = new JLayeredPane();
        addComponentToLayered(agency_image, layered_pane, 0, 0, 2);
        addComponentToLayered(label1, layered_pane, frameWidth/2 - label1.getWidth()/2, 20, 0);
        addComponentToLayered(label2, layered_pane, frameWidth/2 - label2.getWidth()/2, 60, 0);
        addComponentToLayered(label3, layered_pane, frameWidth/2 - label3.getWidth()/2, 100, 0);
        addComponentToLayered(start_choosing_button, layered_pane, (int)(frameWidth/2 - start_choosing_button.getPreferredSize().getWidth()/2), 500, 0);
        addComponentToLayered(bar_image, layered_pane, frameWidth - (int)bar_image.getPreferredSize().getWidth(), frameHeight - (int)bar_image.getPreferredSize().getHeight(), 0);
        addComponentToLayered(rony_image, layered_pane, 0, frameHeight - (int)rony_image.getPreferredSize().getHeight(), 0);
        addComponentToLayered(bar_label, layered_pane, bar_image.getX() + bar_image.getWidth()/2 - bar_label.getPreferredSize().width/2, bar_image.getY() - 30, 0);
        addComponentToLayered(rony_label, layered_pane, rony_image.getX() + rony_image.getWidth()/2 - rony_label.getPreferredSize().width/2, rony_image.getY() - 30, 0);
        frame.add(layered_pane);
        frame.setVisible(true);
        return true;
    }
    /**
     Displays the agency's inventory by showing the agency vehicles report.
     @return {@code true} if the agency inventory is successfully displayed.
     */
    boolean showAgencyInventory() {
        frame.setLocation(frame.getX(), AgencyVehiclesReport.reportScrollDimension.height + 30);
        openFrames.add(agency_vehicles_report);
        AgencyVehiclesReport agency_vehicles_report = AgencyVehiclesReport.getInstance(this);
        agency_vehicles_report.setVisible(true);
        return true;
    }

    /**
     *Displays the main menu for the dealership system.
     *@return true if the menu was successfully displayed, false otherwise.
     */
    public boolean showMenu() {
        frame.setLayout(new BorderLayout(10, 10));
        clearFrame();
        panel = new JPanel();
        panel.setLayout(null);
        agency_image = MyShortcuts.pathToLabel(this, "Assets/Dealership.jpg", frameWidth, frameHeight);

        updateCurrentMethod("showMenu");

        setFontSize(30);
        addReturnButton();
        JLabel totalDistanceLabel = new JLabel("Total Distance Traveled : " + agency.totalTraveledDistance);
        totalDistanceLabel.setPreferredSize(new Dimension(200,font.getSize()));
        totalDistanceLabel.setBounds(frameWidth/2-totalDistanceLabel.getPreferredSize().width/2, 10, totalDistanceLabel.getPreferredSize().width, totalDistanceLabel.getPreferredSize().height);
        panel.add(totalDistanceLabel);

        if (agency.getVehicles().length > 0)
        {
            scroll_pane = scroll_pane.fillScrollWithVehicles(this, new Dimension(frameHeight, frameHeight));
        }
        else {
            scroll_pane = new AgencyScrollPane(MyShortcuts.pathToLabel(this, "Assets/closed_dealership.png", frameWidth, 500), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, true);
            scroll_pane.setBounds(0, 40, frameWidth, frameHeight*2/3);
        }
        setFontSize(20);
        int top_button_y = scroll_pane.getY() + scroll_pane.getHeight() + 20;

        JButton add_vehicle_button = new JButton("Add Vehicles to Agency");
        add_vehicle_button.setFont(font);
        add_vehicle_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildAgency();
            }
        });
        panel.add(add_vehicle_button);  // top-left button.


        JButton init_traveled_button = new JButton("Initialize Traveled Destination");
        init_traveled_button.setFont(font);
        init_traveled_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(agency.getVehicles().length == 0){
                    showDialog("Sold Out", "We are sold out.");
                }
                else {
                    createDialogThread(init_destination_code);
                    for (IVehicle v: agency.getVehicles())
                        v.setTraveled(0);
                }
                if (!current_method.startsWith("create"))
                    showMenu();
            }
        });
        panel.add(init_traveled_button);  // top-right button.

        JButton change_flag_button = new JButton("Change Country Flag");
        change_flag_button.setFont(font);
        change_flag_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(agency.getVehicles().length == 0){
                    showDialog("Sold Out", "We are SOLD OUT!");
                }
                else {
                    int sea_count = 0;
                    for (int i = 0; i < agency.getVehicles().length; i++) {
                       try{
                           SeaVehicle x = ((SeaVehicle) agency.getVehicles()[i].getVehicle());
                           sea_count++;
                       }
                       catch (Exception ex) {}
                    }
                    if (sea_count == 0){
                        showDialog("No Sea Vehicle", "We don't have sea vehicles");
                    }
                    else{
                        changeFlag();
                    }

                }
            }
        });
        panel.add(change_flag_button);  // bottom-left button.

        JButton exit_button = new JButton("Exit");
        exit_button.setFont(font);
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DialogThread.openMissionsCounter!=0 ) {
                    showDialog("Didn't Finish all tasks","Can't close the program without finishing all tasks first.");
                    Timer timer1 = new Timer(3000, g -> dialog.dispose());
                    timer1.setRepeats(false);
                    timer1.start();
                }
                else {
                    freeMemory();
                    System.exit(1);
                }
            }
        });
        panel.add(exit_button);  // bottom-right button.

        JButton show_inventory_button = new JButton("Agency Inventory");
        setFontSize(20);
        show_inventory_button.setFont(font);
        show_inventory_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAgencyInventory();
            }
        });
        panel.add(show_inventory_button);  // bottom-right button.

        JButton save_agency_button = new JButton("Save Agency");
        setFontSize(20);
        save_agency_button.setFont(font);
        save_agency_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMemento();
            }
        });
        panel.add(save_agency_button);
        if (DialogThread.openMissionsCounter != 0)
            save_agency_button.setEnabled(false);

        JButton load_agency_button = new JButton("Load Previous Agency");
        setFontSize(20);
        load_agency_button.setFont(font);
        load_agency_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrame();
                careTaker.getMemento();
            }
        });
        if (careTaker.counter == 0)
            load_agency_button.setEnabled(false);
        else load_agency_button.setEnabled(true);
        panel.add(load_agency_button);



        int buttonsWidth = 260;
        int top_buttons_gap = (frameWidth - buttonsWidth*3)/4;
        int vertical_gap = 5;
        add_vehicle_button.setBounds(top_buttons_gap, top_button_y, buttonsWidth, add_vehicle_button.getPreferredSize().height);
        init_traveled_button.setBounds(top_buttons_gap + add_vehicle_button.getWidth() + top_buttons_gap, add_vehicle_button.getY(), buttonsWidth, init_traveled_button.getPreferredSize().height);
        change_flag_button.setBounds(init_traveled_button.getX() + init_traveled_button.getWidth() + top_buttons_gap, top_button_y , buttonsWidth, change_flag_button.getPreferredSize().height);
        int second_buttons_gap = (frameWidth - buttonsWidth*3)/4;
        show_inventory_button.setBounds(second_buttons_gap, add_vehicle_button.getY() + add_vehicle_button.getHeight() + vertical_gap, buttonsWidth, show_inventory_button.getPreferredSize().height);
        save_agency_button.setBounds(show_inventory_button.getX() + show_inventory_button.getWidth() + second_buttons_gap,show_inventory_button.getY(), buttonsWidth, save_agency_button.getPreferredSize().height);
        load_agency_button.setBounds(save_agency_button.getX() + save_agency_button.getWidth() + second_buttons_gap,show_inventory_button.getY(), buttonsWidth, load_agency_button.getPreferredSize().height);

        MyShortcuts.addComponentToPanel(this, exit_button, panel, show_inventory_button.getY() + show_inventory_button.getHeight() + 10);
        frame.add(scroll_pane);
        frame.add(panel);
        frame.setVisible(true);
        return true;
    }
    /**
     *Clears the frame by removing any existing panels and scroll panes and creating new ones.
     *Also removes all components from the content pane and repaints the frame.
     *@return true if the frame was successfully cleared.
     */
    public boolean clearFrame() {
        temp_image = null;
        if (panel != null)
            frame.remove(panel);
        if (scroll_pane != null)
            frame.remove(scroll_pane);
        panel = new JPanel();
        panel.setLayout(null);
        scroll_pane = new AgencyScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, true);
        Container contentPane = frame.getContentPane();
        contentPane.removeAll();
        frame.repaint();
        return true;
    }
    /**
     Displays a dialog box with the specified title and label text.
     @param title The title of the dialog box.
     @param labelText The text to be displayed in the dialog box.
     */
    public void showDialog(String title, String labelText)
    {
        dialog = new JDialog(frame, title, false);
        setFontSize(30);
        JLabel label = new JLabel("<html><i>"+labelText+"</i></html>");
        label.setFont(font);
        dialog.add(label);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(frame);
        dialog.setLocation(dialog.getX(), dialog.getY() + 100);
        Timer timer = new Timer(3000, e -> { dialog.dispose(); if (!current_method.startsWith("create"))
            showMenu();});
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }
    /**
     Sets the testing kilometers to the specified value.
     @param km The value of testing kilometers to be set.
     @return {@code true} if the testing kilometers are successfully set.
     */
    public boolean setTestingKM(int km) { testing_km = Math.max(km,0); return true;}
    /**
     Removes the specified vehicle from the agency.
     @param vehicle The vehicle to be removed from the agency.
     @return {@code true} if the vehicle is successfully removed from the agency.
     */
    protected boolean removeVehicleFromAgency(final IVehicle vehicle) {
        if (agency.getVehicles() == null)
            return false;
        IVehicle[] temp_vehicles = new IVehicle[agency.getVehicles().length - 1];
        int j=0;
        for (IVehicle v: agency.getVehicles()) {
            if (!v.equals(vehicle))
            {
                temp_vehicles[j] = v;
                j++;
            }
        }
        agency.setVehicles(temp_vehicles);
        return true;
    }

    /**
     *Sets a new country flag to all sea vehicles of the agency.
     *@param new_flag the new flag to be set
     */
    public void setNewFlag(final String new_flag) {
        for (IVehicle v: agency.getVehicles())
            try{
                ((SeaVehicle)v.getVehicle()).setCountryFlag(new_flag);
            }
        catch (Exception ex){}
        showDialog("Flag Changed", "Flag changed successfully to "+new_flag);
    }
    /**
     *Changes the country flag for all sea vehicles in the agency.
     *Displays a menu with different flag options as buttons and sets the new flag for all relevant vehicles upon clicking.
     *@return boolean indicating the success of the method.
     */
    boolean changeFlag(){
        openFrames.add(flagsFrame);
        flagsFrame = ChangeFlagFrame.getInstance(this);
        flagsFrame.Initialize();
        flagsFrame.buildFlagsFrame();
        return true;
    }
    /**
     *Returns the VehicleAgency object associated with this instance.
     *@return the VehicleAgency object
     */
    public VehicleAgency getAgency() { return agency; }
    /**
     *Returns the font used by the gui.
     *@return the font used by the gui.
     */
    public Font getFont() { return font; }
    /**
     *Sets the index of the clicked vehicle and returns true if successful.
     *@param index the index of the clicked vehicle
     *@return true if the index was set successfully
     */
    public boolean setClickedVehicleIndex(int index) { clicked_vehicle_index = index; return true; }
    /**
     *Adds a "Return" button to the panel and sets its text based on the current method.
     *@return true if the button is successfully added to the panel.
     */
    public boolean addReturnButton() {
        JButton return_button = new JButton("Return");
        return_button.setPreferredSize(new Dimension(75,font.getSize()));
        if (current_method.equals("showMenu")) {
            return_button.setText("Start a new agency");
            return_button.setPreferredSize(new Dimension(150,font.getSize()));
        }
        return_button.setBounds(10, 10, return_button.getPreferredSize().width, return_button.getPreferredSize().height);
        return_button.setOpaque(true);
        panel.add(return_button);
        return_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalid_input = false;
                clearFrame();
                if ((current_method.equals("buildAgency") && previous_method.equals("welcomeWindow")) || current_method.equals("showMenu"))
                    welcomeWindow();
                else if (current_method.equals("buildAgency") && previous_method.equals("showMenu"))
                    showMenu();
                else if (previous_method.toLowerCase().startsWith("create") && current_method.equals("buildAgency"))
                    showMenu();
                else if (current_method.toLowerCase().startsWith("create"))
                    buildAgency();
                else if (current_method.equals("sellVehicle") || current_method.equals("changeFlag") || current_method.equals("showAgencyInventory"))
                    showMenu();
            }
        });
        return true;
    }
    /**
     *Initializes the build agency gui, allowing the user to add vehicles to the agency.
     *@return true if the gui was successfully built.
     */
    public boolean buildAgency() {
        setFontSize(30);
        clearFrame();
        updateCurrentMethod("buildAgency");

        invalid_input = false;
        int Vgap = 50;
        addReturnButton();
        JLabel title = new JLabel("Which vehicle type do you want to add?");
        title.setFont(font);
        title.setBounds(frameWidth/2 - title.getPreferredSize().width/2, title_y, title.getPreferredSize().width, title.getPreferredSize().height);
        frame.add(title);

        panel = new JPanel();
        panel.setLayout(null);
        JButton jeep_button = new JButton("Jeep");
        jeep_button.setPreferredSize(vehicleButtonDimension);
        MyShortcuts.addComponentToPanel(this, jeep_button, panel, title_y + Vgap);
        jeep_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                landVehicleFactory.getVehicle("JEEP");
            }
        });

        JButton frigate_button = new JButton("Frigate");
        frigate_button.setPreferredSize(vehicleButtonDimension);
        frigate_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                seaVehicleFactory.getVehicle("FRIGATE");
            }
        });
        MyShortcuts.addComponentToPanel(this, frigate_button, panel, title_y + 2*Vgap);

        JButton spyglider_button = new JButton("SpyGlider");
        spyglider_button.setPreferredSize(vehicleButtonDimension);
        spyglider_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                airVehicleFactory.getVehicle("SPY GLIDER");
            }
        });
        MyShortcuts.addComponentToPanel(this, spyglider_button, panel, title_y + 3*Vgap);

        JButton playglider_button = new JButton("PlayGlider");
        playglider_button.setPreferredSize(vehicleButtonDimension);
        MyShortcuts.addComponentToPanel(this, playglider_button, panel, title_y + 4*Vgap);

        playglider_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                airVehicleFactory.getVehicle("PLAY GLIDER");
            }
        });

        JButton amphibious_button = new JButton("Amphibious");
        amphibious_button.setPreferredSize(vehicleButtonDimension);
        MyShortcuts.addComponentToPanel(this, amphibious_button, panel, title_y + 5*Vgap);
        amphibious_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                amphibiousVehicleFactory.getVehicle("AMPHIBIOUS");
            }
        });

        JButton cruise_ship_button = new JButton("CruiseShip");
        cruise_ship_button.setPreferredSize(vehicleButtonDimension);
        MyShortcuts.addComponentToPanel(this, cruise_ship_button, panel, title_y + 6*Vgap);
        cruise_ship_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                seaVehicleFactory.getVehicle("CRUISE SHIP");
            }
        });

        JButton bicycle_button = new JButton("Manual Bicycle");
        bicycle_button.setPreferredSize(vehicleButtonDimension);
        MyShortcuts.addComponentToPanel(this, bicycle_button, panel, title_y + 7*Vgap);
        bicycle_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                landVehicleFactory.getVehicle("MANUAL BICYCLE");
            }
        });

        JButton hybrid_plane_button = new JButton("Hybrid Plane");
        hybrid_plane_button.setPreferredSize(vehicleButtonDimension);
        MyShortcuts.addComponentToPanel(this, hybrid_plane_button, panel, title_y + 8*Vgap);
        hybrid_plane_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                hybridVehicleFactory.getVehicle("HYBRID PLANE");
            }
        });

        JButton electric_bicycle_button = new JButton("Electric Bicycle");
        electric_bicycle_button.setPreferredSize(vehicleButtonDimension);
        MyShortcuts.addComponentToPanel(this, electric_bicycle_button, panel, title_y + 9*Vgap);
        electric_bicycle_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                landVehicleFactory.getVehicle("ELECTRIC BICYCLE");
            }
        });

        JButton finish_building_button = new JButton("Finish building");
        finish_building_button.setPreferredSize(new Dimension((int)(finish_building_button.getText().length()*font.getSize()/2.8),font.getSize()));
        finish_building_button.setBounds(10, title_y + (int)(9.5*Vgap), finish_building_button.getPreferredSize().width, finish_building_button.getPreferredSize().height);
        finish_building_button.setForeground(new Color(82, 52, 235));
        frame.add(finish_building_button);
        finish_building_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                if (!current_method.startsWith("create"))
                    showMenu();
            }
        });
        frame.add(panel);
        frame.setVisible(true);
        return true;
    }

    public static void main(String[] args) {
        VehicleAgencyGUI agency = VehicleAgencyGUI.getInstance();
        agency.welcomeWindow();

    }
    /**
     * Updates the state of the observer with the provided traveled distance.
     *
     * @param traveledDistance The distance traveled to be added to the total traveled distance.
     */
    @Override
    public void update(int traveledDistance) {
        agency.totalTraveledDistance+=traveledDistance;
        if (!current_method.startsWith("create"))
            showMenu();
    }
}
