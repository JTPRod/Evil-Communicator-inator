package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/signup")
public class SignUpRestController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody User signUp) {

        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return SignUpRestDataArrayList.add(signUp);

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
                return SignUpRestDataArrayList.getAll();

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
                return SignUpRestDataArrayList.getById(id);

            case MONGO:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public User update(@RequestBody User signup) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return SignUpRestDataArrayList.update(signup);

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
                return SignUpRestDataArrayList.deleteById(id);

            case MONGO:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

}
