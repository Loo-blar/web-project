package com.haibusiness.szweb.dao;

import com.haibusiness.szweb.entity.TeachingResearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingResearchDao extends JpaRepository<TeachingResearch,Long> {
    Page<TeachingResearch> findByTypeAndTitleLikeOrderByUpdateTimeDesc(String type, String title, Pageable pageable);

}
