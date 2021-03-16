package com.project.sports_app_backend.view.gui;

import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.service.WorkoutService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.grid.GridSelectionModel;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.selection.MultiSelect;
import com.vaadin.flow.data.selection.MultiSelectionEvent;
import com.vaadin.flow.data.selection.MultiSelectionListener;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vaadin.flow.component.notification.Notification.show;

@Route("couch")
public class CouchView extends VerticalLayout {
    @Autowired
    private WorkoutService workoutService;

    H1 h1 = new H1("Couch panel");

    private final AddWorkout add;
    Grid<WorkoutEntity> grid = new Grid<>(WorkoutEntity.class);
    TextField filterText = new TextField();

    public CouchView(WorkoutService workoutService) {
        this.workoutService = workoutService;
        setSizeFull();
        configureGrid();
        getToolBar();

        add = new AddWorkout(workoutService);

        add(h1, filterText, grid, add);
        updateList();
    }


    private void getToolBar() {
        filterText.setPlaceholder("Filter by workouts name"); //znak wodny na oknie
        filterText.setClearButtonVisible(true); //niewidzialny krzyk do kasowania zawartosci
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }


    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("name", "description", "durationMin", "price1h", "address", "day", "hour");
//        grid.addColumn(user -> {
//            List<UserEntity> userEntity = user.getUser();
//            return user == null ? "-" : userEntity.toString();
//        }).setHeader("User");

        grid.addColumn(delete ->{
            Button buttonDelete = new Button("Delete");
            buttonDelete.addClickListener(e->{
                workoutService.delete();
            });
            return buttonDelete;
        }).setHeader("Delete");

//todo dodoac coach
        grid.getColumns().forEach(col -> col.setAutoWidth(true));


//        grid.setSelectionMode(Grid.SelectionMode.MULTI);
//        GridMultiSelectionModel selectionModel = (GridMultiSelectionModel) grid.getSelectionModel();
//
//        Button delSelected = new Button("Delete", e -> {
//            for(Object itemId: selectionModel.getSelectedItems())
//                grid.setItems(workoutService.delete());
//
//        });
//        delSelected.setEnabled(grid.getSelectedItems().size() > 0);

    }

    private void updateList() {
        grid.setItems(workoutService.getAllWorkout(filterText.getValue()));
    }

}
//todo zrobic tak zeby wyswietlila sie lista woroutow dla danego trenera
