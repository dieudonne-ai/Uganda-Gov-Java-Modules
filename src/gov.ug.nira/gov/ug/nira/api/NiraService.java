package gov.ug.nira.api;

import gov.ug.core.api.GovService;
import gov.ug.nira.internal.NinRegistry;

public class NiraService implements GovService {
    public static boolean verify(String nin) {
        return NinRegistry.lookup(nin) != null;
    }

    public String department() {
        return "NIRA - National Identification";
    }

    public String serve(String nin) {
        return verify(nin) ? "NIN " + nin + " is VALID" : "NIN " + nin + " NOT FOUND";
    }
}
