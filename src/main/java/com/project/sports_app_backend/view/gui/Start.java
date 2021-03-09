package com.project.sports_app_backend.view.gui;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("hello")
public class Start extends VerticalLayout {

    public Start() {
        TextArea textArea = new TextArea("hej");

        add(textArea);
    }
}
