package org.ciklum.facebook;

import org.ciklum.twitter.TwitterRepository;
import org.ciklum.twitter.TwitterProfileProxy;
import org.ciklum.utils.CloneObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by ccc on 17.05.2016.
 */
@Controller
@RequestMapping("/facebook")
public class FacebookController {
    @Autowired
    private TwitterRepository repository;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    @Inject
    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }
        connectionRepository.findPrimaryConnection(Facebook.class).getKey();
        User userProfile = facebook.userOperations().getUserProfile();
        TwitterProfileProxy proxy = new TwitterProfileProxy();
        CloneObject.clone(userProfile, proxy);
        repository.save(proxy);
        model.addAttribute("profile", userProfile);
        return "facebookProfile";
    }
}
