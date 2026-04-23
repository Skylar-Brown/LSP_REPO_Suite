package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class that demonstrates polymorphism with Report objects.
 */
public class Driver {

    /**
     * Runs the report generation program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();

        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
        }
    }
}