package com.library.repository;

import com.library.entity.ExtensionEntity;
import com.library.entity.MimeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtensionRepository extends JpaRepository<ExtensionEntity, Long>{

}
