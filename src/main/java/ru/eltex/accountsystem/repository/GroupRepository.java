package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.accountsystem.model.Group;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
}
