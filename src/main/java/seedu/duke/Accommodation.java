package seedu.duke;

import java.time.LocalDate;

public class Accommodation extends TravelActivity {
    private LocalDate date;
    private String duration;

    public Accommodation(String line, LocalDate date, String duration){
        super(line, date, duration);
    }

    @Override
    public String toString(){
        return "Accommodation: " + super.toString();
    }

}
