package home.hoover;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        // Configure the main layout
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER); // Centers vertically

        // Create a header with a primary color accent
        H1 header = new H1("Hoover Todo");
        header.getStyle().set("color", "var(--lumo-primary-color)");

        // Create the todo list container with a card-like appearance
        VerticalLayout todosList = new VerticalLayout();
        todosList.setWidth("400px");
        todosList.setPadding(true);
        todosList.setSpacing(true);
        todosList.getStyle().set("border", "1px solid var(--lumo-contrast-20pct)");
        todosList.getStyle().set("border-radius", "8px");

        // Create input field with placeholder text
        TextField taskField = new TextField();
        taskField.setPlaceholder("Enter a new task");
        taskField.setWidth("300px");

        // Create an add button with a click listener and ENTER shortcut
        Button addButton = new Button("Add", click -> {
            String task = taskField.getValue().trim();
            if (!task.isEmpty()) {
                Checkbox checkbox = new Checkbox(task);
                todosList.add(checkbox);
                taskField.clear();
            }
        });
        addButton.addClickShortcut(Key.ENTER);

        // Arrange the input field and button horizontally
        HorizontalLayout inputLayout = new HorizontalLayout(taskField, addButton);
        inputLayout.setSpacing(true);
        inputLayout.setDefaultVerticalComponentAlignment(Alignment.END);

        // Add all components to the main layout
        add(header, todosList, inputLayout);
    }
}
