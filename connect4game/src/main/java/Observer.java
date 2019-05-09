/**
 * @author benjaminmartinez
 * Date: 2019-05-06
 */
public interface Observer<T extends Event> {

    void on(T event);

}
