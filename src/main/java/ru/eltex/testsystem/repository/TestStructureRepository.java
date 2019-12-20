package ru.eltex.testsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.testsystem.dao.TestStructureRepositoryCustom;
import ru.eltex.testsystem.model.TestStructure;
@Repository
public interface TestStructureRepository extends MongoRepository<TestStructure,String>, TestStructureRepositoryCustom {

}
