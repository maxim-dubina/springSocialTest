package org.ciklum.twitter;

import org.ciklum.twitter.config.TwitterTemplateCreator;
import org.ciklum.utils.CloneObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/twitter")
public class TwitterController {
    @Autowired
    private TwitterRepository repository;
    private Twitter twitter;

    @Inject
    public TwitterController(TwitterTemplateCreator templateCreator) {
        twitter = templateCreator.getTwitterTemplate("spring.social.test");
    }

    @RequestMapping(method=RequestMethod.GET)
    public String helloTwitter(Model model) {

        TwitterProfile userProfile = twitter.userOperations().getUserProfile();
        TwitterProfileProxy profileProxy = new TwitterProfileProxy();
        CloneObject.clone(userProfile, profileProxy);
        repository.save(profileProxy);

        model.addAttribute("profile", twitter.userOperations().getUserProfile());
        return "twitterProfile";
    }

}
