package ru.itis.task.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.task.annotation.ThreadScope;
import ru.itis.task.session.MySession;
import ru.itis.task.model.User;
import ru.itis.task.repository.UserRepository;

import java.util.Optional;

@Service(value = "myUserDetailService")
@ThreadScope
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MySession session;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findUserByLogin(login);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            session.setUser(user);
            return new UserDetailsImpl(user);
        }
        throw new UsernameNotFoundException("User not found");
    }
}
