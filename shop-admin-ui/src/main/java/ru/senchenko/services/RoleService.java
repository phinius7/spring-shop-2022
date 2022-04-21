package ru.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senchenko.dao.RoleDao;
import ru.senchenko.entities.Role;
import ru.senchenko.repositories.RoleRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService implements CrudInterface<RoleDao> {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }


    @Override
    @Transactional
    public void create(RoleDao dao) {
        Role role = new Role();
        role.setId(dao.getId());
        role.setCreateDate(dao.getCreateDate());
        role.setModifyDate(dao.getModifyDate());
        role.setTitle(dao.getTitle());
        roleRepo.save(role);
    }

    @Override
    public List<RoleDao> readAll() {
        return roleRepo.findAll().stream().map(RoleDao::new).collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDao> readById(Integer id) {
        return roleRepo.findById(id).map(RoleDao::new);
    }

    @Override
    public void delete(Integer id) {
        roleRepo.deleteById(id);
    }
}
