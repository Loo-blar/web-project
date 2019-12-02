package com.haibusiness.szweb.controller;

import com.haibusiness.szweb.entity.MemberStudent;
import com.haibusiness.szweb.entity.User;
import com.haibusiness.szweb.service.MemberStudentService;
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
public class MemberStudentController {
    private MemberStudentService memberStudentService;
    private MenuService menuService;

    @GetMapping("/public/memberStudent/{type}")
    public ResponseEntity list(
            @PathVariable("type") String type,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "title", required = false, defaultValue = "") String title
    ) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<MemberStudent> page = memberStudentService.listMemberStudentsByTypeAndTitleLike(type,title,pageable);
        Map model=new HashMap<>();
        model.put("page", page);
        model.put("name", menuService.getMenus().get(type));
        model.put("type", type);
        model.put("title", menuService.getMenus().get("memberStudent"));
        return ResponseEntity.ok().body(model);
    }
    @PutMapping(value = "/public/memberStudent/{id}")
    public ResponseEntity addHit(@PathVariable Long id) {
        try {
            MemberStudent memberStudent = memberStudentService.getMemberStudentById(id);
            memberStudent.setHit( memberStudent.getHit()+1);
            memberStudent.setType(memberStudent.getType());
            memberStudentService.saveMemberStudent(memberStudent);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "修改浏览次数失败！"));
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
    /**
     * 新建
     *
     * @param memberStudent
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping(value = "/memberStudent/{type}")
    public ResponseEntity<Response> createDaogou(@PathVariable String type,@RequestBody MemberStudent memberStudent) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            memberStudent.setUpdateUser(user);
            memberStudent.setType(type);
            memberStudentService.saveMemberStudent(memberStudent);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 更新
     *
     * @param memberStudent
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping(value = "/memberStudent")
    public ResponseEntity<Response> updateMemberStudent(@RequestBody MemberStudent memberStudent) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            memberStudent.setUpdateUser(user);
            //更新,对于表单中没有出现的字段要把原来的值付给它们
            MemberStudent originalMemberStudent = memberStudentService.getMemberStudentById(memberStudent.getId());
            memberStudent.setHit(originalMemberStudent.getHit());
            memberStudentService.saveMemberStudent(memberStudent);
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
    @DeleteMapping(value = "/memberStudent/{id}")
    public ResponseEntity<Response> deleteMemberStudent(@PathVariable Long id) {
        try {
            memberStudentService.removeMemberStudent(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "删除失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }
}
