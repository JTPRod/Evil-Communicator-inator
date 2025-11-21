package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import java.util.List;

import evil.doofenshmirtz.evilcommunicatorinator.Models.User;
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
                return LoginRestDataArrayList.getAll();

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
                return LoginRestDataArrayList.getById(id);

            case SQL:
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
                return LoginRestDataArrayList.deleteById(id);

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

}
