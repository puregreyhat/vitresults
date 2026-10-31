package com.vit.resultportal.controller;

import com.vit.resultportal.dto.StudentResultResponse;
import com.vit.resultportal.model.StudentResult;
import com.vit.resultportal.repository.StudentResultRepository;
import com.vit.resultportal.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class ResultController {
    private final StudentResultRepository repository;
    private final ResultService resultService;

    public ResultController(StudentResultRepository repository, ResultService resultService) {
        this.repository = repository;
        this.resultService = resultService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("result", new StudentResult());
        return "index";
    }

    @PostMapping("/results")
    public String save(@ModelAttribute StudentResult result, RedirectAttributes flash) {
        repository.findByRegistrationNumberIgnoreCase(result.getRegistrationNumber())
                .ifPresent(old -> result.setId(old.getId()));
        StudentResult saved = repository.save(result);
        flash.addFlashAttribute("notice", "Result prepared and saved successfully.");
        return "redirect:/results/" + saved.getId();
    }

    @GetMapping("/results/{id}")
    public String resultPage(@PathVariable Long id, Model model) {
        StudentResultResponse response = buildResponse(repository.findById(id).orElseThrow());
        model.addAttribute("student", response);
        model.addAttribute("subjects", response.getSubjects());
        model.addAttribute("aggregate", response.getAggregate());
        model.addAttribute("cgpa", response.getCgpa());
        model.addAttribute("status", response.getStatus());
        return "result";
    }

    @GetMapping("/lookup")
    public String lookup(@RequestParam String registrationNumber, RedirectAttributes flash) {
        return repository.findByRegistrationNumberIgnoreCase(registrationNumber)
                .map(r -> "redirect:/results/" + r.getId())
                .orElseGet(() -> {
                    flash.addFlashAttribute("error", "No saved result was found for that registration number.");
                    return "redirect:/";
                });
    }

    @PostMapping("/api/results")
    @ResponseBody
    public ResponseEntity<StudentResultResponse> createApi(@RequestBody StudentResult result) {
        repository.findByRegistrationNumberIgnoreCase(result.getRegistrationNumber())
                .ifPresent(old -> result.setId(old.getId()));
        StudentResult saved = repository.save(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(buildResponse(saved));
    }

    @GetMapping("/api/results/{id}")
    @ResponseBody
    public ResponseEntity<StudentResultResponse> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(result -> ResponseEntity.ok(buildResponse(result)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/results")
    @ResponseBody
    public ResponseEntity<StudentResultResponse> getByRegistrationNumber(@RequestParam String registrationNumber) {
        return repository.findByRegistrationNumberIgnoreCase(registrationNumber)
                .map(result -> ResponseEntity.ok(buildResponse(result)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/results/all")
    @ResponseBody
    public List<StudentResultResponse> allResults() {
        return repository.findAll().stream().map(this::buildResponse).toList();
    }

    private StudentResultResponse buildResponse(StudentResult student) {
        List<Map<String, Object>> subjectRows = resultService.subjectRows(student);
        double aggregate = subjectRows.stream().mapToDouble(s -> (double) s.get("total")).average().orElse(0);
        double cgpa = subjectRows.stream().mapToDouble(s -> (double) s.get("point")).average().orElse(0);

        StudentResultResponse response = new StudentResultResponse();
        response.setId(student.getId());
        response.setRegistrationNumber(student.getRegistrationNumber());
        response.setStudentName(student.getStudentName());
        response.setProgramme(student.getProgramme());
        response.setSemester(student.getSemester());
        response.setAggregate(aggregate);
        response.setCgpa(cgpa);
        response.setStatus(subjectRows.stream().anyMatch(s -> "F".equals(s.get("grade"))) ? "Not Cleared" : "Cleared");

        List<StudentResultResponse.SubjectResult> subjects = subjectRows.stream().map(row -> {
            StudentResultResponse.SubjectResult subject = new StudentResultResponse.SubjectResult();
            subject.setCode((String) row.get("code"));
            subject.setSubject((String) row.get("subject"));
            subject.setMse((double) row.get("mse"));
            subject.setEse((double) row.get("ese"));
            subject.setTotal((double) row.get("total"));
            subject.setGrade((String) row.get("grade"));
            subject.setPoint((double) row.get("point"));
            return subject;
        }).toList();
        response.setSubjects(subjects);
        return response;
    }
}
