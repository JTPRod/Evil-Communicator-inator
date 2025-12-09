package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import java.util.List;

import evil.doofenshmirtz.evilcommunicatorinator.Models.Message;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/message")
//@CrossOrigin(origins = "*")
public class MessageRestController {

    private final SseEmitter emitter = new SseEmitter(0L);

    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public SseEmitter streamMessages() {
        return emitter;
    }

    public void notifyNewMessage() {
        try {
            emitter.send(SseEmitter.event().name("new-message").data("update"));
        } catch (Exception e) {
            // Maybe send message on client disconnetc
        }
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody Message message) {
        //check for profanity
        Message filteredMessage = Message.checkProfanity(message);

        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return MessageRestDataArrayList.add(filteredMessage);

            case MONGO:
                String returnMsg = EvilCommunicatorRestDataMongo.addMessage(filteredMessage);
                notifyNewMessage();
                return returnMsg;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Message> getAll() {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return MessageRestDataArrayList.getAll();

            case MONGO:
                return EvilCommunicatorRestDataMongo.getAllMessages();

            default:
                return null;
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Message findById(@PathVariable ObjectId id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return MessageRestDataArrayList.getById(id);

            case MONGO:
                return EvilCommunicatorRestDataMongo.getMessageById(id);

            default:
                return null;
        }
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public List<Message> findByUserId(@PathVariable ObjectId id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return null;
            case MONGO:
                return EvilCommunicatorRestDataMongo.getAllMessagesByUserId(id);
            default:
                return null;
        }
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public Message update(@RequestBody Message message) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return MessageRestDataArrayList.update(message);

            case MONGO:
                return EvilCommunicatorRestDataMongo.updateMessage(message);

            default:
                return null;
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable ObjectId id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return MessageRestDataArrayList.deleteById(id);

            case MONGO:
                return EvilCommunicatorRestDataMongo.deleteMessageById(id);

            default:
                return null;
        }

    }

}
