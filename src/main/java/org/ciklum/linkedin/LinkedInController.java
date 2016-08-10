package org.ciklum.linkedin;

import org.ciklum.twitter.TwitterProfileProxy;
import org.ciklum.twitter.TwitterRepository;
import org.ciklum.utils.CloneObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by ccc on 18.05.2016.
 */
@Controller
@RequestMapping("/linkedin")
public class LinkedInController {
    @Autowired
    private TwitterRepository repository;
    private LinkedIn linkedIn;
    private ConnectionRepository connectionRepository;

    @Inject
    public LinkedInController(LinkedIn linkedIn, ConnectionRepository connectionRepository) {
        this.linkedIn = linkedIn;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }

        LinkedInProfile userProfile = linkedIn.profileOperations().getUserProfile();
        TwitterProfileProxy proxy = new TwitterProfileProxy();
        CloneObject.clone(userProfile, proxy);
        repository.save(proxy);

        model.addAttribute("profile", userProfile);
        return "linkedInProfile";
    }
}
