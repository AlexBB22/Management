public class Lecture_Room extends Room {

    public Lecture_Room(int capacity, boolean available){
        super(capacity, available);
        String type = "Lecture Room";
        this.whiteBoard = true;
        this.beamer = true;
        this.television = true;
        this.pc = false;
    }


    public boolean equals(Object other){
        if (other instanceof Lecture_Room && super.equals(other)){
            return true;
        }
        return false;
    }
}
