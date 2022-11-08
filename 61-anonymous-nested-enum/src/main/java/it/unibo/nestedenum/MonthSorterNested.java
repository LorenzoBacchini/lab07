package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    enum Month{
        GENNAIO(31), FEBBRAIO(28), MARZO(31),
        APRILE(30), MAGGIO(31), GIUGNO(30),
        LUGLIO(31), AGOSTO(31), SETTEMBRE(30),
        OTTOBRE(31), NOVEMBRE(30), DICEMBRE(31);

        private final int days;

        private Month(int days){
            this.days = days;
        }

        private Month fromString(String name){
            Objects.requireNonNull(name);
            try{
                return valueOf(name);
            }catch(Exception e){
                Month find = null;
                for (final Month month: values()) {
                    if (month.toString().toLowerCase(Locale.ROOT).startsWith(name.toLowerCase(Locale.ROOT))) {
                        if (find != null) {
                            throw new IllegalArgumentException(name + "is ambiguous");
                        }
                        find = month;
                    }
                }
                if (find == null) {
                    throw new IllegalArgumentException("Impossible to find Month: " + name);
                }
                return find;
            }
        }

    }
    
    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }
}
