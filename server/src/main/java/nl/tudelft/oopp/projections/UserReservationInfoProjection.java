package nl.tudelft.oopp.projections;

import java.sql.Date;
import java.sql.Time;

/**
 * This interface is used by Spring to project the selected columns into Java Objects
 * The getters specified here correspond to the selected columns in the SQL query.
 * @author Kanish Dwivedi
 */
public interface UserReservationInfoProjection {
    int getreservationID();

    Date getday();

    Time getstartTime();

    Time getendTime();

    String getbuildingName();

    String getroomName();

    String getname();
}
