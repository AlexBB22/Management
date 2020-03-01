package nl.tudelft.oopp.communication;

public class Type {
    private int type_id;
    private String name;
    private boolean whiteBoard;
    private boolean tv;
    private boolean powerOutlets;
    private boolean clicker;

    public int getType_id() {
        return type_id;
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

    public void setType_id(int type_id) {
        this.type_id = type_id;
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