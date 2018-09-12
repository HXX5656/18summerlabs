import java.io.Serializable;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class User implements Serializable{
    private String name="";
    private String password="";
    private String[] myLessons=new String[0];//开课
    private String[] chooseLessons=new String[0];//选课
    private static String[] lessonlist;
    private String lessonNum="";
    public String getName(){
        return name;
    }

    public String getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(String lessonNum) {
        this.lessonNum = lessonNum;
    }

    public void setName(String n){
        name=n;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String n){
        password=n;
    }
    public void openLesson(String n){
        String[] tempt=new String[myLessons.length+1];
        for(int i=0;i<myLessons.length;i++) {
            tempt[i]=myLessons[i];
        }
        tempt[myLessons.length]=n;
        myLessons=tempt;
        open(n);
    }
    private void open(String n){
        if(lessonlist==null){
            String[] tempt=new String[1];
            tempt[0]=n;
            lessonlist=tempt;
        }
        else {
        String[] tempt=new String[lessonlist.length+1];
        for(int i=0;i<lessonlist.length;i++) {
            tempt[i]=lessonlist[i];
        }
        tempt[lessonlist.length]=n;
        lessonlist=tempt;
    }
    }
    public void getMyLessons(){
        System.out.println("我所开的课如下：");
        for (int i=0;i<myLessons.length;i++){
            System.out.print(" "+myLessons[i]);
        }
    }
    //获取所开的课
    public String[] getLessons(){
        return myLessons;
    }
    public String[] getChooseLessons(){
        return chooseLessons;
    }

    public static String[] getLessonlist() {
        return lessonlist;
    }
    public void chooseLesson(){
        if(parseInt(lessonNum)-1>=0&&parseInt(lessonNum)-1<lessonlist.length) {
            String n = lessonlist[parseInt(lessonNum) - 1];
            String[] tempt = new String[chooseLessons.length + 1];
            for (int i = 0; i < chooseLessons.length; i++) {
                tempt[i] = chooseLessons[i];
            }
            tempt[chooseLessons.length] = n;
            chooseLessons = tempt;
            getchooseLesson();
        }
        else {
            System.out.println("选课失败！");
        }
    }
    public void getchooseLesson(){
        System.out.println("我所选的课如下：");
        for (int i = 0; i <chooseLessons.length ; i++) {
            System.out.println("第"+i+"门-课程名称："+chooseLessons[i]);
        }
    }
}
