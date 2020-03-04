package nl.tudelft.oopp.communication;

public class Type {
    private int typeId;
    private String name;
    private boolean whiteBoard;
    private boolean tv;
    private boolean powerOutlets;
    private boolean clicker;

    public Type() {
    }

    /**.
     * constructor for type class
     * @param name name of the room type
     * @param whiteBoard availability of whiteboard
     * @param powerOutlets availability of poweroutlets
     * @param clicker availability of clicker
     * @Author Eli
     */
    public Type(String name, boolean whiteBoard, boolean powerOutlets, boolean clicker) {
        this.name = name;
        this.whiteBoard = whiteBoard;
        this.powerOutlets = powerOutlets;
        this.clicker = clicker;
    }

    public int getType_id() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public boolean isWhiteBoard() {
        return whiteBoard;
    }

    public boolean isTv() {
        return tv;
    }

    public boolean isPowerOutlets() {
        return powerOutlets;
    }

    public boolean isClicker() {
        return clicker;
    }

    public void setType_id(int typeId) {
        this.typeId = typeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWhiteBoard(boolean whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public void setPowerOutlets(boolean powerOutlets) {
        this.powerOutlets = powerOutlets;
    }

    public void setClicker(boolean clicker) {
        this.clicker = clicker;
    }
}