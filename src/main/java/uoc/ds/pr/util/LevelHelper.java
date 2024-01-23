package uoc.ds.pr.util;

import uoc.ds.pr.*;

public class LevelHelper {
    public static CTTCompaniesJobsPR2.Level getLevel(Integer level) {
        if (level < 10)
            return CTTCompaniesJobsPR2.Level.BEFINNER;
        if (level < 200)
            return CTTCompaniesJobsPR2.Level.INTERN;
        if (level < 500)
            return CTTCompaniesJobsPR2.Level.JUNIOR;
        if (level < 1000)
            return CTTCompaniesJobsPR2.Level.SENIOR;
        if (level >= 1000)
            return CTTCompaniesJobsPR2.Level.EXPERT;
        return null;
    }

}
