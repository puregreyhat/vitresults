package com.vit.resultportal.dto;

import java.util.List;

public class StudentResultResponse {
    private Long id;
    private String registrationNumber;
    private String studentName;
    private String programme;
    private String semester;
    private List<SubjectResult> subjects;
    private double aggregate;
    private double cgpa;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getProgramme() { return programme; }
    public void setProgramme(String programme) { this.programme = programme; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public List<SubjectResult> getSubjects() { return subjects; }
    public void setSubjects(List<SubjectResult> subjects) { this.subjects = subjects; }
    public double getAggregate() { return aggregate; }
    public void setAggregate(double aggregate) { this.aggregate = aggregate; }
    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static class SubjectResult {
        private String code;
        private String subject;
        private double mse;
        private double ese;
        private double total;
        private String grade;
        private double point;

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        public double getMse() { return mse; }
        public void setMse(double mse) { this.mse = mse; }
        public double getEse() { return ese; }
        public void setEse(double ese) { this.ese = ese; }
        public double getTotal() { return total; }
        public void setTotal(double total) { this.total = total; }
        public String getGrade() { return grade; }
        public void setGrade(String grade) { this.grade = grade; }
        public double getPoint() { return point; }
        public void setPoint(double point) { this.point = point; }
    }
}
