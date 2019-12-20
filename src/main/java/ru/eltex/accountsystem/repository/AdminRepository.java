package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.accountsystem.model.users.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
}
