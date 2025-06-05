package com.ca.Service;

import com.ca.Respository.UserRepository;
import com.ca.model.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        Long id = (Long) attributes.get("sub");
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");

        User user = new User();
        user.setOidPkFld(id);
        user.setName(name);
        user.setEmail(email);

        userRepository.save(user);

        return oAuth2User;
    }
}
