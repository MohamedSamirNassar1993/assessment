package com.stc.assessment.repository;

import com.stc.assessment.common.repository.BaseRepository;
import com.stc.assessment.model.entities.File;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends BaseRepository<File>, JpaSpecificationExecutor<File> {

}