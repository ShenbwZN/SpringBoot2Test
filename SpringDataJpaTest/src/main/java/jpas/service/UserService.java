package jpas.service;

import jpas.bean.User;
import jpas.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        // 当ID存在时为更新，ID不存在时为添加
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        boolean present = byId.isPresent(); // present == true 时,找到了
        // User user = byId.orElse(null);
        if (present) {
            return byId.get();
        }
        return null;
    }
}
