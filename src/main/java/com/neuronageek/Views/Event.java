package com.neuronageek.Views;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class Event extends VerticalLayout {
    protected TextField name;
    protected DateField start;
    protected DateField end;
    protected Button create_button;
    protected Button cancel_button;

    public Event() {
        Design.read(this);
    }
}
