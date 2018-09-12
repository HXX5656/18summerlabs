import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class LAB02 {
    public  static HashMap<String,String> hashMap=new HashMap<String, String>();
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请你注册或登陆，输入0为注册，输入其他为登陆");
        String signal=scanner.nextLine();
        if(signal.matches("[0-9]+")&&parseInt(signal)==0){
            signIn(scanner);
        }
        else{
           signUp(scanner);
        }
    }
    //注册
    public static void signIn(Scanner scanner){
        boolean state=true;
        System.out.println("请您输入您想注册的用户名：");
        String user1=scanner.nextLine();
        if(hashMap.containsKey(user1)){
            state=false;
        }
        else {
            state=true;
            System.out.println("请您输入您想注册的用户名对应的密码：");
            String password1=scanner.nextLine();
            System.out.println("恭喜您注册成功,赶紧来登陆吧！");
            hashMap.put(user1,password1);
            signUp(scanner);
        }
        if(!state){
                System.out.println("注册失败，用户名已存在。请您重新注册：");
                signIn(scanner);
        }

    }
    //登陆
    public static void signUp(Scanner scanner){
        System.out.println("请您输入您想登陆的用户名：");
        String user2=scanner.nextLine();
        if(hashMap.containsKey(user2)){
           check(scanner,user2);
        }
        else{
            System.out.println("您输入的用户名不存在，请您重新输入：");
            signUp(scanner);
        }


    }
    public static void check(Scanner scanner,String user2){
        System.out.println("请您输入密码：");
        String password2=scanner.nextLine();
        if(password2.equals(hashMap.get(user2))){
            System.out.println("您已成功登陆");
            System.exit(0);
        }
        else{
            System.out.println("密码出错，请您重新输入:");
            check(scanner,user2);
        }
    }
}
