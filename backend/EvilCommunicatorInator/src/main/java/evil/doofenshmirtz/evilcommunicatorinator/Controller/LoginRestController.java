package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import java.util.List;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginRestController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody User login) {

        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return LoginRestDataArrayList.add(login);

            case MONGO:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getAll() {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return LoginRestDataArrayList.getAll();

            case MONGO:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable ObjectId id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return LoginRestDataArrayList.getById(id);

            case MONGO:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public User update(@RequestBody User login) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return LoginRestDataArrayList.update(login);

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
                return LoginRestDataArrayList.deleteById(id);

            case MONGO:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

}
