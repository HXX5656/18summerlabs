import java.util.HashMap;

public class Server {
    private static HashMap<String,String> users=new HashMap<String, String>();
    public static void addUsers(String name,String email){
           users.put(name,email);
    }
    public static HashMap getUsers(){
        return users;
    }
}
