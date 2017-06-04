package me.zhongmingmao.web;

import me.zhongmingmao.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping(value = "/users")
public class UserController {
    
    private static ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>(); // 代替 DB
    private static String SUCCESS = "success";
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return SUCCESS;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        users.put(id, u);
        return SUCCESS;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return SUCCESS;
    }
}
