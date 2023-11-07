package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month{
        
        January(31),
        February(29),
        March(31),
        April(30),
        May(31),
        June(30),
        July(31),
        August(31),
        September(30),
        October(31),
        November(30),
        December(31);
        
        private final int Days;

        private Month(int Days){
            this.Days = Days;
        }

        public int getDays(){
            return this.Days;
        }

        public static Month fromString(String month){

            int matchCount=0;
            Month monthFound=null;

            for (Month mese : Month.values()) {
                String temp = mese.name();
                if(temp.startsWith(month) || 
                   temp.toLowerCase().startsWith(month) ||
                   temp.toUpperCase().startsWith(month)){
                    matchCount++;
                    monthFound=mese;
                }
                
            }

            if(matchCount == 1){
                return monthFound;
            }

            return null;
        }        
    }


    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>(){

            public int compare(String s1,String s2){
                Month m1 = Month.fromString(s1);
                Month m2 = Month.fromString(s2);
                if(m1 == null || m2 == null){
                    throw new IllegalArgumentException("Invaild month name");
                }
                return Integer.compare(m1.getDays(), m2.getDays());
            }

        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>(){

            public int compare(String s1,String s2){
                Month m1 = Month.fromString(s1);
                Month m2 = Month.fromString(s2);
                if(m1 == null || m2 == null){
                    throw new IllegalArgumentException("Invaild month name");
                }
                return Integer.compare(m1.ordinal(), m2.ordinal());
            }

        };
    }
}
