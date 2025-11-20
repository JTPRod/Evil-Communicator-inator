package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.Message;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class MessageRestDataArrayList {
    private static final ArrayList<Message> messages = new ArrayList<>();

    public static String add(Message message) {
        if (messages.stream().filter(i -> i.getMessage_id() == message.getMessage_id()).findFirst().orElse(null) == null) {
            messages.add(message);
            return "Message Added";
        } else {
            return "Id Conflict";
        }
    }

    public static ArrayList<Message> getAll() {
        return messages;
    }

    public static Message getById(ObjectId id) {
        return messages.stream().filter(message -> message.getMessage_id() == id).findFirst().orElse(null);
    }

    public static Message update(Message message) {
        Message temp_message = messages.stream().filter(i -> i.getMessage_id() == message.getMessage_id()).findFirst().orElse(null);
        if (temp_message != null) {
            messages.remove(temp_message);
            messages.add(message);
            return message;
        }

        return null;
    }

    public static String deleteById(ObjectId id) {
        return messages.removeIf(message -> message.getMessage_id() == id) ? "Removed" : "Not Found";
    }
}
