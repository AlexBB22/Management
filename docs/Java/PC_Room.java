public class PC_Room extends Room {

    public PC_Room(int capacity, boolean available) {
        super(capacity, available);
        String type = "PC Room";
        this.whiteBoard = true;
        this.beamer = false;
        this.television = false;
        this.pc = true;
    }

    public boolean equals(Object other){
        if (other instanceof PC_Room && super.equals(other)){
            return true;
        }
        return false;
    }

}
