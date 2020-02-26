public class Room {

    public String type;
    public int capacity;
    public boolean available;
    public boolean whiteBoard;
    public boolean beamer;
    public boolean television;
    public boolean pc;

    public Room(int capacity, boolean available){
        this.capacity = capacity;
        this.available = available;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public boolean hasTelevision(){
        return this.television;
    }

    public boolean hasWhiteBoard(){
        return true;
    }

    public boolean isAvailable(){
        return this.available;
    }

    public boolean hasPC(){
        return this.pc;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean equals(Object other) {
        if (other instanceof Room) {
            Room that = (Room) other;
            if ((this.type.equals(that.type) && this.capacity == that.capacity && this.available == that.available &&
                                this.beamer == that.beamer && this.pc == this.television && this.beamer) == (that.beamer && this.whiteBoard == that.whiteBoard)) {
                return true;
            }
        }
        return false;
    }
}
