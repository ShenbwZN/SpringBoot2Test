package jpas.controller;

import jpas.bean.User;
import jpas.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user")
    public User save(User user) {
        return userService.save(user);
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @GetMapping("/user")
    public Iterable<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/user/{id}")
    public String deleteById(@PathVariable("id") int id) {
        User user = userService.findById(id);
        if (user == null) {
            return "当前用户不存在";
        }
        userService.deleteById(id);
        return "删除成功";
    }

    @PutMapping("/user/{id}")
    public String update(@PathVariable("id") int id, User user) {
        // 这个id会封装到User里
        User updateUser = userService.findById(id);
        if (updateUser != null) {
            updateUser.setUsername(user.getUsername());
            updateUser.setAge(user.getAge());
            updateUser.setSex(user.getSex());
            userService.save(updateUser);
            return "更新成功";
        }
        return "没有这个用户";
    }

}
