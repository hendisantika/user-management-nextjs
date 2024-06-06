package id.my.hendisantika.userservice.service;

import id.my.hendisantika.userservice.dto.UserDTO;
import id.my.hendisantika.userservice.entity.User;
import id.my.hendisantika.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : user-management-nextjs
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/6/24
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO saveUser(UserDTO user) {
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return user;
    }

    public List<UserDTO> getAllUsers() {
        List<User> userEntities = userRepository.findAll();
        return userEntities
                .stream()
                .map(userEntity -> new UserDTO(
                        userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getEmailId()
                ))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User userEntity = userRepository.findById(id).get();
        UserDTO user = new UserDTO();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return true;
    }

    public UserDTO updateUser(Long id, UserDTO user) {
        User userEntity = userRepository.findById(id).get();
        userEntity.setEmailId(user.getEmailId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        userRepository.save(userEntity);
        return user;
    }
}
