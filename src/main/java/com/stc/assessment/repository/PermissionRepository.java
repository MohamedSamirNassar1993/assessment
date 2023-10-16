package com.stc.assessment.repository;

import com.stc.assessment.common.repository.BaseRepository;
import com.stc.assessment.model.entities.Permission;
import com.stc.assessment.model.entities.PermissionGroup;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends BaseRepository<Permission>, JpaSpecificationExecutor<Permission> {

    Optional<Permission> findByUserEmailAndPermissionGroup(String email , PermissionGroup permissionGroup);
    List<Permission> findByUserEmail(String email);
}