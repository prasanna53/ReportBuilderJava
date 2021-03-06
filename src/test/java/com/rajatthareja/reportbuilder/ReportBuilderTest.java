package com.rajatthareja.reportbuilder;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Report Builder Test
 */
public class ReportBuilderTest {

    /**
     * Report Builder Test
     */
    @Test
    public void reportBuilderTest() throws Exception {
        try {
            String report = getClass().getResource("/com/rajatthareja/reportbuilder/report.html").getPath();
            String javaCucumberJson = report.replace("report.html", "cucumberJson/java");
            String rubyCucumberJson = report.replace("report.html", "cucumberJson/ruby");

            ReportBuilder reportBuilder = new ReportBuilder();
            reportBuilder.setReportTitle("Sample Test Report");
            reportBuilder.setAdditionalInfo("OS", "Mac OS X");
            reportBuilder.setAdditionalInfo("Browser", "Chrome");
            reportBuilder.setAdditionalInfo("Info", "More Info");
            reportBuilder.build(new File(javaCucumberJson), new File(rubyCucumberJson));

            assertEquals(new String(Files.readAllBytes(Paths.get(report))),
                    new String(Files.readAllBytes(Paths.get("report.html"))));
        } finally {
            Files.deleteIfExists(Paths.get("report.html"));
        }
    }
}
