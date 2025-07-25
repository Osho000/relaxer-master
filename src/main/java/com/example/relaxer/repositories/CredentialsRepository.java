package com.example.relaxer.repositories;

import com.example.relaxer.entity.Account;
import com.example.relaxer.entity.Credentials;
import com.example.relaxer.entity.Role;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Optional<Credentials> findByUserName(String name);
}
