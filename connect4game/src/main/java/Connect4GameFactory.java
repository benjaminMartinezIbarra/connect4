/**
 * @author benjaminmartinez
 * Date: 2019-05-06
 */
public class Connect4GameFactory {


    public Connect4 build(Output output){
        Connect4 engine = new Connect4();
        engine.addObserver(event -> output.log(event.toString()));
        engine.addObserver(new Connect4GameObserver());
        return engine;
    }

}
