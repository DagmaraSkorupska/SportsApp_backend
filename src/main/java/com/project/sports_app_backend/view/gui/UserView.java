package com.project.sports_app_backend.view.gui;

import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.SportRepository;
import com.project.sports_app_backend.service.SportService;
import com.project.sports_app_backend.service.WorkoutService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;




@Route("user")
public class UserView extends VerticalLayout {

    private SportService sportService;

    private SportRepository sportRepository;

    private WorkoutService workoutService;

//    @Autowired
//    private WorkoutRepository workoutRepository;

    private Grid<WorkoutEntity>grid = new Grid<>(WorkoutEntity.class);

    public UserView(WorkoutService workoutService,SportService sportService) {
        this.workoutService = workoutService;
        this.sportService = sportService;

        ComboBox<SportEntity> comboBoxSport = new ComboBox<>("Choose sport");
//        comboBoxSport.setItems(sportService.getSportByName(SportEntity.class.getName()));
        comboBoxSport.setItems(sportService.getSportByName(getClassName()));
        add(comboBoxSport);

        setSizeFull();
        configureGrid();

        add(grid);
        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("name", "description", "durationMin", "price1h", "address", "sport");
    }

    private void updateList() {
        grid.setItems(workoutService.getAllWorkout());
    }
}
//todo popracowac nad comboboxem