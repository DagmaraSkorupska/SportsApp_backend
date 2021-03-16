package com.project.sports_app_backend.view.gui;

import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.service.WorkoutService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;


public class AddWorkout extends FormLayout {

    @Autowired
    private WorkoutService workoutService;

    H2 h2 = new H2("Add Workout");
    TextField textFieldName = new TextField("Name workout");
    IntegerField integerFieldDuration = new IntegerField("Duration Minute", "Enter minutes");
    NumberField numberFieldPrice = new NumberField("Price 1h", "Enter amount");
    TextField textFieldAddress = new TextField("Address");
    TextField textFieldDescription = new TextField("Description");

    Button buttonSave = new Button("Save");
    Button buttonDelete = new Button("Delete");//???
    Button buttonClose = new Button("Close");


    public AddWorkout(WorkoutService workoutService) {
        this.workoutService = workoutService;

        setSizeFull();

        buttonSave.addClickListener(click -> {
            WorkoutEntity newWorkout = new WorkoutEntity();
            newWorkout.setName(textFieldName.getValue());
            newWorkout.setDurationMin(integerFieldDuration.getValue());
            newWorkout.setPrice1h(numberFieldPrice.getValue());
            newWorkout.setAddress(textFieldAddress.getValue());
            newWorkout.setDescription(textFieldDescription.getValue());
            workoutService.saveWorkout(newWorkout);

            Notification notification = new Notification("Workout save!", 3000);
            notification.open();
        });

        add(h2);
        add(textFieldName,
                integerFieldDuration,
                numberFieldPrice,
                textFieldAddress,
                textFieldDescription
        );
        add(createButtonsLayout());
    }

    private Component createButtonsLayout() {
        buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonClose.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonSave.addClickShortcut(Key.ENTER);
        buttonSave.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(buttonSave, buttonClose);
    }
}
