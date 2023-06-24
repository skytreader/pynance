package net.skytreader.pynance;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

@Route("/setup")
public class SetupView extends VerticalLayout {

    public SetupView(){
        add(new Header(new H1("Pynance Set-up")));
        NumberField monthlyBudget = new NumberField("Monthly Budget");
        monthlyBudget.setMin(0);
        add(monthlyBudget);
    }
}
