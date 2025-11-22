package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import java.util.List;

import evil.doofenshmirtz.evilcommunicatorinator.Models.Message;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MessageRestController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody Message message) {

        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return MessageRestDataArrayList.add(message);

            case MONGO:
                return null;

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
                return null;

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
                return null;

            case JPA:
                return null;

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
                return null;

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
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

}
