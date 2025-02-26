module programacao.s_a {
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires static lombok;
    requires jbcrypt;
    requires org.hibernate.orm.core;
    requires mysql.connector.java;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.web;
    requires java.desktop;

    opens programacao.s_a.models.entities to org.hibernate.orm.core;

    exports programacao.s_a.views;
    opens programacao.s_a.views to javafx.fxml;
    exports programacao.s_a.controllers;
    opens programacao.s_a.controllers to javafx.fxml;
    exports programacao.s_a;
    opens programacao.s_a to javafx.fxml;
    exports programacao.s_a.controllers.admin;
    opens programacao.s_a.controllers.admin to javafx.fxml;
    exports programacao.s_a.controllers.client to javafx.fxml;
    opens programacao.s_a.controllers.client to javafx.fxml;
    exports programacao.s_a.controllers.general to javafx.fxml;
    opens programacao.s_a.controllers.general to javafx.fxml;
    exports programacao.s_a.models.service;
    exports programacao.s_a.models.entities;
    exports programacao.s_a.models.dao;
    exports programacao.s_a.models.utils;
    exports programacao.s_a.models.exceptions;
}