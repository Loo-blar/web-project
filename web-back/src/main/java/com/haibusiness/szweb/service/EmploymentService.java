package com.haibusiness.szweb.service;


import com.haibusiness.szweb.entity.Employment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmploymentService {
    Employment saveEmployment(Employment dynamic);


    void removeEmployment(Long id);


    void removeEmploymentsInBatch(List<Employment> employments);

    Employment updateEmployment(Employment employment);

    Employment getEmploymentById(Long id);


    List<Employment> listEmployments();


    Page<Employment> listEmploymentsByTypeAndTitleLike(String type, String title, Pageable pageable);
}
