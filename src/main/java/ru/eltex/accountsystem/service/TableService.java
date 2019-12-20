package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.TableRepository;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Table;
import ru.eltex.accountsystem.repository.GroupRepository;

@Service
public class TableService {
    private final GroupRepository groupRepository;
    private final TableRepository tableRepository;

    @Autowired
    public TableService(GroupRepository groupRepository, TableRepository tableRepository) {
        this.groupRepository = groupRepository;
        this.tableRepository = tableRepository;
    }
//    public void saveTable(Table request,String idGroup){
//        Group group = groupRepository.findById(idGroup).get();
//        group.setTable(request.getId());
//        groupRepository.save(group);
//        tableRepository.save(request);
//    }
    public Table loadTable(String idGroup){
        Group group = groupRepository.findById(idGroup).get();
        return tableRepository.findById(group.getTable()).get();
    }
}
