public class Study_Room extends Room {

    public Study_Room(int capacity, boolean available){
        super(capacity, available);
        String type = "Study Room";
        this.whiteBoard = true;
        this.beamer = false;
        this.television = true;
        this.pc = false;
    }

    public boolean equals(Object other){
        if (other instanceof Study_Room && super.equals(other)){
            return true;
        }
        return false;
    }

}
