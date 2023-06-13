import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EventQueue<T> {

    private Queue<T> eventQueue;

    public EventQueue() {
        eventQueue = new ConcurrentLinkedQueue<>();
    }

    public void addEvent(T event) {
        eventQueue.add(event);
    }

    public boolean hasEvent() {
        return !eventQueue.isEmpty();
    }

    public T getEvent() {
        return eventQueue.poll();
    }
}
