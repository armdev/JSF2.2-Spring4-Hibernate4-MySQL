package com.web.jsf.beans;

import com.project.entities.User;
import com.project.services.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Home
 */
@Named(value = "userViewBean")
@ViewScoped
public class UserViewBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private UserService userService;
    private User user;
    @ManagedProperty("#{param.userId}")
    private Long userId;
    private FacesContext context = null;
    private ExternalContext externalContext = null;

    public UserViewBean() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
       // System.out.println("User view init ");
    }

    @PostConstruct
    public void init() {
        user = new User();
      
        //userId = ParamUtil.longValue((this.getRequestParameter("userId")));
        //System.out.println("User id " + userId);
        user = userService.getUserById(userId);
//        if (user != null) {
//            System.out.println("Email " + user.getEmail());
//        }

    }

    public String updateUser() {
        if(user == null || user.getId() == null){            
              return "index?faces-redirect=true";
        }else {
            userService.updateUser(user);
        }
        
        return "index?faces-redirect=true";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String getRequestParameter(String paramName) {
        String returnValue = null;
        if (externalContext.getRequestParameterMap().containsKey(paramName)) {
            returnValue = (externalContext.getRequestParameterMap().get(paramName));
        }
        return returnValue;
    }

}
