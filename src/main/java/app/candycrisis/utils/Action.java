package app.candycrisis.utils;

public interface Action<T> {

    public void performAction(Event<T> event);

}
