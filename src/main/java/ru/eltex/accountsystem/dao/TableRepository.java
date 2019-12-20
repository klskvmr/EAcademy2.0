package ru.eltex.accountsystem.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.Table;

public interface TableRepository extends MongoRepository <Table,String>{
}
