package br.com.ifood.advancedtest.spotify.repository.search;

import br.com.ifood.advancedtest.spotify.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {
}
