package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class SignUpRestController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody User signUp) {

        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.add(signUp);

            case MONGO:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getAll() {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.getAll();

            case MONGO:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable ObjectId id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.getById(id);

            case MONGO:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public User update(@RequestBody User signup) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.update(signup);

            case MONGO:
                return null;


            default:
                return null;
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable ObjectId id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.deleteById(id);

            case MONGO:
                return null;


            default:
                return null;
        }

    }

}
