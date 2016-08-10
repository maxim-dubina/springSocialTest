package org.ciklum.twitter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ccc on 18.05.2016.
 */
@Repository
public interface TwitterRepository extends MongoRepository<TwitterProfileProxy, String> {
}
