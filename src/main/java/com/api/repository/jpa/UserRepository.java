package com.api.repository.jpa;

import com.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("jpaUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
