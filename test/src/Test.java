import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String x="11\n22";
        System.out.println(x.split("\\\\n").length);
        System.out.println(x.split("\\n").length);
        System.out.println("enter");
        String  s=scanner.next();
        System.out.println(s.split("\\\\n").length);
        System.out.println(s.split("\\n").length);
        //读的时候会首先一步转义\n
    }
}
