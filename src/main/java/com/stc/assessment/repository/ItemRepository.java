package com.stc.assessment.repository;

import com.stc.assessment.common.repository.BaseRepository;
import com.stc.assessment.model.entities.Item;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends BaseRepository<Item>, JpaSpecificationExecutor<Item> {

    @Query(value = "SELECT id as a FROM item i "
            + "WHERE i.permission_group_id IN (:permissionGroupsList) ", nativeQuery = true)
    List<Long> findAllItemsByPermissionGroups(List<Long> permissionGroupsList);

}