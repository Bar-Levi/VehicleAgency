package Graphics;

import Decorator.StatusDecorator;

import javax.swing.*;
import java.io.Serializable;

/**
 *The AmphibiousDriver class represents a driver thread specifically designed for amphibious vehicles.
 *It extends the Thread class and implements the Serializable interface.
 */
public class AmphibiousDriver extends Thread implements Serializable {
    private DialogThread currentThread;
    /**
     *Constructs a new AmphibiousDriver object.
     *@param thread The DialogThread associated with this driver.
     */
    public AmphibiousDriver(DialogThread thread) {
        super();
        currentThread = thread;

    }
    /**
     *The run() method represents the main execution logic of the AmphibiousDriver thread.
     *It handles the Test driving process for amphibious vehicles in a vehicle agency.
     */
    @Override
    public void run() {
        try {
            DialogThread.openMissionsCounter+=1;
            currentThread.current_gui.lock.lock();
            currentThread.current_gui.lock.unlock();
            // If the vehicle was bought meanwhile processing the beginning the test drive:
            if(!currentThread.current_gui.getAgency().getVehicles()[Math.min(currentThread.current_gui.getAgency().getVehicles().length-1, currentThread.vehicle_index)].equals(currentThread.currentVehicle)) {
                currentThread.showDialog("Vehicle Bought", "Can't implement the test, the vehicle was already bought.");
                Timer timer = new Timer(3000, e -> { currentThread.dialog.dispose(); DialogThread.openMissionsCounter+=1; });
                timer.setRepeats(false);
                timer.start();
                return;
            }

            if (currentThread.currentVehicle.getVehicleLock().isLocked()) {
                currentThread.showWaitingDialog();
            }


            currentThread.currentVehicle.getVehicleLock().lock();

            if (currentThread.currentVehicle.getSellingLock().isLocked()) {
                synchronized (currentThread.currentVehicle.getUserConfirmedBuying()) {
                    currentThread.currentVehicle.getUserConfirmedBuying().wait();
                }
                try {
                    if (!currentThread.current_gui.getAgency().getVehicles()[currentThread.vehicle_index].equals(currentThread.currentVehicle)) {
                        DialogThread.openMissionsCounter-=1;
                        return;
                    }
                } catch (Exception ex) {
                    DialogThread.openMissionsCounter-=1;
                    return;
                }
            }

            GifDialogThread gif = null;

            try {
                if (currentThread.waiting_dialog != null) {
                    currentThread.waiting_dialog.dispose();
                    DialogThread.openMissionsCounter-=1;
                }
                gif = new GifDialogThread(currentThread.current_gui, "Loading Test...");
                ((StatusDecorator)currentThread.currentVehicle.getIvehicle()).setStatus("In Test",currentThread.current_gui);
                sleep(currentThread.slider.getValue() * 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            gif.dialog.dispose();
            ((StatusDecorator)currentThread.currentVehicle.getIvehicle()).setStatus("Available",currentThread.current_gui);
            currentThread.semaphore.release();

            DialogThread.openMissionsCounter-=1;
            currentThread.ActualTestDrive(currentThread.slider);

            if (currentThread.currentVehicle.getVehicleLock().isLocked())
                currentThread.currentVehicle.getVehicleLock().unlock();




            currentThread.current_gui.getAgency().getVehicles()[currentThread.vehicle_index].setCurrentDriversCount(currentThread.current_gui.getAgency().getVehicles()[currentThread.vehicle_index].getCurrentDriversCount() - 1);
            currentThread.current_gui.showMenu();

        } catch (Exception e) {
            currentThread.current_gui.showMenu();
        } finally {
            synchronized (currentThread.currentVehicle.getUserFinishedTesting()) {
                currentThread.currentVehicle.getUserFinishedTesting().notify();
                DialogThread.openMissionsCounter-=1;}
        }
    }
}
