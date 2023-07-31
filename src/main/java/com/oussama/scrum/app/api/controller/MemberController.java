package com.oussama.scrum.app.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oussama.scrum.app.api.entity.TaskEntity;
import com.oussama.scrum.app.api.model.Member;
import com.oussama.scrum.app.api.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
//@CrossOrigin(value = {"http://localhost:3000", "http://localhost:3000/add"})
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class MemberController {
    @Autowired
    private MemberService MemberService;

    public MemberController(MemberService memberService) {
        MemberService = memberService;
    }

    @PostMapping("/members")
    public Member createMember(@RequestParam("memberData") String memberData, @RequestParam("file") MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Member member = objectMapper.readValue(memberData, Member.class);
            String imageUrl = saveImageToServerAndGetImageUrl(file);
            member.setImg(imageUrl);
            return this.MemberService.createMember(member);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return this.MemberService.getAllMembers();
    }

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable int id) {
        return this.MemberService.getMember(id);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMember(@PathVariable int id) {
        boolean deleted = false;
        deleted = this.MemberService.deleteMember(id);
        Map<String, Boolean> respone = new HashMap<>();
        respone.put("deleted", deleted);
        return ResponseEntity.ok(respone);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member member) {
        this.MemberService.updateMember(id, member);
        return ResponseEntity.ok(member);
    }

   // saving images on the server:
    private final String IMG_UPLOAD_DIR = "/home/oussma/Downloads/ScrumFlowTracker/scrum.app.api/src/images/";

    public  String saveImageToServerAndGetImageUrl( MultipartFile file) {
        try {
            System.out.println("Received file: " + file.getOriginalFilename() + ", size: " + file.getSize());
            // Generate a unique filename for the uploaded image
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            // Save the image to the specified upload directory
            File imageFile = new File(IMG_UPLOAD_DIR + fileName);
            file.transferTo(imageFile);
            System.out.println(imageFile);
            // Return the image URL to the client
            return "http://localhost:8080/images/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading the image.";
        }
    }
    // This method handles GET requests for serving the images
    @GetMapping("/{fileName:.+}")
    @ResponseBody
    public Resource serveImage(@PathVariable String fileName) {
        // Load the image as a Resource and serve it to the client
        return (Resource) new FileSystemResource(IMG_UPLOAD_DIR + fileName);
    }


    //Tasks methods
    @DeleteMapping("/members/task/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable int id) {
        boolean deleted = false;
        deleted = this.MemberService.deleteTask(id);
        Map<String, Boolean> respone = new HashMap<>();
        respone.put("deleted", deleted);
        return ResponseEntity.ok(respone);
    }

    @GetMapping("/members/task/{id}")
    public TaskEntity getTask(@PathVariable int id) {
        return this.MemberService.getTask(id);
    }

    @PutMapping("/members/task/{id}")
    public TaskEntity updateTask(@RequestBody TaskEntity task, @PathVariable int id) {
        return this.MemberService.updateTask(id, task);
    }

    @PutMapping("/members/task/add/{idMember}")
    public TaskEntity addTask(@PathVariable int idMember, @RequestBody TaskEntity t) {
        System.out.println(t);
        this.MemberService.addTask(idMember, t);

        return t;
    }
}
