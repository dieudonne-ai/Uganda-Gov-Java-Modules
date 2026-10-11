package gov.ug.ura.api;

import gov.ug.core.api.GovService;
import gov.ug.nira.api.NiraService;
import gov.ug.ura.internal.TaxCalculator;

public class TaxService implements GovService {
    public static String issueTin(String nin) {
        return "TIN-" + nin.substring(2, 10);
    }

    public String department() {
        return "URA - Tax Collection";
    }

    public String serve(String nin) {
        if (!NiraService.verify(nin)) return "Rejected: NIN not verified by NIRA";
        long income = 1500000;
        return issueTin(nin) + " | PAYE on UGX " + income + " = UGX " + TaxCalculator.payeTax(income);
    }
}
