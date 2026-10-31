package com.vit.resultportal.service;

import com.vit.resultportal.model.StudentResult;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ResultService {
    public double weighted(double mse, double ese) { return mse * .30 + ese * .70; }
    public String grade(double mark) {
        if (mark >= 90) return "S"; if (mark >= 80) return "A"; if (mark >= 70) return "B";
        if (mark >= 60) return "C"; if (mark >= 50) return "D"; if (mark >= 40) return "E"; return "F";
    }
    public double point(String grade) { return switch (grade) { case "S" -> 10; case "A" -> 9; case "B" -> 8; case "C" -> 7; case "D" -> 6; case "E" -> 5; default -> 0; }; }
    public List<Map<String, Object>> subjectRows(StudentResult r) {
        return List.of(
                row("CSE2101", "Web Technologies", r.getMathematicsMse(), r.getMathematicsEse()),
                row("CSE2102", "Design and Analysis of Algorithms", r.getProgrammingMse(), r.getProgrammingEse()),
                row("CSE2103", "Computer Networks", r.getDatabaseMse(), r.getDatabaseEse()),
                row("CSE2104", "Data Science", r.getNetworksMse(), r.getNetworksEse()));
    }
    private Map<String, Object> row(String code, String subject, double mse, double ese) {
        double total = weighted(mse, ese); String grade = grade(total);
        Map<String, Object> row = new LinkedHashMap<>(); row.put("code", code); row.put("subject", subject); row.put("mse", mse); row.put("ese", ese); row.put("total", total); row.put("grade", grade); row.put("point", point(grade)); return row;
    }
}
