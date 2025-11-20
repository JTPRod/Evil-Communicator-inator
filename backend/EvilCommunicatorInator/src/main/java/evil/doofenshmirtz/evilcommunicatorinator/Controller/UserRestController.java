package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import java.util.List;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody User user) {

        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.add(user);

            case SQL:
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
                return UserRestDataArrayList.getAll();

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable int id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.getById(id);

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public User update(@RequestBody User user) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.update(user);

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable int id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return UserRestDataArrayList.deleteById(id);

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

}
