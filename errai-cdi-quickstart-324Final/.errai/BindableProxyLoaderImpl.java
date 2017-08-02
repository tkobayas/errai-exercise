package org.jboss.errai.databinding.client;

import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.Locale;

public class BindableProxyLoaderImpl implements BindableProxyLoader { public void loadBindableProxies() {
    class org_jboss_errai_ui_shared_api_LocaleProxy extends Locale implements BindableProxy {
      private BindableProxyAgent<Locale> agent;
      public org_jboss_errai_ui_shared_api_LocaleProxy(InitialState initialState) {
        this(new Locale(), initialState);
      }

      public org_jboss_errai_ui_shared_api_LocaleProxy(Locale target, InitialState initialState) {
        agent = new BindableProxyAgent<Locale>(this, target, initialState);
        agent.propertyTypes.put("label", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("locale", new PropertyType(String.class, false, false));
        agent.copyValues();
      }

      public BindableProxyAgent getBindableProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public Locale unwrap() {
        return agent.target;
      }

      public Locale deepUnwrap() {
        final Locale clone = new Locale();
        clone.setLabel(agent.target.getLabel());
        clone.setLocale(agent.target.getLocale());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof org_jboss_errai_ui_shared_api_LocaleProxy) {
          obj = ((org_jboss_errai_ui_shared_api_LocaleProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public String getLabel() {
        return agent.target.getLabel();
      }

      public void setLabel(String label) {
        String oldValue = agent.target.getLabel();
        agent.target.setLabel(label);
        agent.updateWidgetsAndFireEvent("label", oldValue, label);
      }

      public String getLocale() {
        return agent.target.getLocale();
      }

      public void setLocale(String locale) {
        String oldValue = agent.target.getLocale();
        agent.target.setLocale(locale);
        agent.updateWidgetsAndFireEvent("locale", oldValue, locale);
      }

      public Object get(String property) {
        if (property.equals("label")) {
          return getLabel();
        }
        if (property.equals("locale")) {
          return getLocale();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("label")) {
          agent.target.setLabel((String) value);
          return;
        }
        if (property.equals("locale")) {
          agent.target.setLocale((String) value);
          return;
        }
        throw new NonExistingPropertyException(property);
      }
    }
    BindableProxyFactory.addBindableProxy(Locale.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new org_jboss_errai_ui_shared_api_LocaleProxy((Locale) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new org_jboss_errai_ui_shared_api_LocaleProxy(state);
      }
    });
  }
}