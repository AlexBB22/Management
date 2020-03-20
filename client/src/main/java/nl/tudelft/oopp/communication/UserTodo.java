package nl.tudelft.oopp.communication;

import java.sql.Date;

public class UserTodo {
    private String title;
    private String day;

    /**
     * Empty constructor for Jackson object mapper.
     */
    public UserTodo() {

    }

    /**
     * Constructor for a new Todo object.
     * @param title - the title of the todo object.
     * @param day - the day this todo coressponds to
     */
    public UserTodo(String title, String day) {
        this.title = title;
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "UserTodo{" + "title='" + title + '\'' + ", day='" + day + '\'' + '}';
    }
}
