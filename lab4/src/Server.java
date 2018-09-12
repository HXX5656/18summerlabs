import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    private User[] users=null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    public static void main(String[] args){
        new Server();
    }
    public Server(){
        try {
            ServerSocket serverSocket=new ServerSocket(8000);
            Socket socket=serverSocket.accept();
            System.out.println("The server started at"+new Date());
            try {
               while (true){
                   InputStream is = socket.getInputStream();
                   OutputStream os = socket.getOutputStream();
                   ois = new ObjectInputStream(is);
                   oos = new ObjectOutputStream(os);
                   CommandTransfer commandTransfer = (CommandTransfer) ois.readObject();
                   commandTransfer = exe(commandTransfer);//执行客户端指令操作
                   oos.writeObject(commandTransfer);
               }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private CommandTransfer exe(CommandTransfer commandTransfer){
        String cmd=commandTransfer.getCmd();
        if(cmd.equals("signIn")){
            User user=(User)commandTransfer.getData();
            boolean flag=addUser(user);
            commandTransfer.setFlag(flag);
            if(flag){
                commandTransfer.setResult("注册成功，请登录！");
            }
            else {
                commandTransfer.setResult("注册失败");
            }
        }
        if(cmd.equals("signUp")){
            User user = (User) commandTransfer.getData();
            boolean flag = loginUser(user);
            commandTransfer.setFlag(flag);
            if(flag){
                commandTransfer.setResult("登陆成功！");
            }else{
                commandTransfer.setResult("登录失败");
            }

        }
        if(cmd.equals("openLesson")){
            User user=(User)commandTransfer.getData();
            boolean flag=openLesson(user);
            commandTransfer.setFlag(flag);
            if(flag){
                commandTransfer.setResult("开课成功！");
            }
            else {
                commandTransfer.setResult("开课失败！您可能未登陆");
            }
        }
        if(cmd.equals("chooseLesson")){
            User user=(User)commandTransfer.getData();
            boolean flag=chooseLesson(user);
            commandTransfer.setFlag(flag);
            if(flag){
                commandTransfer.setResult("选课成功！");
            }
            else {
                commandTransfer.setResult("选课失败！您可能未登陆");
            }
        }
        return commandTransfer;
    }
    private boolean chooseLesson(User user){
        boolean b=true;
        if(user==null){
            b=false;
        }
        else{
            b=true;
        }
        return b;
    }
    private boolean openLesson(User user){
        boolean b=true;
        if(user==null){
            b=false;
        }
        else{
            b=true;
        }
        return b;
    }
    private boolean addUser(User user){
        boolean b=true;
        if(users==null){
            users=new User[1];
            users[0]=user;
            b=true;
        }
        else{
            int len=users.length;
            User[] tempt=new User[len+1];
            b=true;
            for (int i = 0; i <len ; i++) {
                tempt[i]=users[i];
                if(user.getName().equals(users[i].getName())){
                    b=false;
                }
            }
            if(b==true){
                tempt[len]=user;
                users=tempt;
            }

        }
        return b;
    }
    private boolean loginUser(User user){
        boolean b=false;
        if(users!=null){
            for (int i = 0; i <users.length ; i++) {
            if(user.getName().equals(users[i].getName())&&user.getPassword().equals(users[i].getPassword())){
                b=true;
                break;
            }
        }}
        return b;
    }
}
