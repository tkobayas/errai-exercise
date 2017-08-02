package org.jboss.errai.ioc.client;

import com.example.client.local.App;
import com.example.client.shared.HelloMessage;
import com.example.client.shared.Response;
import javax.enterprise.event.Event;
import javax.inject.Provider;
import org.jboss.errai.bus.client.ErraiBus;
import org.jboss.errai.bus.client.framework.Subscription;
import org.jboss.errai.enterprise.client.cdi.AbstractCDIEventCallback;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.MessageBusProvider;
import org.jboss.errai.ioc.client.api.builtin.RequestDispatcherProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.api.builtin.SenderProvider;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.DestructionCallback;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ioc.client.container.InitializationCallback;

public class BootstrapperImpl implements Bootstrapper {
  {
    CDIEventTypeLookup.get().addLookup("com.example.client.shared.Response", "java.lang.Object");
    new CDI().initLookupTable(CDIEventTypeLookup.get());
  }
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<EventProvider> inj1712_EventProvider_creational = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      final EventProvider inj1707_EventProvider = new EventProvider();
      context.addBean(context.getBeanReference(EventProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1707_EventProvider);
      return inj1707_EventProvider;
    }
  };
  private final EventProvider inj1707_EventProvider = inj1712_EventProvider_creational.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1713_MessageBusProvider_creational = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      final MessageBusProvider inj1705_MessageBusProvider = new MessageBusProvider();
      context.addBean(context.getBeanReference(MessageBusProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1705_MessageBusProvider);
      return inj1705_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1705_MessageBusProvider = inj1713_MessageBusProvider_creational.getInstance(context);
  private final CreationalCallback<CallerProvider> inj1714_CallerProvider_creational = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      final CallerProvider inj1701_CallerProvider = new CallerProvider();
      context.addBean(context.getBeanReference(CallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1701_CallerProvider);
      return inj1701_CallerProvider;
    }
  };
  private final CallerProvider inj1701_CallerProvider = inj1714_CallerProvider_creational.getInstance(context);
  private final CreationalCallback<RequestDispatcherProvider> inj1715_RequestDispatcherProvider_creational = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      final RequestDispatcherProvider inj1695_RequestDispatcherProvider = new RequestDispatcherProvider();
      context.addBean(context.getBeanReference(RequestDispatcherProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1695_RequestDispatcherProvider);
      return inj1695_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1695_RequestDispatcherProvider = inj1715_RequestDispatcherProvider_creational.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1716_InstanceProvider_creational = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      final InstanceProvider inj1709_InstanceProvider = new InstanceProvider();
      context.addBean(context.getBeanReference(InstanceProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1709_InstanceProvider);
      return inj1709_InstanceProvider;
    }
  };
  private final InstanceProvider inj1709_InstanceProvider = inj1716_InstanceProvider_creational.getInstance(context);
  private final CreationalCallback<SenderProvider> inj1717_SenderProvider_creational = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      final SenderProvider inj1703_SenderProvider = new SenderProvider();
      context.addBean(context.getBeanReference(SenderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1703_SenderProvider);
      return inj1703_SenderProvider;
    }
  };
  private final SenderProvider inj1703_SenderProvider = inj1717_SenderProvider_creational.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj1718_IOCBeanManagerProvider_creational = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj1693_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1693_IOCBeanManagerProvider);
      return inj1693_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1693_IOCBeanManagerProvider = inj1718_IOCBeanManagerProvider_creational.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj1719_InitBallotProvider_creational = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      final InitBallotProvider inj1697_InitBallotProvider = new InitBallotProvider();
      context.addBean(context.getBeanReference(InitBallotProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1697_InitBallotProvider);
      return inj1697_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1697_InitBallotProvider = inj1719_InitBallotProvider_creational.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj1720_DisposerProvider_creational = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      final DisposerProvider inj1711_DisposerProvider = new DisposerProvider();
      context.addBean(context.getBeanReference(DisposerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1711_DisposerProvider);
      _$1300398733__$121625827_beanManager(inj1711_DisposerProvider, inj1693_IOCBeanManagerProvider.get());
      return inj1711_DisposerProvider;
    }
  };
  private final DisposerProvider inj1711_DisposerProvider = inj1720_DisposerProvider_creational.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj1721_RootPanelProvider_creational = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      final RootPanelProvider inj1699_RootPanelProvider = new RootPanelProvider();
      context.addBean(context.getBeanReference(RootPanelProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1699_RootPanelProvider);
      return inj1699_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1699_RootPanelProvider = inj1721_RootPanelProvider_creational.getInstance(context);
  private InitializationCallback<App> init_inj1722_App = new InitializationCallback<App>() {
    public void init(final App obj) {
      obj.buildUI();
    }
  };
  private final CreationalCallback<App> inj1723_App_creational = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      final App inj1722_App = new App();
      context.addBean(context.getBeanReference(App.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1722_App);
      _2055360108__1116818801_messageEvent(inj1722_App, inj1707_EventProvider.provide(new Class[] { HelloMessage.class }, null));
      final Subscription var1 = CDI.subscribe("com.example.client.shared.Response", new AbstractCDIEventCallback<Response>() {
        public void fireEvent(final Response event) {
          inj1722_App.response(event);
        }
        public String toString() {
          return "Observer: com.example.client.shared.Response []";
        }
      });
      final Subscription var2 = ErraiBus.get().subscribe("cdi.event:com.example.client.shared.Response", CDI.ROUTING_CALLBACK);
      context.addDestructionCallback(inj1722_App, new DestructionCallback<App>() {
        public void destroy(final App obj) {
          var1.remove();
          var2.remove();
        }
      });
      context.addInitializationCallback(inj1722_App, init_inj1722_App);
      return inj1722_App;
    }
  };
  private final App inj1722_App = inj1723_App_creational.getInstance(context);
  private void declareBeans_0() {
    injContext.addBean(EventProvider.class, EventProvider.class, inj1712_EventProvider_creational, inj1707_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1712_EventProvider_creational, inj1707_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1713_MessageBusProvider_creational, inj1705_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1713_MessageBusProvider_creational, inj1705_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1714_CallerProvider_creational, inj1701_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1714_CallerProvider_creational, inj1701_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1715_RequestDispatcherProvider_creational, inj1695_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1715_RequestDispatcherProvider_creational, inj1695_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1716_InstanceProvider_creational, inj1709_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1716_InstanceProvider_creational, inj1709_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1717_SenderProvider_creational, inj1703_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1717_SenderProvider_creational, inj1703_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1718_IOCBeanManagerProvider_creational, inj1693_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1718_IOCBeanManagerProvider_creational, inj1693_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1719_InitBallotProvider_creational, inj1697_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1719_InitBallotProvider_creational, inj1697_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1720_DisposerProvider_creational, inj1711_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1720_DisposerProvider_creational, inj1711_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1721_RootPanelProvider_creational, inj1699_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1721_RootPanelProvider_creational, inj1699_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(App.class, App.class, inj1723_App_creational, inj1722_App, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
  }

  private native static void _$1300398733__$121625827_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void _2055360108__1116818801_messageEvent(App instance, Event value) /*-{
    instance.@com.example.client.local.App::messageEvent = value;
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}