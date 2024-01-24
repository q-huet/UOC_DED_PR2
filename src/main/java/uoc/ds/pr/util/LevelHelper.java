package uoc.ds.pr.util;

import uoc.ds.pr.CTTCompaniesJobsPR2.Level;

public class LevelHelper {
    public static Level getLevel(Integer level) {
        if (level < 10)
            return Level.BEFINNER;
        if (level < 200)
            return Level.INTERN;
        if (level < 500)
            return Level.JUNIOR;
        if (level < 1000)
            return Level.SENIOR;
        if (level >= 1000)
            return Level.EXPERT;
        return null;
    }

}
