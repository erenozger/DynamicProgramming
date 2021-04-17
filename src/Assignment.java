import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Assignment implements Comparable<Assignment> {
    private String name;
    private String start;
    private int duration;
    private int importance;
    private boolean maellard;


    /*
            Getter methods
         */
    public String getName() {
        return name;
    }

    public String getStartTime() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    public int getImportance() {
        return importance;
    }

    public boolean isMaellard() {
        return maellard;
    }

    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() {
        /*Date format is determined,
        then Start time and date are created and the deadline is determined by adding the time.
        Converts and returns the last date to hour and minute types by splitting. */
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date d = df.parse(this.getStartTime());
            Calendar gc = new GregorianCalendar();
            gc.setTime(d);
            gc.add(Calendar.HOUR, this.getDuration());
            Date d2 = gc.getTime();
            String[] splitDate = d2.toString().split(" ");
            String[] splitHour = splitDate[3].split(":");
            return splitHour[0] + ":" + splitHour[1];
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    public double getWeight() {
        return (double) this.getImportance() * (this.isMaellard() ? 1001 : 1) / this.getDuration();
    }

    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    @Override
    public int compareTo(Assignment o) {
        return this.getFinishTime().compareTo(o.getFinishTime());
    }

    /**
     * @return Should return a string in the following form:
     * Assignment{name='Refill vending machines', start='12:00', duration=1, importance=45, maellard=false, finish='13:00', weight=45.0}
     */
    @Override
    public String toString() {
        return "Assignment{name='" + getName() + "', start='" + getStartTime() + "', duration=" + getDuration() + ", importance=" + getImportance() +
                ", maellard=" + isMaellard() + ", finish='" + getFinishTime() + "', weight=" + getWeight() + "}";
    }
}
