import java.util.ArrayList;
import java.util.List;

/**
 * @author benjaminmartinez
 * Date: 2019-05-06
 */
public class Output {

    private List<String> messages;

    public Output() {
        this.messages = new ArrayList<>();
    }

    public void log(final String logMessage) {
        messages.add(logMessage);
    }

    public String lastMessage() {
        return messages.get(messages.size()-1);
    }

}
