package com.haibusiness.szweb.service;

import com.haibusiness.szweb.dao.MemberStudentDao;
import com.haibusiness.szweb.entity.MemberStudent;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MemberStudentServiceImpl implements MemberStudentService {

    private MemberStudentDao memberStudentDao;
    @Override
    public MemberStudent saveMemberStudent(MemberStudent memberStudent) {

        return memberStudentDao.save(memberStudent);
    }

    @Override
    public void removeMemberStudent(Long id) {

        memberStudentDao.deleteById(id);
    }

    @Override
    public void removeMemberStudentsInBatch(List<MemberStudent> memberStudents) {

        memberStudentDao.deleteInBatch(memberStudents);
    }

    @Override
    public MemberStudent updateMemberStudent(MemberStudent memberStudent) {

        return memberStudentDao.save(memberStudent);
    }

    @Override
    public MemberStudent getMemberStudentById(Long id) {

        return memberStudentDao.getOne(id);
    }

    @Override
    public List<MemberStudent> listMemberStudents() {

        return memberStudentDao.findAll();
    }

    @Override
    public Page<MemberStudent> listMemberStudentsByTypeAndTitleLike(String type,String title, Pageable pageable) {
        title = "%" + title + "%";
        return memberStudentDao.findByTypeAndTitleLikeOrderByUpdateTimeDesc(type, title, pageable);
    }


}
