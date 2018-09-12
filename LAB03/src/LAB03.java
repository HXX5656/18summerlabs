import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class LAB03 extends Application {
    public static final String defaultURL="https://www.baidu.com";
    @Override
    public void start(Stage primaryStage) {
        init(defaultURL);
    }
    //每一次地址的变换 构建窗口的方法
    public void init(String url){
        if(url.trim().length()==0){
            url=defaultURL;
        }
        final Stage stage=new Stage();
        Group group=new Group();//作为根节点，也就是root
        stage.setScene(new Scene(group));

        WebView webView=new WebView();
        final WebEngine engine=  webView.getEngine();
        engine.load(url);

        final TextField textField=new TextField(url);
        /**修改输入栏的地址，也就是访问那个网站，这个地址栏显示那个网站的地址
         * locationProperty()是获得当前页面的url封装好的ReadOnlyStringProperty对象
         */
        engine.locationProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textField.setText(newValue);
            }
        });
        /**
         * 设置标题栏为当前访问页面的标题。
         */
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>(){
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if(newValue==Worker.State.SUCCEEDED){
                    stage.setTitle(engine.getTitle());
                }
            }
        });


        //加载新的地址 实现多线程：每一次地址栏加载新的地址都相当于重新开一个窗口 重建
        EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                init(textField.getText().trim());
            }
        };

        textField.setOnAction(handler);

        Button okButton=new Button("go");
        okButton.setDefaultButton(true);
        okButton.setOnAction(handler);

        HBox hbox=new HBox();
        hbox.getChildren().addAll(textField,okButton);
        HBox.setHgrow(textField, Priority.ALWAYS);

        VBox vBox=new VBox();
        vBox.getChildren().addAll(hbox,webView);
        VBox.setVgrow(webView, Priority.ALWAYS);

        group.getChildren().add(vBox);
        stage.show();
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }
}
