package com.haibusiness.szweb.service;

import com.haibusiness.szweb.dao.EmploymentDao;
import com.haibusiness.szweb.entity.Employment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class EmploymentServiceImpl implements EmploymentService {

    private EmploymentDao employmentDao;
    @Override
    public Employment saveEmployment(Employment employment) {

        return employmentDao.save(employment);
    }

    @Override
    public void removeEmployment(Long id) {

        employmentDao.deleteById(id);
    }

    @Override
    public void removeEmploymentsInBatch(List<Employment> employments) {

        employmentDao.deleteInBatch(employments);
    }

    @Override
    public Employment updateEmployment(Employment employment) {

        return employmentDao.save(employment);
    }

    @Override
    public Employment getEmploymentById(Long id) {

        return employmentDao.getOne(id);
    }

    @Override
    public List<Employment> listEmployments() {

        return employmentDao.findAll();
    }
    @Override
    public Page<Employment> listEmploymentsByTypeAndTitleLike(String type, String title, Pageable pageable) {
        title = "%" + title + "%";
        return employmentDao.findByTypeAndTitleLikeOrderByUpdateTimeDesc(type, title, pageable);
    }


}
