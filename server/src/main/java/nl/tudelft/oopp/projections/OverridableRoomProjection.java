package nl.tudelft.oopp.projections;

/**
 * This interface is used by Spring to project the selected columns into Java Objects
 * The getters specified here correspond to the selected columns in the SQL query.
 * @author Kanish Dwivedi
 */
public interface OverridableRoomProjection {
    int getroleID();

    String getroleName();

    String getuserName();

    String getbuildingName();

    String getroomName();

    String getname();

    int getroomID();

    int getcapacity();

    boolean getclicker();

    boolean getpowerOutlets();

    boolean gettv();

    boolean getwhiteboard();

    int getreservationID();

    int gettimeslotID();
}

