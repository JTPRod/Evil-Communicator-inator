package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import evil.doofenshmirtz.evilcommunicatorinator.Models.Message;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/message")
//@CrossOrigin(origins = "*")
public class MessageRestController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public SseEmitter streamMessages() {
        SseEmitter emitter = new SseEmitter(0L);

        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        return emitter;
    }

    public void notifyNewMessage() {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().name("new-message").data("update"));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody Message message) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return MessageRestDataArrayList.add(message);

            case MONGO:
                message.setContent(Message.filterProfanity(message.getContent()));
                String returnMsg = EvilCommunicatorRestDataMongo.addMessage(message);
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
