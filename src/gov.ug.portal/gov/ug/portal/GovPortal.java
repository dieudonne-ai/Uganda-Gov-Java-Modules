package gov.ug.portal;

import gov.ug.core.api.GovService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

public class GovPortal {
    public static void main(String[] args) {
        System.out.println("=== UGANDA GOVERNMENT e-SERVICES PORTAL ===");
        System.out.println("\n[1] MODULES LOADED (each built by an independent department)");
        ModuleLayer.boot().modules().stream()
                .map(Module::getName)
                .filter(name -> name.startsWith("gov.ug"))
                .sorted()
                .forEach(name -> System.out.println("    - " + name));

        System.out.println("\n[2] SERVICES FOUND BY ServiceLoader (new departments plug in without changing the portal)");
        List<GovService> services = new ArrayList<>();
        ServiceLoader.load(GovService.class).forEach(services::add);
        services.sort(Comparator.comparing(GovService::department));
        for (String nin : new String[]{"CM90012345ABCD", "CM00000000FAKE"}) {
            System.out.println("    Citizen NIN: " + nin);
            for (GovService s : services) {
                System.out.println("      " + s.department() + " -> " + s.serve(nin));
            }
        }

        System.out.println("\n=== All requests handled. Sensitive packages stayed protected. ===");
    }

}
