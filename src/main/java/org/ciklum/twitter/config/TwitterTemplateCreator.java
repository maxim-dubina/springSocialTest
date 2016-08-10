package org.ciklum.twitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ccc on 17.05.2016.
 */
@Component
public class TwitterTemplateCreator {
    @Autowired
    private Environment env;

    public Twitter getTwitterTemplate(String accountPrefix) {
        String consumerKey = env.getProperty(accountPrefix + ".consumerKey");
        String consumerSecret = env.getProperty(accountPrefix + ".consumerSecret");
        String accessToken = env.getProperty(accountPrefix + ".accessToken");
        String accessTokenSecret = env.getProperty(accountPrefix + ".accessTokenSecret");

        return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }
}