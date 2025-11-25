package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import java.util.List;

import evil.doofenshmirtz.evilcommunicatorinator.Models.Message;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageRestController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody Message message) {

        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return MessageRestDataArrayList.add(message);

            case MONGO:
                return EvilCommunicatorRestDataMongo.addMessage(message);

            case JPA:
                return null;

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

            case JPA:
                return null;

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

            case JPA:
                return null;

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

            case JPA:
                return null;

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

            case JPA:
                return null;

            default:
                return null;
        }

    }

}
