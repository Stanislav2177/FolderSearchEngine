package com.string.search.via.web.String.search.repository;

import com.string.search.via.web.String.search.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestEntityRepo extends JpaRepository<RequestEntity, Long> {
}
