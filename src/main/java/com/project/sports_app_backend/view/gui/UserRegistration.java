package com.project.sports_app_backend.view.gui;

import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.UserType;
import com.project.sports_app_backend.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("registration")
public class UserRegistration extends FormLayout {

    H1 h1 = new H1("User Registration");

    TextField textFieldFirstName = new TextField("First name");
    TextField textFieldLastName = new TextField("Last name");
    TextField textFieldEmail = new TextField("Email");
    TextField textFieldPassword = new TextField("Password");
    TextField textFieldPhone = new TextField("Phone");
    ComboBox<UserType> comboBoxUserType = new ComboBox<>("User Type");

    Button buttonSave = new Button("Save");
    Button buttonClose = new Button("Close");

    @Autowired
    public UserRegistration(UserService userService) {

        setSizeFull();

        comboBoxUserType.setItems(UserType.USER, UserType.COUCH);

        buttonSave.addClickListener(click -> {
            UserEntity newUser = new UserEntity();
            newUser.setFirstName(textFieldFirstName.getValue());
            newUser.setLastName(textFieldLastName.getValue());
            newUser.setEmail(textFieldEmail.getValue());
            newUser.setPassword(textFieldPassword.getValue());
            newUser.setPhone(textFieldPhone.getValue());
            newUser.setType(comboBoxUserType.getValue());
            userService.saveUser(newUser);

            Notification notification = new Notification("User save!", 3000);
            notification.open();
        });

        add(h1);
        add(textFieldFirstName,
                textFieldLastName,
                textFieldEmail,
                textFieldPassword,
                textFieldPhone,
                comboBoxUserType,
                createButtonsLayout()
        );
    }

    private Component createButtonsLayout() {
        buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonClose.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonSave.addClickShortcut(Key.ENTER);
        buttonSave.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(buttonSave, buttonClose);
    }
}
