package com.project.sports_app_backend.view.gui;

import com.project.sports_app_backend.domain.Reservation;
import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.service.ReservationService;
import com.project.sports_app_backend.service.WorkoutService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("user")
public class UserView extends VerticalLayout {

    private WorkoutService workoutService;
    private WorkoutEntity workoutEntity;
    private ReservationService reservationService;

//    @Autowired
//    private WorkoutRepository workoutRepository;

    Grid<WorkoutEntity>grid = new Grid<>(WorkoutEntity.class);
    TextField filterText = new TextField();
    H1 h1 = new H1("User");

    public UserView(WorkoutService workoutService, WorkoutEntity workoutEntity, ReservationService reservationService) {
        this.workoutService = workoutService;
        this.workoutEntity = workoutEntity;
        this.reservationService = reservationService;

        setSizeFull();
        configureGrid();
        configureFilter();

        add(h1, filterText, grid);
        updateList();
    }


    private void configureFilter() {
        filterText.setPlaceholder("Filter by workouts name"); //znak wodny na oknie
        filterText.setClearButtonVisible(true); //niewidzialny krzyk do kasowania zawartosci
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.removeColumnByKey("user");
        grid.setColumns("name", "description", "durationMin", "price1h", "address", "day", "hour");
        grid.addColumn(user -> {
            return user.getUser().getFirstName()+ " " + user.getUser().getLastName();
        }).setHeader("Couch");

        grid.addColumn(save -> {
            Button buttonSave = new Button("Reservation workout");
            return buttonSave.addClickListener(click -> {
                Reservation newReservation = new Reservation();
//                newReservation.setToPay(workoutEntity.getPrice1h() * workoutEntity.getDurationMin());
//                newReservation.setDay(workoutService.getDay(workoutEntity.getDay()));
                newReservation.setHour(workoutService.getHour(workoutEntity.getHour()));
                reservationService.saveReservation(newReservation);
//todo cos z tym zrobic, odpowiedani implementacja
                Notification notification = new Notification("Reservation save!", 3000);
                notification.open();
            });
        }).setHeader("Couch");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        grid.setItems(workoutService.getAllWorkout(filterText.getValue()));
    }
}

//todo maja sie wyswietlac workouty tylko dla danego usera