module org.example.rpsdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens org.example.rpsdemo to javafx.fxml;
    exports org.example.rpsdemo;
}