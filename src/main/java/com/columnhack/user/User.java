package com.columnhack.user;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.*;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private UserType type;
    private Date date;

    public User() {
    }

    public User(long id, String firstName, String lastName, UserType type, Date data) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.date = data;
    }

    public long getId() {
        return id;
    }

    public UserType getType() {

        return type;
    }

//    public int getDiffYears(Date first, Date last) {
//        Calendar a = getCalendar(first);
//        Calendar b = getCalendar(last);
//        int diff = b.get(YEAR) - a.get(YEAR);
//        if (a.get(MONTH) > b.get(MONTH) ||
//                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
//            diff--;
//        }
//        return diff;
//    }

    public int getDiffYears(Date last) {
        Calendar a = getCalendar(date);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
