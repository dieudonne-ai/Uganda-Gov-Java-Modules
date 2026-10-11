package gov.ug.immigration;

import gov.ug.core.api.GovService;
import gov.ug.nira.records.CitizenRecord;

public class PassportService implements GovService {
    public String department() {
        return "DCIC - Passport Processing";
    }

    public String serve(String nin) {
        CitizenRecord r = CitizenRecord.fetch(nin);
        if (r == null) return "Passport rejected: citizen not found";
        return "Passport issued to " + r.name + " (born " + r.dateOfBirth + ", " + r.district + ")";
    }
}
