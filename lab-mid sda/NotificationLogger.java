public class NotificationLogger implements TransportObserver {
    @Override
    public void update(String message) {
        System.out.println("[NotificationLogger] Alert: " + message);
    }
}
