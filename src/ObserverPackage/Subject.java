package ObserverPackage;

public interface Subject {
    /**
     * Notifies the observers about a change or update in the subject.
     *
     * @param traveledDistance The distance traveled to be passed to the observers.
     */
    void notifyObserver(final int traveledDistance);
}
