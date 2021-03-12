package com.project.sports_app_backend.view.gui;

import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.SportRepository;
import com.project.sports_app_backend.service.SportService;
import com.project.sports_app_backend.service.WorkoutService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("user")
public class UserView extends VerticalLayout {

    private SportService sportService;

    private SportRepository sportRepository;

    private WorkoutService workoutService;

//    @Autowired
//    private WorkoutRepository workoutRepository;

    Grid<WorkoutEntity>grid = new Grid<>(WorkoutEntity.class);
    TextField filterText = new TextField();

    public UserView(WorkoutService workoutService,SportService sportService) {
        this.workoutService = workoutService;
        this.sportService = sportService;

        ComboBox<SportEntity> comboBoxSport = new ComboBox<>("Choose sport");
//        comboBoxSport.setItems(sportService.getSportByName(SportEntity.class.getName()));
        //todo
        comboBoxSport.setItems(sportService.getAllSports());
        add(comboBoxSport);

        setSizeFull();
        configureGrid();
        configureFilter();

        add(filterText, grid);
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
        grid.removeColumnByKey("sport");
        grid.setColumns("name", "description", "durationMin", "price1h", "address");
        grid.addColumn(sport -> {
            String sportEntity = sport.getName();
            return sportEntity == null ? "-" : sportEntity;
        }).setHeader("Sport");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        grid.setItems(workoutService.getAllWorkout(filterText.getValue()));
    }
}
//todo popracowac nad comboboxem