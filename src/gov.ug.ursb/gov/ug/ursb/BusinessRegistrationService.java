package gov.ug.ursb;

import gov.ug.core.api.GovService;
import gov.ug.nira.api.NiraService;
import gov.ug.ura.api.TaxService;

public class BusinessRegistrationService implements GovService {
    public String department() {
        return "URSB - Business Registration";
    }

    public String serve(String nin) {
        if (!NiraService.verify(nin)) return "Registration rejected: invalid NIN";
        return "Business BRN-2026-" + nin.substring(10) + " registered, tax account " + TaxService.issueTin(nin);
    }
}
