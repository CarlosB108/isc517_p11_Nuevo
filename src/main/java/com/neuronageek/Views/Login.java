package com.neuronageek.Views;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.declarative.Design;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p/>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p/>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class Login extends FormLayout {

    protected TextField user_field;
    protected TextField password_field;
    protected Button login_button;

    public Login() {
        Design.read(this);
    }
}
