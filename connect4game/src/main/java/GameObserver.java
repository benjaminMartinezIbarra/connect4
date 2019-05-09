import java.lang.reflect.Method;

/**
 * @author benjaminmartinez
 * Date: 2019-05-06
 */
public abstract class GameObserver<T extends GameEvent> implements Observer<T>, GameStartedObserver,DrawGameObserver, GameWonObserver, DiscAddedObserver {

    @Override
    public void on(final T event) {
        try{
            Method update = getClass().getMethod("on", Class.forName(event.getClass().getName()));
            update.invoke(this, event);
        } catch(Exception e) {
            // log exception
        }
    }

}
