package br.com.ifood.advancedtest.spotify.repository;

import br.com.ifood.advancedtest.spotify.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
