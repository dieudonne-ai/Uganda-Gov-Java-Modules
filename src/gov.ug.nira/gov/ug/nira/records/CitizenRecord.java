package gov.ug.nira.records;

import gov.ug.nira.internal.NinRegistry;

public class CitizenRecord {
    public final String name, dateOfBirth, district;

    private CitizenRecord(String[] data) {
        name = data[0];
        dateOfBirth = data[1];
        district = data[2];
    }

    public static CitizenRecord fetch(String nin) {
        String[] data = NinRegistry.lookup(nin);
        return data == null ? null : new CitizenRecord(data);
    }
}
