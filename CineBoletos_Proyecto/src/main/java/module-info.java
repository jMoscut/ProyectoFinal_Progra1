module com.mycompany.cineboletos_proyecto {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    exports controllers.homeController;
    exports Conexion;
    exports controllers.AuthController;
    exports models;
    exports controllers.EventsController;
    exports controllers.ResponsibleController;
    exports controllers.SeatController;
    exports controllers.UsersController;
    exports com.mycompany.cineboletos_proyecto;    
}
