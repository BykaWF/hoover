package home.hoover.post;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Route("posts")
public class PostsView extends Div {

    public PostsView(PostService postService) {
        setHeightFull();

        // Create a container with vertical layout to center content
        VerticalLayout container = new VerticalLayout();
        container.setWidthFull();
        container.setPadding(true);
        container.setSpacing(true);
        container.setAlignItems(FlexComponent.Alignment.CENTER);

        // Add a styled header
        H1 header = new H1("Posts");
        header.getStyle().set("color", "var(--lumo-primary-color)");
        header.getStyle().set("margin-top", "20px");

        // Create a grid with custom columns and styling
        Grid<Post> grid = new Grid<>(Post.class, false);
        grid.addColumn(Post::title)
                .setHeader("Title")
                .setAutoWidth(true);
        grid.addColumn(Post::summary)
                .setHeader("Summary")
                .setFlexGrow(1);
        // Render URL as a clickable link
        grid.addColumn(new ComponentRenderer<>(post -> {
                    Anchor link = new Anchor(post.url(), "Visit");
                    link.setTarget("_blank");
                    return link;
                }))
                .setHeader("URL")
                .setAutoWidth(true);
        // Format and display the date published
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        grid.addColumn(post -> post.datePublished().format(dateFormatter))
                .setHeader("Date Published")
                .setAutoWidth(true);
        grid.addColumn(post -> post.author().name())
                .setHeader("Author")
                .setAutoWidth(true);

        // Apply additional themes to improve grid appearance
        grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES);
        grid.setHeight("70vh");
        grid.setWidth("90%");

        // Fetch and set grid items
        List<Post> posts = postService.findAll();
        grid.setItems(posts);

        // Add components to the container
        container.add(header, grid);
        add(container);
    }
}
