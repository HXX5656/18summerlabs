public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private double balance;
    public Customer(){
        this.id="unknown";
        this.firstName="unknown";
        this.lastName="unknown";
        this.balance=0;
    }
    public Customer(String id,String firstName,String lastName,double balance){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.balance=balance;
    }
}
