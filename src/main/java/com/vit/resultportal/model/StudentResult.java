package com.vit.resultportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_results")
public class StudentResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String registrationNumber;
    @Column(nullable = false) private String studentName;
    @Column(nullable = false) private String programme;
    @Column(nullable = false) private String semester;
    @Column(nullable = false) private Double mathematicsMse;
    @Column(nullable = false) private Double mathematicsEse;
    @Column(nullable = false) private Double programmingMse;
    @Column(nullable = false) private Double programmingEse;
    @Column(nullable = false) private Double databaseMse;
    @Column(nullable = false) private Double databaseEse;
    @Column(nullable = false) private Double networksMse;
    @Column(nullable = false) private Double networksEse;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getRegistrationNumber() { return registrationNumber; } public void setRegistrationNumber(String v) { registrationNumber = v; }
    public String getStudentName() { return studentName; } public void setStudentName(String v) { studentName = v; }
    public String getProgramme() { return programme; } public void setProgramme(String v) { programme = v; }
    public String getSemester() { return semester; } public void setSemester(String v) { semester = v; }
    public Double getMathematicsMse() { return mathematicsMse; } public void setMathematicsMse(Double v) { mathematicsMse = v; }
    public Double getMathematicsEse() { return mathematicsEse; } public void setMathematicsEse(Double v) { mathematicsEse = v; }
    public Double getProgrammingMse() { return programmingMse; } public void setProgrammingMse(Double v) { programmingMse = v; }
    public Double getProgrammingEse() { return programmingEse; } public void setProgrammingEse(Double v) { programmingEse = v; }
    public Double getDatabaseMse() { return databaseMse; } public void setDatabaseMse(Double v) { databaseMse = v; }
    public Double getDatabaseEse() { return databaseEse; } public void setDatabaseEse(Double v) { databaseEse = v; }
    public Double getNetworksMse() { return networksMse; } public void setNetworksMse(Double v) { networksMse = v; }
    public Double getNetworksEse() { return networksEse; } public void setNetworksEse(Double v) { networksEse = v; }
}
