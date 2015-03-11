package com.web.jsf.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 * Current Revision: $Rev: $ Last Modified: $LastChangedDate: 2014-08-29
 * 22:13:08 +0400 (Fri, 29 Aug 2014) $ Last Modified By: $LastChangedBy:
 * ALGOL\aarzumanyan $
 */
public class MessageFactory {

    private ResourceBundle bundle;
    private Locale locale;

    public MessageFactory() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle = ResourceBundle.getBundle("i18n", locale);
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }

    public String getMessage(String key, Object arg1) {
        return getMessage(key, new Object[]{arg1});
    }

    public String getMessage(String key, Object arg1, Object arg2) {
        return getMessage(key, new Object[]{arg1, arg2});
    }

    public String getMessage(String key, Object[] args) {
        if (args == null || args.length == 0) {
            return bundle.getString(key);
        }
        MessageFormat fmt = new MessageFormat(bundle.getString(key));
        fmt.setLocale(locale);
        return fmt.format(args).toString();
    }
}
