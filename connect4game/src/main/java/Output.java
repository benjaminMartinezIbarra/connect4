import java.util.Vector;

/**
 * @author benjaminmartinez
 * Date: 2019-05-06
 */
public class Output {

    private Vector<String> messages;

    public Output() {
        this.messages = new Vector<>();
    }

    public void log(final String logMessage) {
        messages.add(logMessage);
    }

    public String lastMessage() {
        return messages.lastElement();
    }
}
