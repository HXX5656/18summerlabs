import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mys {
private static Connection connection;
        public static void main(String[] args){
            try{
                String url="jdbc:mysql://localhost:3306/lab?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=GMT%2B8";
                String user="root";
                String pass="";
                String driver="com.mysql.cj.jdbc.Driver";
                Class.forName(driver);
               connection=DriverManager.getConnection(url,user,pass);
                Scanner scanner=new Scanner(System.in);
                System.out.println("welcome,please enter your operator,while 1 means add,2 means delete,3 means change,4 means show:");
                String operator=scanner.next();
                String sql="";
                if(operator.equals("1")){
                    sql=add(scanner);
                }else if(operator.equals("2")){
                    sql=delete(scanner);
                }else if(operator.equals("3")){
                    sql=change(scanner);
                }else if(operator.equals("4")){
                     show(scanner);
                }
                else {
                    System.exit(0);
                }
                Statement statement = connection.createStatement();
                //执行sql语句并返回一个ResultSet对象
                statement.execute(sql);
                 ResultSet resultSet=connection.createStatement().executeQuery("SELECT * FROM students");
                //从ResultSet对象中获得查询结果
                getresult(resultSet);
                //关闭连接并释放与连接有关的资源
                connection.close();

        }
        catch (Exception e){
            e.printStackTrace();}
        }
        public static void getresult(ResultSet resultSet) throws Exception{
            System.out.println("操作成功，新表如下：");
            while(resultSet.next()){

                System.out.println(resultSet.getString(1) + "  " +
                        resultSet.getString(2)+ "  " + resultSet.getString(3)+
                        "  " + resultSet.getString(4)+ "  " + resultSet.getString(5));}
        }
        public static String add(Scanner scanner){
            System.out.println("your name:");
            String name=scanner.next();
            String sex="";
            String date="";
            while (true) {
                System.out.println("your sex(male或female）");
                sex = scanner.next();
                if(sex.equals("male")||sex.equals("female")){
                    break;
                }
            }
            while (true){
                System.out.println("your birth(YYYY-MM-DD)");
            date=scanner.next();
                String regEx= "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?((0[1-9])|([1-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?((0[1-9])|([1-2][0-9])|(30)))|(02[\\-\\/\\s]?((0[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?((0[1-9])|([1-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?((0[1-9])|([1-2][0-9])|(30)))|(02[\\-\\/\\s]?((0[1-9])|(1[0-9])|(2[0-8]))))))";   //正则表达式
            Pattern pat = Pattern.compile(regEx);
            Matcher mat = pat.matcher(date);
            boolean rs = mat.matches();
            if(rs == true){
                break;
                }

            }
            System.out.println("your birth location:");
            String location=scanner.next();
            String sql="insert into students (name,gende,birth,location) values ('"+name+"','"+sex+"','"+date+"','"+location+"')";
            return sql;
        }
       public static String delete(Scanner scanner){
            while (true) {
                System.out.println("your id：");
                String id = scanner.next();
                boolean result = id.matches("[0-9]+");
                if (result) {
                    String sql = "DELETE FROM students where ID=" + Integer.parseInt(id);
                    return sql;
                }
            }
      }
      public static String change(Scanner scanner){
          while (true) {
              System.out.println("your id：");
              String id = scanner.next();
              boolean result = id.matches("[0-9]+");
              if (result) {
                  System.out.println("your name:");
                  String name=scanner.next();
                  String sex="";
                  String date="";
                  while (true) {
                      System.out.println("your sex(male或female）");
                      sex = scanner.next();
                      if(sex.equals("male")||sex.equals("female")){
                          break;
                      }
                  }
                  while (true){
                      System.out.println("your birth(YYYY-MM-DD)");
                      date=scanner.next();
                      String regEx= "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?((0[1-9])|([1-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?((0[1-9])|([1-2][0-9])|(30)))|(02[\\-\\/\\s]?((0[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?((0[1-9])|([1-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?((0[1-9])|([1-2][0-9])|(30)))|(02[\\-\\/\\s]?((0[1-9])|(1[0-9])|(2[0-8]))))))";   //正则表达式
                      Pattern pat = Pattern.compile(regEx);
                      Matcher mat = pat.matcher(date);
                      boolean rs = mat.matches();
                      if(rs == true){
                          break;
                      }

                  }
                  System.out.println("your birth location:");
                  String location=scanner.next();
                  String sql="UPDATE students SET name='"+name+"',gende='"+sex+"',birth='"+date+"',location='"+location+"' WHERE id="+Integer.parseInt(id);
                  return sql;
              }
          }
      }
      public static void  show(Scanner scanner) throws Exception{
          while (true) {
              System.out.println("your id：");
              String id = scanner.next();
              boolean result = id.matches("[0-9]+");
              if (result) {
                  String sql = "SELECT * FROM students where ID=" + Integer.parseInt(id);
                  ResultSet resultSet=connection.createStatement().executeQuery(sql);
                  getresult(resultSet);
              }
          }
       }
}
