package com.stc.assessment.repository;

import com.stc.assessment.common.repository.BaseRepository;
import com.stc.assessment.model.entities.PermissionGroup;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionGroupRepository extends BaseRepository<PermissionGroup>, JpaSpecificationExecutor<PermissionGroup> {

    Optional<PermissionGroup> findByName(String name);
}