package me.zhongmingmao.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    
    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    @ApiOperation(value = "创建用户", notes = "根据 User 对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体信息", required = true, dataType = "User")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return SUCCESS;
    }
    
    @ApiOperation(value = "获取用户详细信息", notes = "根据 id 获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户 ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }
    
    @ApiOperation(value = "更新用户详细信息", notes = "根据 id 和传递过来的 user 信息更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户 ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户实体信息", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        users.put(id, u);
        return SUCCESS;
    }
    
    
    @ApiOperation(value = "删除用户详细信息", notes = "根据 id 删除用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户 ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return SUCCESS;
    }
}
