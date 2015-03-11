package com.web.jsf.beans;

import com.project.entities.User;
import com.project.services.UserService;
import java.io.Serializable;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Home
 */
@Named(value = "userManagedBean")
@ViewScoped
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private UserService userService;

    private User user = new User();

    public UserManagedBean() {

    }

    @PostConstruct
    public void init() {
        user = new User();
    }

    public List<User> getUserList() {
        return userService.getUserList(0, 1000);
    }

    public String saveUser() {
        User u = userService.getUserByEmail(user.getEmail());
        if (u != null) {
            System.out.println("User is not null, return  ");
            FacesMessage msg = new FacesMessage(getBundle().getString("emailbusy"), getBundle().getString("emailbusy"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        } else {
            userService.saveUser(user);
            return "index";
        }
    }

    public PropertyResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{i18n}", PropertyResourceBundle.class);
    }

    public String updateUser() {
        if (user != null) {
            userService.updateUser(user);
        }
        return "index?faces-redirect=true";
    }

    public String deleteUser(Long id) {
        if (id != null) {
            userService.deleteUser(id);
        }
        return "index";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
