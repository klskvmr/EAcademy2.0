package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.accountsystem.model.UserRole;

@Repository
public interface AllUserRepository extends MongoRepository<UserRole, String> {
    UserRole findByUsername(String username);
}