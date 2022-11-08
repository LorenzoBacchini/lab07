package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final Comparator<String> BY_DATE = new SortByDate();
    private static final Comparator<String> BY_ORDER = new SortByMonthOrder();

    enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        private Month(int days){
            this.days = days;
        }

        private static Month fromString(String name){
            Objects.requireNonNull(name);
            try{
                return valueOf(name);
            }catch(IllegalArgumentException e){
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
        return BY_DATE;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BY_ORDER;
    }

    private static class SortByMonthOrder implements Comparator<String>{

        @Override
        public int compare(String s1, String s2) {
            Month m1 = Month.fromString(s1);
            Month m2 = Month.fromString(s2);
            return Integer.compare(m1.ordinal(), m2.ordinal());
        }

    }

    private static class SortByDate implements Comparator<String>{

        @Override
        public int compare(String s1, String s2) {
            Month m1 = Month.fromString(s1);
            Month m2 = Month.fromString(s2);
            return Integer.compare(m1.days, m2.days);
        }

    }
}
