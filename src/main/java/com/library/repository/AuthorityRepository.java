package com.library.repository;

import com.library.security.entity.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findByName(String name);
    List<Authority> findByDefaultAuthority(boolean isDefault);
}
