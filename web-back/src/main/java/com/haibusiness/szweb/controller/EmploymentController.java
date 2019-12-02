package com.haibusiness.szweb.controller;

import com.haibusiness.szweb.entity.Employment;
import com.haibusiness.szweb.entity.User;
import com.haibusiness.szweb.service.EmploymentService;
import com.haibusiness.szweb.service.MenuService;
import com.haibusiness.szweb.vo.Response;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class EmploymentController {
    private EmploymentService employmentService;
    private MenuService menuService;

    @GetMapping("/public/employment/{type}")
    public ResponseEntity list(
            @PathVariable("type") String type,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "title", required = false, defaultValue = "") String title
    ) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Employment> page = employmentService.listEmploymentsByTypeAndTitleLike(type,title,pageable);
        Map model=new HashMap<>();
        model.put("page", page);
        model.put("name", menuService.getMenus().get(type));
        model.put("type", type);
        model.put("title", menuService.getMenus().get("employment"));
        return ResponseEntity.ok().body(model);
    }

    @PutMapping(value = "/public/employment/{id}")
    public ResponseEntity addHit(@PathVariable Long id) {
        try {
            Employment employment = employmentService.getEmploymentById(id);
            employment.setHit( employment.getHit()+1);
            employment.setType(employment.getType());
            employmentService.saveEmployment(employment);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "修改浏览次数失败！"));
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    /**
     * 新建
     *
     * @param employment
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping(value = "/employment/{type}")
    public ResponseEntity<Response> createDaogou(@PathVariable String type,@RequestBody Employment employment) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            employment.setUpdateUser(user);
            employment.setType(type);
            employmentService.saveEmployment(employment);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 更新
     *
     * @param employment
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping(value = "/employment")
    public ResponseEntity<Response> updateEmployment(@RequestBody Employment employment) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            employment.setUpdateUser(user);
            //更新,对于表单中没有出现的字段要把原来的值付给它们
            Employment originalEmployment = employmentService.getEmploymentById(employment.getId());
            employment.setHit(originalEmployment.getHit());
            employmentService.saveEmployment(employment);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "更新失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }

    /**
     * 删除
     *
     * @param id
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping(value = "/employment/{id}")
    public ResponseEntity<Response> deleteEmployment(@PathVariable Long id) {
        try {
            employmentService.removeEmployment(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "删除失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }
}
