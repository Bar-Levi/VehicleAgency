package ObserverPackage;

public interface Observer {
    /**
     * Update method called by the subject to notify the observer
     * about a change or update in the observed entity.
     *
     * @param traveledDistance The distance traveled that triggered the update.
     */
    void update(final int traveledDistance);
}
