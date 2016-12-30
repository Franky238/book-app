package com.library.repository;

import com.library.entity.MimeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MimeTypeRepository extends JpaRepository<MimeTypeEntity, Long>{
    MimeTypeEntity findByType(String type);
}
