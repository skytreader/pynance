package net.skytreader.pynance;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/setup")
public class SetupView extends VerticalLayout {

    public SetupView(){
        TextField monthlyBudget = new TextField("Monthly Budget");
        monthlyBudget.setRequired(true);
        add(monthlyBudget);
    }
}
