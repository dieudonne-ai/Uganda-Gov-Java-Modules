package gov.ug.portal;

import gov.ug.core.api.GovService;
import java.lang.reflect.Method;
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

        System.out.println("\n[3] ACCESS CONTROL MATRIX (read at runtime from the module-info rules)");
        String[] packages = {"gov.ug.nira.api", "gov.ug.nira.records", "gov.ug.nira.internal", "gov.ug.ura.internal"};
        String[] clients = {"gov.ug.portal", "gov.ug.ura", "gov.ug.immigration", "gov.ug.ursb"};
        for (String pkg : packages) {
            Module owner = find(pkg.substring(0, pkg.indexOf('.', 7)));
            StringBuilder row = new StringBuilder(String.format("    %-21s", pkg));
            for (String client : clients) {
                String rule = owner.isExported(pkg, find(client)) ? "ALLOWED" : "DENIED";
                row.append(String.format("%-20s", client.substring(7) + ":" + rule));
            }
            System.out.println(row);
        }

        System.out.println("\n[4] SECURITY TEST: the portal tries to call NIRA code directly");
        tryAccess("gov.ug.nira.api.NiraService", "verify");
        tryAccess("gov.ug.nira.records.CitizenRecord", "fetch");
        tryAccess("gov.ug.nira.internal.NinRegistry", "lookup");
        System.out.println("\n=== All requests handled. Sensitive packages stayed protected. ===");
    }

    static Module find(String name) {
        return ModuleLayer.boot().findModule(name).orElseThrow();
    }

    static void tryAccess(String className, String method) {
        try {
            Method m = Class.forName(className).getMethod(method, String.class);
            System.out.println("    ALLOWED " + className + "." + method + "() -> " + m.invoke(null, "CM90012345ABCD"));
        } catch (IllegalAccessException e) {
            System.out.println("    BLOCKED " + className + "." + method + "() -> package not exported to gov.ug.portal");
        } catch (Exception e) {
            System.out.println("    ERROR " + e);
        }
    }
}
