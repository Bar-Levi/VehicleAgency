package Graphics;

import Decorator.StatusDecorator;

import java.io.Serializable;
import java.util.Random;

/**
 * The VehicleSeller class represents a thread for selling vehicles.
 * It extends the Thread class and implements Serializable.
 */
public class VehicleSeller extends Thread implements Serializable {
    private DialogThread currentThread;
    public boolean currentlyTaken = false;
    /**
     * Constructs a VehicleSeller object with the specified DialogThread.
     * @param t the DialogThread associated with the seller
     */
    public VehicleSeller(DialogThread t) { super(); currentThread = t; }
    /**
     * Overrides the run() method of the Thread class.
     * Performs the selling operation.
     */
    public void run(){
        currentThread.vehicleSeller.currentlyTaken = true;
        if (currentThread.currentVehicle.getSellingLock().isLocked()) {
            synchronized (currentThread.currentVehicle.getUserConfirmedBuying()) {
                try {
                    currentThread.currentVehicle.getUserConfirmedBuying().wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                if (!currentThread.current_gui.getAgency().getVehicles()[currentThread.vehicle_index].equals(currentThread.currentVehicle))
                {
                    DialogThread.openMissionsCounter -= 1;
                    return;
                }
            } catch (Exception ex) {
                DialogThread.openMissionsCounter -= 1;
                return;
            }
        }
        currentThread.currentVehicle.getVehicleLock().lock(); // Locking - Waiting until unlocked, and then keep going.
        currentThread.currentVehicle.getSellingLock().lock(); // Locking - Waiting until unlocked, and then keep going.

        if(currentThread.dialog != null) {
            currentThread.dialog.dispose();
        }

        GifDialogThread gif = null;
        try {
            if (currentThread.waiting_dialog != null)
                currentThread.waiting_dialog.dispose();
            ((StatusDecorator)currentThread.currentVehicle.getIvehicle()).setStatus("In Buying Process",currentThread.current_gui);
            gif = new GifDialogThread(currentThread.current_gui, "Loading Buy...");

            int sleeping_time = (new Random().nextInt(6)+5) * 1000;
            sleep(sleeping_time);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            currentThread.dialog.dispose();
            gif.dialog.dispose();
            //((ColorAndStatusDecorator)currentThread.currentVehicle).setStatus("Available",currentThread.current_gui);

            currentThread.current_code = currentThread.buying_confirmation_code;
            currentThread.updateDialog();
        }
        catch(Exception exception) {
        } finally {
            currentThread.vehicleSeller.currentlyTaken = false;
            currentThread.currentVehicle.getVehicleLock().unlock();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentThread.currentVehicle.getSellingLock().unlock();

        }

    }
}
