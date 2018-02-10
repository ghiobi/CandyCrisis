package app.candycrisis.utils;

public class Event<T> {

    private T source;

    public Event(T source) {
        this.source = source;
    }

    public T getSource() {
        return this.source;
    }

}
