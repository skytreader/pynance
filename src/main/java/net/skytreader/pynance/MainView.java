package net.skytreader.pynance;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import net.skytreader.pynance.service.StartUpService;

/**
 * The main view contains a button and a click listener.
 */
@Route
public class MainView extends VerticalLayout implements BeforeEnterObserver {
    private StartUpService startUpService;

    public MainView(StartUpService startUpService) {
        this.startUpService = startUpService;
        Button button = new Button("Click me",
                event -> Notification.show("Clicked!"));
        add(button);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!this.startUpService.isInstallationComplete()) {
            beforeEnterEvent.rerouteTo(SetupView.class);
        }
    }
}
