package nl.tudelft.oopp.communication;

public class Food {
    private String foodId;
    private int price;

    public Food(){}

    public Food(String foodId, int price) {
        this.foodId = foodId;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    /**.
     * set price of food if given price below 0 then price will be 0
     * @param price an integer indicating the price to be set
     * @Author Scott Jochems
     */
    public void setPrice(int price) {
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
}
