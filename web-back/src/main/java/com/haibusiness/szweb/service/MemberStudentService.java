package com.haibusiness.szweb.service;

import com.haibusiness.szweb.entity.MemberStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberStudentService {
    MemberStudent saveMemberStudent(MemberStudent memberStudent);


    void removeMemberStudent(Long id);


    void removeMemberStudentsInBatch(List<MemberStudent> memberStudents);

    MemberStudent updateMemberStudent(MemberStudent memberStudent);

    MemberStudent getMemberStudentById(Long id);


    List<MemberStudent> listMemberStudents();


    Page<MemberStudent> listMemberStudentsByTypeAndTitleLike(String type, String title, Pageable pageable);
}
