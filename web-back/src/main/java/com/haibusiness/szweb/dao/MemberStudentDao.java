package com.haibusiness.szweb.dao;


import com.haibusiness.szweb.entity.MemberStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStudentDao extends JpaRepository<MemberStudent,Long> {
    Page<MemberStudent> findByTypeAndTitleLikeOrderByUpdateTimeDesc(String type, String title, Pageable pageable);
}
