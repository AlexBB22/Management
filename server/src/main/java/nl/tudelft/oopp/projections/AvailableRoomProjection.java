package nl.tudelft.oopp.projections;

/**
 * This interface is used by Spring to project the selected columns into Java Objects
 * The getters specified here correspond to the selected columns in the SQL query.
 * @author Kanish Dwivedi
 */

public interface AvailableRoomProjection {
    int getroomID();

    String getroomName();

    String getname();

    int getcapacity();

    String getbuildingName();

    boolean getclicker();

    boolean getpowerOutlets();

    boolean gettv();

    boolean getwhiteboard();

}
