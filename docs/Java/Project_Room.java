public class Project_Room extends Room {

    public Project_Room(int capacity, boolean available){
        super(capacity, available);
        this.type = "Project Room";
        this.whiteBoard = true;
        this.beamer = false;
        this.television = true;
        this.pc = false;
    }

    public boolean equals(Object other){
        if (other instanceof Project_Room && super.equals(other)){
            return true;
        }
        return false;
    }

}
