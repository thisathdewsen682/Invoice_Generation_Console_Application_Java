public class Product {

    private String productName;

    private String description;

    private float purchasePrice;
    private float sellingPrice;
    private int quantity;

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean addProduct(){

        return false;

    }

    public boolean updateProductById(){
        return false;
    }

    public boolean deleteProductById(){
        return false;
    }

    public static void searchProduct(){

    }




}
