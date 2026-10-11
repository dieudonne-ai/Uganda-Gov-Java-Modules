package gov.ug.nira.internal;

import java.util.Map;

public class NinRegistry {
    private static final Map<String, String[]> CITIZENS = Map.of(
        "CM90012345ABCD", new String[]{"Nakato Sarah", "12/03/1990", "Kampala"},
        "CM85067890EFGH", new String[]{"Okello James", "05/07/1985", "Gulu"},
        "CF98011223IJKL", new String[]{"Mukasa Brian", "21/11/1998", "Mbarara"});

    public static String[] lookup(String nin) {
        return CITIZENS.get(nin);
    }
}
