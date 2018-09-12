import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Client {
    private User user=null;
    private Scanner scanner=new Scanner(System.in);
    private Socket socket = null;
    public static void main(String[] args){
        new Client();
    }
    public Client(){
        try {
            socket = new Socket("127.0.0.1", 8000);
            total();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void total(){
        System.out.print("please enter number from 1 to 4 to choose the service you want:"
                +"\n"+"1 means signIn,2 means signUp,"+"\n"+"3 means open lessons,4 means choose lessons,\n others will be useless");
        String signal=scanner.next();
        if(signal.matches("[0-9]+")){
            if(parseInt(signal)==1){signIn();
            }
            if(parseInt(signal)==2){signUp();}
        }
        if(parseInt(signal)==3){openLesson();
        }
        if(parseInt(signal)==4){chooseLesson();}
    }

    public  void signIn(){
        user=new User();
        CommandTransfer commandTransfer=new CommandTransfer();
        while (true) {
            System.out.print("请输入用户名：");
            user.setName(scanner.next());
            System.out.print("请输入密码：");
            user.setPassword(scanner.next());
            System.out.print("请再次确认密码：");
            String rePassword = scanner.next();
            if(!rePassword.equals(user.getPassword())){
                System.out.println("**********两次输入的密码不一致**********");
                continue;
            }
            commandTransfer.setCmd("signIn");
            commandTransfer.setData(user);
            sendData(commandTransfer);
            commandTransfer=getData();
            System.out.println(commandTransfer.getResult());
            if(commandTransfer.isFlag()){
                break;
            }
        }
        user=null;
        signUp();

    }
    public void signUp(){
        user=new User();
        CommandTransfer commandTransfer=new CommandTransfer();
        while (true) {
            System.out.print("请输入用户名：");
            user.setName(scanner.next());
            System.out.print("请输入密码：");
            user.setPassword(scanner.next());
            commandTransfer.setData(user);
            commandTransfer.setCmd("signUp");
            sendData(commandTransfer);
            commandTransfer=getData();
            System.out.println(commandTransfer.getResult());
            if(commandTransfer.isFlag()){
                break;
            }
        }
        total();
    }
    public void openLesson(){
        CommandTransfer commandTransfer=new CommandTransfer();
        while(true){
            System.out.println("请输入您想开课的课程名称：");
            String name=scanner.next();
            commandTransfer.setData(user);
            commandTransfer.setCmd("openLesson");
            sendData(commandTransfer);
            commandTransfer=getData();
            System.out.println(commandTransfer.getResult());
            if(commandTransfer.isFlag()){
                user.openLesson(name);
                break;
            }
            else {
                break;
            }
        }
        total();
    }
    public void chooseLesson(){
        CommandTransfer commandTransfer=new CommandTransfer();
        while(true){
            String[] lists=User.getLessonlist();
            System.out.println("可选课程列表如下：");
            if(lists==null){
                System.out.println("可选列表为空 不能选课");
                break;
            }
            else{
            for (int i=0;i<lists.length;i++){
                System.out.println("课程序号："+(i+1)+" 课程名称："+lists[i]);
            }
            System.out.println("请输入您想选课的课程序号(输入不存在的课程序号无效)：");
            String name=scanner.next();
            if(name.matches("[0-9]+")){
                user.setLessonNum(name);
                commandTransfer.setData(user);
                commandTransfer.setCmd("chooseLesson");
                sendData(commandTransfer);
                commandTransfer=getData();
                System.out.println(commandTransfer.getResult());
                if(commandTransfer.isFlag()){
                    user.chooseLesson();
                    break;
                }
                else{
                    break;
                }
            }
        }
        }
        total();
    }
    //获取服务器发来的数据
    private CommandTransfer getData() {
        InputStream is;
        ObjectInputStream osi;
        CommandTransfer commandTransfer = null;
        try {
            is = socket.getInputStream();
            osi = new ObjectInputStream(is);
            commandTransfer= (CommandTransfer) osi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return commandTransfer;
    }
    /**
     * 向服务器发送数据
     * @hxx
     */
    private void sendData(CommandTransfer commandTransfer) {
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(commandTransfer);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
