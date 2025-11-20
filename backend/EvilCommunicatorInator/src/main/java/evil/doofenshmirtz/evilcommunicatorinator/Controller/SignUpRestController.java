package evil.doofenshmirtz.evilcommunicatorinator.Controller;

import evil.doofenshmirtz.evilcommunicatorinator.Models.SignUp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SignUpRestController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String create(@RequestBody SignUp signUp) {

        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return SignUpRestDataArrayList.add(signUp);

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<SignUp> getAll() {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return SignUpRestDataArrayList.getAll();

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public SignUp findById(@PathVariable int id) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return SignUpRestDataArrayList.getById(id);

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public SignUp update(@RequestBody SignUp signup) {
        switch (Settings.dbStatus) {
            case ARRAYLIST:
                return SignUpRestDataArrayList.update(signup);

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
                return SignUpRestDataArrayList.deleteById(id);

            case SQL:
                return null;

            case JPA:
                return null;

            default:
                return null;
        }

    }

}
