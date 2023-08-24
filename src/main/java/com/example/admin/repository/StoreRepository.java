package com.example.admin.repository;

import com.example.admin.entity.Store;
import com.example.admin.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long>, StoreRepositoryExtension {

    Optional<Store> findByUser(User user);

    boolean existsByUser(User user);

    boolean existsByName(String name);

    @EntityGraph(attributePaths = {"user"})
    Optional<Store> findByIdAndUser(Long id, User user);


}
