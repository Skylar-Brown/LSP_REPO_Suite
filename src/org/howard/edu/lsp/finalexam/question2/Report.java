package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class for reports using the Template Method pattern.
 * The generateReport method defines the fixed workflow for all reports.
 */
public abstract class Report {

    /**
     * Loads the data needed for the report.
     */
    protected abstract void loadData();

    /**
     * Formats the report-specific header.
     *
     * @return formatted header text
     */
    protected abstract String formatHeader();

    /**
     * Formats the report-specific body.
     *
     * @return formatted body text
     */
    protected abstract String formatBody();

    /**
     * Formats the report-specific footer.
     *
     * @return formatted footer text
     */
    protected abstract String formatFooter();

    /**
     * Template method that defines the fixed report generation workflow.
     */
    public final void generateReport() {
        loadData();

        System.out.println("=== HEADER ===");
        System.out.println(formatHeader());

        System.out.println("=== BODY ===");
        System.out.println(formatBody());

        System.out.println("=== FOOTER ===");
        System.out.println(formatFooter());
    }
}