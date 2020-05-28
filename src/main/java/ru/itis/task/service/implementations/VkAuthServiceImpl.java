package ru.itis.task.service.implementations;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.task.model.Role;
import ru.itis.task.model.User;
import ru.itis.task.repository.UserRepository;
import ru.itis.task.security.details.UserAuth;
import ru.itis.task.security.details.UserDetailsImpl;
import ru.itis.task.service.interfaces.VkAuthService;
import ru.itis.task.session.MySession;

import java.util.List;
import java.util.Optional;

@Component
public class VkAuthServiceImpl implements VkAuthService {
    @Autowired
    MySession session;

    @Autowired
    UserRepository userRepository;

    @Value("${redirect-uri}")
    String redirectUri;
    @Value("${client-id}")
    String clientId;
    @Value("${client-secret}")
    String clientSecret;

    @Override
    public boolean auth(String code) {
        try {
            TransportClient transportClient = HttpTransportClient.getInstance();
            VkApiClient vk = new VkApiClient(transportClient);
            System.out.println(clientId);
            UserAuthResponse authResponse = vk.oauth()
                    .userAuthorizationCodeFlow(Integer.parseInt(clientId), clientSecret, redirectUri, code)
                    .execute();

            UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
            List<UserXtrCounters> list = vk.users().get(actor).userIds(actor.getId().toString()).execute();
            System.out.println("smth");
            if (list.get(0) != null) {
                UserXtrCounters user = list.get(0);
                Optional<User> userOptional = userRepository.findUserByEmail(user.getId().toString());
                User u;
                if (userOptional.isPresent()) {
                    u = userOptional.get();
                } else {
                    u = User.builder()
                            .login(user.getFirstName() + " " + user.getLastName())
                            .email(user.getId().toString())
                            .role(Role.BUYER)
                            .hashPassword("")
                            .build();
                    userRepository.save(u);
                }
                session.setUser(u);
                UserAuth userAuth = new UserAuth(new UserDetailsImpl(u));
                SecurityContextHolder.getContext().setAuthentication(userAuth);
                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
                return true;
            }
        } catch (ApiException | ClientException e) {
            return false;
        }
        return false;
    }
}
