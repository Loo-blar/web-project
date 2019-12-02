package com.haibusiness.szweb.dao;


import com.haibusiness.szweb.entity.Collaboration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaborationDao extends JpaRepository<Collaboration,Long> {
    Page<Collaboration> findByTypeAndTitleLikeOrderByUpdateTimeDesc(String type, String title, Pageable pageable);

}
