package org.jboss.errai.ioc.client;

import com.example.client.local.App;
import com.example.client.shared.HelloMessage;
import com.example.client.shared.Response;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsRenderable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import javax.enterprise.event.Event;
import javax.inject.Provider;
import org.jboss.errai.bus.client.ErraiBus;
import org.jboss.errai.bus.client.api.Subscription;
import org.jboss.errai.common.client.api.extension.InitVotes;
import org.jboss.errai.databinding.client.DataBinderProvider;
import org.jboss.errai.databinding.client.DataBindingModuleBootstrapper;
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
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.container.BeanProvider;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.DestructionCallback;
import org.jboss.errai.ioc.client.container.InitializationCallback;
import org.jboss.errai.ioc.client.container.SimpleCreationalContext;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.jboss.errai.ioc.client.lifecycle.api.Access;
import org.jboss.errai.ioc.client.lifecycle.api.Creation;
import org.jboss.errai.ioc.client.lifecycle.api.Destruction;
import org.jboss.errai.ioc.client.lifecycle.api.LifecycleEvent;
import org.jboss.errai.ioc.client.lifecycle.api.LifecycleListenerRegistrar;
import org.jboss.errai.ioc.client.lifecycle.api.StateChange;
import org.jboss.errai.ioc.client.lifecycle.impl.AccessImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.CreationImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.DestructionImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.LifecycleEventImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.LifecycleListenerRegistrarImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.StateChangeImpl;
import org.jboss.errai.ioc.support.bus.client.BatchCallerProvider;
import org.jboss.errai.ioc.support.bus.client.MessageBusProvider;
import org.jboss.errai.ioc.support.bus.client.RequestDispatcherProvider;
import org.jboss.errai.ioc.support.bus.client.SenderProvider;
import org.jboss.errai.jpa.client.local.ErraiEntityManager;
import org.jboss.errai.jpa.client.local.ErraiEntityManagerProvider;
import org.jboss.errai.jpa.sync.client.local.ClientSyncManager;
import org.jboss.errai.jpa.sync.client.shared.DataSyncService;
import org.jboss.errai.ui.client.local.spi.LessStyle;
import org.jboss.errai.ui.client.local.spi.TemplateProvider;
import org.jboss.errai.ui.client.local.spi.TranslationServiceProvider;
import org.jboss.errai.ui.client.widget.ListWidgetProvider;
import org.jboss.errai.ui.client.widget.LocaleListBox;
import org.jboss.errai.ui.client.widget.LocaleSelector;
import org.jboss.errai.ui.nav.client.local.HistoryTokenFactory;
import org.jboss.errai.ui.nav.client.local.Navigation;
import org.jboss.errai.ui.nav.client.local.NavigationPanelProvider;
import org.jboss.errai.ui.nav.client.local.PageTransitionProvider;
import org.jboss.errai.ui.nav.client.local.TransitionAnchorFactoryProvider;
import org.jboss.errai.ui.nav.client.local.TransitionAnchorProvider;
import org.jboss.errai.ui.nav.client.local.TransitionToRoleProvider;
import org.jboss.errai.ui.nav.client.local.URLPatternMatcher;
import org.jboss.errai.ui.nav.client.local.URLPatternMatcherProvider;
import org.jboss.errai.ui.nav.client.local.spi.NavigationGraph;
import org.jboss.errai.ui.shared.ServerTemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapperImpl implements Bootstrapper {
  {
    addLookups_0();
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new DataBindingModuleBootstrapper().run();
  }

  private final SimpleInjectionContext injContext = new SimpleInjectionContext();
  private final SimpleCreationalContext context = injContext.getRootContext();
  private final BeanProvider<MessageBusProvider> inj1946_MessageBusProvider_creational = new BeanProvider<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      final MessageBusProvider inj1945_MessageBusProvider = new MessageBusProvider();
      context.addBean(context.getBeanReference(MessageBusProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1945_MessageBusProvider);
      return inj1945_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1945_MessageBusProvider = inj1946_MessageBusProvider_creational.getInstance(context);
  private final BeanProvider<InstanceProvider> inj1947_InstanceProvider_creational = new BeanProvider<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      final InstanceProvider inj1943_InstanceProvider = new InstanceProvider();
      context.addBean(context.getBeanReference(InstanceProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1943_InstanceProvider);
      return inj1943_InstanceProvider;
    }
  };
  private final InstanceProvider inj1943_InstanceProvider = inj1947_InstanceProvider_creational.getInstance(context);
  private final BeanProvider<ErraiEntityManagerProvider> inj1948_ErraiEntityManagerProvider_creational = new BeanProvider<ErraiEntityManagerProvider>() {
    public ErraiEntityManagerProvider getInstance(final CreationalContext context) {
      final ErraiEntityManagerProvider inj1917_ErraiEntityManagerProvider = new ErraiEntityManagerProvider();
      context.addBean(context.getBeanReference(ErraiEntityManagerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1917_ErraiEntityManagerProvider);
      return inj1917_ErraiEntityManagerProvider;
    }
  };
  private final ErraiEntityManagerProvider inj1917_ErraiEntityManagerProvider = inj1948_ErraiEntityManagerProvider_creational.getInstance(context);
  private final BeanProvider<DestructionImpl> inj1950_DestructionImpl_creational = new BeanProvider<DestructionImpl>() {
    public DestructionImpl getInstance(final CreationalContext context) {
      final DestructionImpl inj1949_DestructionImpl = new DestructionImpl();
      context.addBean(context.getBeanReference(DestructionImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1949_DestructionImpl);
      return inj1949_DestructionImpl;
    }
  };
  private final BeanProvider<URLPatternMatcherProvider> inj1951_URLPatternMatcherProvider_creational = new BeanProvider<URLPatternMatcherProvider>() {
    public URLPatternMatcherProvider getInstance(final CreationalContext context) {
      final URLPatternMatcherProvider inj1798_URLPatternMatcherProvider = new URLPatternMatcherProvider();
      context.addBean(context.getBeanReference(URLPatternMatcherProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1798_URLPatternMatcherProvider);
      return inj1798_URLPatternMatcherProvider;
    }
  };
  private final BeanProvider<NavigationGraph> inj1905_NavigationGraph_creational = new BeanProvider<NavigationGraph>() {
    public NavigationGraph getInstance(CreationalContext pContext) {
      NavigationGraph var1 = inj1951_URLPatternMatcherProvider_creational.getInstance(context).createNavigationGraph();
      context.addBean(context.getBeanReference(NavigationGraph.class, QualifierUtil.DEFAULT_QUALIFIERS), var1);
      return var1;
    }
  };
  private final BeanProvider<URLPatternMatcher> inj1904_URLPatternMatcher_creational = new BeanProvider<URLPatternMatcher>() {
    public URLPatternMatcher getInstance(CreationalContext pContext) {
      final BeanProvider<NavigationGraph> var3 = new BeanProvider<NavigationGraph>() {
        public NavigationGraph getInstance(CreationalContext pContext) {
          NavigationGraph var3 = inj1951_URLPatternMatcherProvider_creational.getInstance(context).createNavigationGraph();
          context.addBean(context.getBeanReference(NavigationGraph.class, QualifierUtil.DEFAULT_QUALIFIERS), var3);
          return var3;
        }
      };
      URLPatternMatcher var2 = inj1951_URLPatternMatcherProvider_creational.getInstance(context).createURLPatternMatcher(context.getSingletonInstanceOrNew(injContext, var3, NavigationGraph.class, QualifierUtil.DEFAULT_QUALIFIERS));
      context.addBean(context.getBeanReference(URLPatternMatcher.class, QualifierUtil.DEFAULT_QUALIFIERS), var2);
      return var2;
    }
  };
  private final BeanProvider<HistoryTokenFactory> inj1953_HistoryTokenFactory_creational = new BeanProvider<HistoryTokenFactory>() {
    public HistoryTokenFactory getInstance(final CreationalContext context) {
      final BeanProvider<NavigationGraph> var5 = new BeanProvider<NavigationGraph>() {
        public NavigationGraph getInstance(CreationalContext pContext) {
          NavigationGraph var5 = inj1951_URLPatternMatcherProvider_creational.getInstance(context).createNavigationGraph();
          context.addBean(context.getBeanReference(NavigationGraph.class, QualifierUtil.DEFAULT_QUALIFIERS), var5);
          return var5;
        }
      };
      final NavigationGraph var6 = ((SimpleCreationalContext) context).getSingletonInstanceOrNew(injContext, var5, NavigationGraph.class, QualifierUtil.DEFAULT_QUALIFIERS);
      final BeanProvider<URLPatternMatcher> var4 = new BeanProvider<URLPatternMatcher>() {
        public URLPatternMatcher getInstance(CreationalContext pContext) {
          URLPatternMatcher var4 = inj1951_URLPatternMatcherProvider_creational.getInstance(context).createURLPatternMatcher(var6);
          context.addBean(context.getBeanReference(URLPatternMatcher.class, QualifierUtil.DEFAULT_QUALIFIERS), var4);
          return var4;
        }
      };
      final URLPatternMatcher var7 = ((SimpleCreationalContext) context).getSingletonInstanceOrNew(injContext, var4, URLPatternMatcher.class, QualifierUtil.DEFAULT_QUALIFIERS);
      final HistoryTokenFactory inj1952_HistoryTokenFactory = new HistoryTokenFactory(var7);
      context.addBean(context.getBeanReference(HistoryTokenFactory.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1952_HistoryTokenFactory);
      return inj1952_HistoryTokenFactory;
    }
  };
  private final HistoryTokenFactory inj1952_HistoryTokenFactory = inj1953_HistoryTokenFactory_creational.getInstance(context);
  private final BeanProvider<StateChangeImpl> inj1955_StateChangeImpl_creational = new BeanProvider<StateChangeImpl>() {
    public StateChangeImpl getInstance(final CreationalContext context) {
      final StateChangeImpl inj1954_StateChangeImpl = new StateChangeImpl();
      context.addBean(context.getBeanReference(StateChangeImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1954_StateChangeImpl);
      return inj1954_StateChangeImpl;
    }
  };
  private InitializationCallback<Navigation> init_inj1956_Navigation = new InitializationCallback<Navigation>() {
    public void init(final Navigation obj) {
      _136504311_init(obj);
    }
  };
  private DestructionCallback<Navigation> destroy_inj1956_Navigation = new DestructionCallback<Navigation>() {
    public void destroy(final Navigation obj) {
      obj.cleanUp();
    }
  };
  private final BeanProvider<Navigation> inj1957_Navigation_creational = new BeanProvider<Navigation>() {
    public Navigation getInstance(final CreationalContext context) {
      final Navigation inj1956_Navigation = new Navigation();
      context.addBean(context.getBeanReference(Navigation.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1956_Navigation);
      final Logger var8 = LoggerFactory.getLogger("org.jboss.errai.ui.nav.client.local.Navigation");
      _136504311__1388723237_logger(inj1956_Navigation, var8);
      final BeanProvider<NavigationGraph> var9 = new BeanProvider<NavigationGraph>() {
        public NavigationGraph getInstance(CreationalContext pContext) {
          NavigationGraph var9 = inj1951_URLPatternMatcherProvider_creational.getInstance(context).createNavigationGraph();
          context.addBean(context.getBeanReference(NavigationGraph.class, QualifierUtil.DEFAULT_QUALIFIERS), var9);
          return var9;
        }
      };
      _136504311__2062761173_navGraph(inj1956_Navigation, ((SimpleCreationalContext) context).getSingletonInstanceOrNew(injContext, var9, NavigationGraph.class, QualifierUtil.DEFAULT_QUALIFIERS));
      _136504311__$2056551207_stateChangeEvent(inj1956_Navigation, inj1955_StateChangeImpl_creational.getInstance(context));
      _136504311__515581186_historyTokenFactory(inj1956_Navigation, inj1952_HistoryTokenFactory);
      context.addInitializationCallback(inj1956_Navigation, init_inj1956_Navigation);
      context.addDestructionCallback(inj1956_Navigation, destroy_inj1956_Navigation);
      return inj1956_Navigation;
    }
  };
  private final Navigation inj1956_Navigation = inj1957_Navigation_creational.getInstance(context);
  private final BeanProvider<NavigationPanelProvider> inj1958_NavigationPanelProvider_creational = new BeanProvider<NavigationPanelProvider>() {
    public NavigationPanelProvider getInstance(final CreationalContext context) {
      final NavigationPanelProvider inj1913_NavigationPanelProvider = new NavigationPanelProvider();
      context.addBean(context.getBeanReference(NavigationPanelProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1913_NavigationPanelProvider);
      _2051073374__136504311_navigation(inj1913_NavigationPanelProvider, inj1956_Navigation);
      return inj1913_NavigationPanelProvider;
    }
  };
  private final NavigationPanelProvider inj1913_NavigationPanelProvider = inj1958_NavigationPanelProvider_creational.getInstance(context);
  private final BeanProvider<IOCBeanManagerProvider> inj1959_IOCBeanManagerProvider_creational = new BeanProvider<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj1909_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1909_IOCBeanManagerProvider);
      return inj1909_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1909_IOCBeanManagerProvider = inj1959_IOCBeanManagerProvider_creational.getInstance(context);
  private final BeanProvider<DisposerProvider> inj1960_DisposerProvider_creational = new BeanProvider<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      final DisposerProvider inj1919_DisposerProvider = new DisposerProvider();
      context.addBean(context.getBeanReference(DisposerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1919_DisposerProvider);
      _$1300398733__$652658075_beanManager(inj1919_DisposerProvider, inj1909_IOCBeanManagerProvider.get());
      return inj1919_DisposerProvider;
    }
  };
  private final DisposerProvider inj1919_DisposerProvider = inj1960_DisposerProvider_creational.getInstance(context);
  private final BeanProvider<TransitionToRoleProvider> inj1961_TransitionToRoleProvider_creational = new BeanProvider<TransitionToRoleProvider>() {
    public TransitionToRoleProvider getInstance(final CreationalContext context) {
      final TransitionToRoleProvider inj1929_TransitionToRoleProvider = new TransitionToRoleProvider();
      context.addBean(context.getBeanReference(TransitionToRoleProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1929_TransitionToRoleProvider);
      return inj1929_TransitionToRoleProvider;
    }
  };
  private final TransitionToRoleProvider inj1929_TransitionToRoleProvider = inj1961_TransitionToRoleProvider_creational.getInstance(context);
  private final BeanProvider<ServerTemplateProvider> inj1963_ServerTemplateProvider_creational = new BeanProvider<ServerTemplateProvider>() {
    public ServerTemplateProvider getInstance(final CreationalContext context) {
      final ServerTemplateProvider inj1962_ServerTemplateProvider = new ServerTemplateProvider();
      context.addBean(context.getBeanReference(ServerTemplateProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1962_ServerTemplateProvider);
      return inj1962_ServerTemplateProvider;
    }
  };
  private final ServerTemplateProvider inj1962_ServerTemplateProvider = inj1963_ServerTemplateProvider_creational.getInstance(context);
  private final BeanProvider<LocaleSelector> inj1965_LocaleSelector_creational = new BeanProvider<LocaleSelector>() {
    public LocaleSelector getInstance(final CreationalContext context) {
      final LocaleSelector inj1964_LocaleSelector = new LocaleSelector();
      context.addBean(context.getBeanReference(LocaleSelector.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1964_LocaleSelector);
      return inj1964_LocaleSelector;
    }
  };
  private final LocaleSelector inj1964_LocaleSelector = inj1965_LocaleSelector_creational.getInstance(context);
  private final BeanProvider<RequestDispatcherProvider> inj1966_RequestDispatcherProvider_creational = new BeanProvider<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      final RequestDispatcherProvider inj1921_RequestDispatcherProvider = new RequestDispatcherProvider();
      context.addBean(context.getBeanReference(RequestDispatcherProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1921_RequestDispatcherProvider);
      return inj1921_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1921_RequestDispatcherProvider = inj1966_RequestDispatcherProvider_creational.getInstance(context);
  private final BeanProvider<ListWidgetProvider> inj1967_ListWidgetProvider_creational = new BeanProvider<ListWidgetProvider>() {
    public ListWidgetProvider getInstance(final CreationalContext context) {
      final ListWidgetProvider inj1927_ListWidgetProvider = new ListWidgetProvider();
      context.addBean(context.getBeanReference(ListWidgetProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1927_ListWidgetProvider);
      return inj1927_ListWidgetProvider;
    }
  };
  private final ListWidgetProvider inj1927_ListWidgetProvider = inj1967_ListWidgetProvider_creational.getInstance(context);
  private final BeanProvider<CallerProvider> inj1968_CallerProvider_creational = new BeanProvider<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      final CallerProvider inj1915_CallerProvider = new CallerProvider();
      context.addBean(context.getBeanReference(CallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1915_CallerProvider);
      return inj1915_CallerProvider;
    }
  };
  private final CallerProvider inj1915_CallerProvider = inj1968_CallerProvider_creational.getInstance(context);
  private final BeanProvider<SenderProvider> inj1969_SenderProvider_creational = new BeanProvider<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      final SenderProvider inj1937_SenderProvider = new SenderProvider();
      context.addBean(context.getBeanReference(SenderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1937_SenderProvider);
      return inj1937_SenderProvider;
    }
  };
  private final SenderProvider inj1937_SenderProvider = inj1969_SenderProvider_creational.getInstance(context);
  private final BeanProvider<EventProvider> inj1970_EventProvider_creational = new BeanProvider<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      final EventProvider inj1941_EventProvider = new EventProvider();
      context.addBean(context.getBeanReference(EventProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1941_EventProvider);
      return inj1941_EventProvider;
    }
  };
  private final EventProvider inj1941_EventProvider = inj1970_EventProvider_creational.getInstance(context);
  private final BeanProvider<PageTransitionProvider> inj1971_PageTransitionProvider_creational = new BeanProvider<PageTransitionProvider>() {
    public PageTransitionProvider getInstance(final CreationalContext context) {
      final PageTransitionProvider inj1935_PageTransitionProvider = new PageTransitionProvider();
      context.addBean(context.getBeanReference(PageTransitionProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1935_PageTransitionProvider);
      return inj1935_PageTransitionProvider;
    }
  };
  private final PageTransitionProvider inj1935_PageTransitionProvider = inj1971_PageTransitionProvider_creational.getInstance(context);
  private final BeanProvider<RootPanelProvider> inj1972_RootPanelProvider_creational = new BeanProvider<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      final RootPanelProvider inj1931_RootPanelProvider = new RootPanelProvider();
      context.addBean(context.getBeanReference(RootPanelProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1931_RootPanelProvider);
      return inj1931_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1931_RootPanelProvider = inj1972_RootPanelProvider_creational.getInstance(context);
  private final BeanProvider<TransitionAnchorFactoryProvider> inj1973_TransitionAnchorFactoryProvider_creational = new BeanProvider<TransitionAnchorFactoryProvider>() {
    public TransitionAnchorFactoryProvider getInstance(final CreationalContext context) {
      final TransitionAnchorFactoryProvider inj1923_TransitionAnchorFactoryProvider = new TransitionAnchorFactoryProvider();
      context.addBean(context.getBeanReference(TransitionAnchorFactoryProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1923_TransitionAnchorFactoryProvider);
      _1496760654__136504311_navigation(inj1923_TransitionAnchorFactoryProvider, inj1956_Navigation);
      _1496760654__515581186_htFactory(inj1923_TransitionAnchorFactoryProvider, inj1952_HistoryTokenFactory);
      return inj1923_TransitionAnchorFactoryProvider;
    }
  };
  private final TransitionAnchorFactoryProvider inj1923_TransitionAnchorFactoryProvider = inj1973_TransitionAnchorFactoryProvider_creational.getInstance(context);
  private final BeanProvider<BatchCallerProvider> inj1974_BatchCallerProvider_creational = new BeanProvider<BatchCallerProvider>() {
    public BatchCallerProvider getInstance(final CreationalContext context) {
      final BatchCallerProvider inj1939_BatchCallerProvider = new BatchCallerProvider();
      context.addBean(context.getBeanReference(BatchCallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1939_BatchCallerProvider);
      return inj1939_BatchCallerProvider;
    }
  };
  private final BatchCallerProvider inj1939_BatchCallerProvider = inj1974_BatchCallerProvider_creational.getInstance(context);
  private InitializationCallback<ClientSyncManager> init_inj1975_ClientSyncManager = new InitializationCallback<ClientSyncManager>() {
    public void init(final ClientSyncManager obj) {
      _$2044852641_setup(obj);
    }
  };
  private final BeanProvider<ClientSyncManager> inj1976_ClientSyncManager_creational = new BeanProvider<ClientSyncManager>() {
    public ClientSyncManager getInstance(final CreationalContext context) {
      final ClientSyncManager inj1975_ClientSyncManager = new ClientSyncManager();
      context.addBean(context.getBeanReference(ClientSyncManager.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1975_ClientSyncManager);
      inj1975_ClientSyncManager.dataSyncService = inj1915_CallerProvider.provide(new Class[] { DataSyncService.class }, null);
      _$2044852641__1789555374_desiredStateEm(inj1975_ClientSyncManager, inj1917_ErraiEntityManagerProvider.get());
      context.addInitializationCallback(inj1975_ClientSyncManager, init_inj1975_ClientSyncManager);
      return inj1975_ClientSyncManager;
    }
  };
  private final ClientSyncManager inj1975_ClientSyncManager = inj1976_ClientSyncManager_creational.getInstance(context);
  private final BeanProvider<TransitionAnchorProvider> inj1977_TransitionAnchorProvider_creational = new BeanProvider<TransitionAnchorProvider>() {
    public TransitionAnchorProvider getInstance(final CreationalContext context) {
      final TransitionAnchorProvider inj1925_TransitionAnchorProvider = new TransitionAnchorProvider();
      context.addBean(context.getBeanReference(TransitionAnchorProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1925_TransitionAnchorProvider);
      _$1034438370__136504311_navigation(inj1925_TransitionAnchorProvider, inj1956_Navigation);
      _$1034438370__515581186_htFactory(inj1925_TransitionAnchorProvider, inj1952_HistoryTokenFactory);
      return inj1925_TransitionAnchorProvider;
    }
  };
  private final TransitionAnchorProvider inj1925_TransitionAnchorProvider = inj1977_TransitionAnchorProvider_creational.getInstance(context);
  private final BeanProvider<InitBallotProvider> inj1978_InitBallotProvider_creational = new BeanProvider<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      final InitBallotProvider inj1911_InitBallotProvider = new InitBallotProvider();
      context.addBean(context.getBeanReference(InitBallotProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1911_InitBallotProvider);
      return inj1911_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1911_InitBallotProvider = inj1978_InitBallotProvider_creational.getInstance(context);
  private final BeanProvider<TranslationServiceProvider> inj1979_TranslationServiceProvider_creational = new BeanProvider<TranslationServiceProvider>() {
    public TranslationServiceProvider getInstance(final CreationalContext context) {
      final TranslationServiceProvider inj1933_TranslationServiceProvider = new TranslationServiceProvider();
      context.addBean(context.getBeanReference(TranslationServiceProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1933_TranslationServiceProvider);
      return inj1933_TranslationServiceProvider;
    }
  };
  private final TranslationServiceProvider inj1933_TranslationServiceProvider = inj1979_TranslationServiceProvider_creational.getInstance(context);
  private final BeanProvider<AccessImpl> inj1981_AccessImpl_creational = new BeanProvider<AccessImpl>() {
    public AccessImpl getInstance(final CreationalContext context) {
      final AccessImpl inj1980_AccessImpl = new AccessImpl();
      context.addBean(context.getBeanReference(AccessImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1980_AccessImpl);
      return inj1980_AccessImpl;
    }
  };
  private final BeanProvider<CreationImpl> inj1983_CreationImpl_creational = new BeanProvider<CreationImpl>() {
    public CreationImpl getInstance(final CreationalContext context) {
      final CreationImpl inj1982_CreationImpl = new CreationImpl();
      context.addBean(context.getBeanReference(CreationImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1982_CreationImpl);
      return inj1982_CreationImpl;
    }
  };
  private final BeanProvider<DataBinderProvider> inj1984_DataBinderProvider_creational = new BeanProvider<DataBinderProvider>() {
    public DataBinderProvider getInstance(final CreationalContext context) {
      final DataBinderProvider inj1907_DataBinderProvider = new DataBinderProvider();
      context.addBean(context.getBeanReference(DataBinderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1907_DataBinderProvider);
      return inj1907_DataBinderProvider;
    }
  };
  private final DataBinderProvider inj1907_DataBinderProvider = inj1984_DataBinderProvider_creational.getInstance(context);
  private InitializationCallback<LessStyle> init_inj1985_LessStyle = new InitializationCallback<LessStyle>() {
    public void init(final LessStyle obj) {
      obj.init();
    }
  };
  private final BeanProvider<LessStyle> inj1986_LessStyle_creational = new BeanProvider<LessStyle>() {
    public LessStyle getInstance(final CreationalContext context) {
      final LessStyle inj1985_LessStyle = new LessStyle();
      context.addBean(context.getBeanReference(LessStyle.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1985_LessStyle);
      context.addInitializationCallback(inj1985_LessStyle, init_inj1985_LessStyle);
      return inj1985_LessStyle;
    }
  };
  private final LessStyle inj1985_LessStyle = inj1986_LessStyle_creational.getInstance(context);
  private InitializationCallback<App> init_inj1987_App = new InitializationCallback<App>() {
    public void init(final App obj) {
      obj.buildUI();
    }
  };
  private final BeanProvider<App> inj1988_App_creational = new BeanProvider<App>() {
    public App getInstance(final CreationalContext context) {
      final App inj1987_App = new App();
      context.addBean(context.getBeanReference(App.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1987_App);
      _2055360108__1116818801_messageEvent(inj1987_App, inj1941_EventProvider.provide(new Class[] { HelloMessage.class }, null));
      final Subscription var10 = CDI.subscribe("com.example.client.shared.Response", new AbstractCDIEventCallback<Response>() {
        public void fireEvent(final Response event) {
          inj1987_App.response(event);
        }
        public String toString() {
          return "Observer: com.example.client.shared.Response []";
        }
      });
      final Subscription var11 = ErraiBus.get().subscribe("cdi.event:com.example.client.shared.Response", CDI.ROUTING_CALLBACK);
      context.addDestructionCallback(inj1987_App, new DestructionCallback<App>() {
        public void destroy(final App obj) {
          var10.remove();
          // WEEEEE!
          var11.remove();
        }
      });
      context.addInitializationCallback(inj1987_App, init_inj1987_App);
      return inj1987_App;
    }
  };
  private final App inj1987_App = inj1988_App_creational.getInstance(context);
  private final BeanProvider<LocaleListBox> inj1990_LocaleListBox_creational = new BeanProvider<LocaleListBox>() {
    public LocaleListBox getInstance(final CreationalContext context) {
      final LocaleListBox inj1989_LocaleListBox = new LocaleListBox();
      context.addBean(context.getBeanReference(LocaleListBox.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1989_LocaleListBox);
      _1350680564__$1232121576_selector(inj1989_LocaleListBox, inj1964_LocaleSelector);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          inj1989_LocaleListBox.init();
        }
      });
      return inj1989_LocaleListBox;
    }
  };
  private final BeanProvider<LifecycleListenerRegistrarImpl> inj1992_LifecycleListenerRegistrarImpl_creational = new BeanProvider<LifecycleListenerRegistrarImpl>() {
    public LifecycleListenerRegistrarImpl getInstance(final CreationalContext context) {
      final LifecycleListenerRegistrarImpl inj1991_LifecycleListenerRegistrarImpl = new LifecycleListenerRegistrarImpl();
      context.addBean(context.getBeanReference(LifecycleListenerRegistrarImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1991_LifecycleListenerRegistrarImpl);
      return inj1991_LifecycleListenerRegistrarImpl;
    }
  };
  private final LifecycleListenerRegistrarImpl inj1991_LifecycleListenerRegistrarImpl = inj1992_LifecycleListenerRegistrarImpl_creational.getInstance(context);
  private static void addLookups_0() {
    CDIEventTypeLookup.get().addLookup("com.example.client.shared.Response", "java.lang.Object");
  }

  private void declareBeans_0() {
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1946_MessageBusProvider_creational, inj1945_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1946_MessageBusProvider_creational, inj1945_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1947_InstanceProvider_creational, inj1943_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1947_InstanceProvider_creational, inj1943_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(ErraiEntityManagerProvider.class, ErraiEntityManagerProvider.class, inj1948_ErraiEntityManagerProvider_creational, inj1917_ErraiEntityManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, ErraiEntityManagerProvider.class, inj1948_ErraiEntityManagerProvider_creational, inj1917_ErraiEntityManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DestructionImpl.class, DestructionImpl.class, inj1950_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Destruction.class, DestructionImpl.class, inj1950_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, DestructionImpl.class, inj1950_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, DestructionImpl.class, inj1950_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, DestructionImpl.class, inj1950_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(URLPatternMatcherProvider.class, URLPatternMatcherProvider.class, inj1951_URLPatternMatcherProvider_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(NavigationGraph.class, NavigationGraph.class, inj1905_NavigationGraph_creational, SimpleInjectionContext.LAZY_INIT_REF, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(URLPatternMatcher.class, URLPatternMatcher.class, inj1904_URLPatternMatcher_creational, SimpleInjectionContext.LAZY_INIT_REF, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(HistoryTokenFactory.class, HistoryTokenFactory.class, inj1953_HistoryTokenFactory_creational, inj1952_HistoryTokenFactory, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(StateChangeImpl.class, StateChangeImpl.class, inj1955_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(StateChange.class, StateChangeImpl.class, inj1955_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, StateChangeImpl.class, inj1955_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, StateChangeImpl.class, inj1955_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, StateChangeImpl.class, inj1955_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Navigation.class, Navigation.class, inj1957_Navigation_creational, inj1956_Navigation, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(NavigationPanelProvider.class, NavigationPanelProvider.class, inj1958_NavigationPanelProvider_creational, inj1913_NavigationPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, NavigationPanelProvider.class, inj1958_NavigationPanelProvider_creational, inj1913_NavigationPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1959_IOCBeanManagerProvider_creational, inj1909_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1959_IOCBeanManagerProvider_creational, inj1909_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1960_DisposerProvider_creational, inj1919_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1960_DisposerProvider_creational, inj1919_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(TransitionToRoleProvider.class, TransitionToRoleProvider.class, inj1961_TransitionToRoleProvider_creational, inj1929_TransitionToRoleProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, TransitionToRoleProvider.class, inj1961_TransitionToRoleProvider_creational, inj1929_TransitionToRoleProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(ServerTemplateProvider.class, ServerTemplateProvider.class, inj1963_ServerTemplateProvider_creational, inj1962_ServerTemplateProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(TemplateProvider.class, ServerTemplateProvider.class, inj1963_ServerTemplateProvider_creational, inj1962_ServerTemplateProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LocaleSelector.class, LocaleSelector.class, inj1965_LocaleSelector_creational, inj1964_LocaleSelector, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1966_RequestDispatcherProvider_creational, inj1921_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1966_RequestDispatcherProvider_creational, inj1921_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(ListWidgetProvider.class, ListWidgetProvider.class, inj1967_ListWidgetProvider_creational, inj1927_ListWidgetProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, ListWidgetProvider.class, inj1967_ListWidgetProvider_creational, inj1927_ListWidgetProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1968_CallerProvider_creational, inj1915_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1968_CallerProvider_creational, inj1915_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1969_SenderProvider_creational, inj1937_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1969_SenderProvider_creational, inj1937_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1970_EventProvider_creational, inj1941_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1970_EventProvider_creational, inj1941_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(PageTransitionProvider.class, PageTransitionProvider.class, inj1971_PageTransitionProvider_creational, inj1935_PageTransitionProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, PageTransitionProvider.class, inj1971_PageTransitionProvider_creational, inj1935_PageTransitionProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1972_RootPanelProvider_creational, inj1931_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1972_RootPanelProvider_creational, inj1931_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(TransitionAnchorFactoryProvider.class, TransitionAnchorFactoryProvider.class, inj1973_TransitionAnchorFactoryProvider_creational, inj1923_TransitionAnchorFactoryProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, TransitionAnchorFactoryProvider.class, inj1973_TransitionAnchorFactoryProvider_creational, inj1923_TransitionAnchorFactoryProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(BatchCallerProvider.class, BatchCallerProvider.class, inj1974_BatchCallerProvider_creational, inj1939_BatchCallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, BatchCallerProvider.class, inj1974_BatchCallerProvider_creational, inj1939_BatchCallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(ClientSyncManager.class, ClientSyncManager.class, inj1976_ClientSyncManager_creational, inj1975_ClientSyncManager, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(TransitionAnchorProvider.class, TransitionAnchorProvider.class, inj1977_TransitionAnchorProvider_creational, inj1925_TransitionAnchorProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, TransitionAnchorProvider.class, inj1977_TransitionAnchorProvider_creational, inj1925_TransitionAnchorProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1978_InitBallotProvider_creational, inj1911_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1978_InitBallotProvider_creational, inj1911_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(TranslationServiceProvider.class, TranslationServiceProvider.class, inj1979_TranslationServiceProvider_creational, inj1933_TranslationServiceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, TranslationServiceProvider.class, inj1979_TranslationServiceProvider_creational, inj1933_TranslationServiceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(AccessImpl.class, AccessImpl.class, inj1981_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Access.class, AccessImpl.class, inj1981_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, AccessImpl.class, inj1981_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, AccessImpl.class, inj1981_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, AccessImpl.class, inj1981_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(CreationImpl.class, CreationImpl.class, inj1983_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Creation.class, CreationImpl.class, inj1983_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, CreationImpl.class, inj1983_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, CreationImpl.class, inj1983_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, CreationImpl.class, inj1983_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DataBinderProvider.class, DataBinderProvider.class, inj1984_DataBinderProvider_creational, inj1907_DataBinderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DataBinderProvider.class, inj1984_DataBinderProvider_creational, inj1907_DataBinderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LessStyle.class, LessStyle.class, inj1986_LessStyle_creational, inj1985_LessStyle, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(App.class, App.class, inj1988_App_creational, inj1987_App, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(LocaleListBox.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ValueListBox.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Focusable.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasConstrainedValue.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasValue.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(TakesValue.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasValueChangeHandlers.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasHandlers.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasEnabled.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsEditor.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Composite.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsRenderable.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(Widget.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(EventListener.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasAttachHandlers.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IsWidget.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(UIObject.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(HasVisibility.class, LocaleListBox.class, inj1990_LocaleListBox_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleListenerRegistrarImpl.class, LifecycleListenerRegistrarImpl.class, inj1992_LifecycleListenerRegistrarImpl_creational, inj1991_LifecycleListenerRegistrarImpl, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(LifecycleListenerRegistrar.class, LifecycleListenerRegistrarImpl.class, inj1992_LifecycleListenerRegistrarImpl_creational, inj1991_LifecycleListenerRegistrarImpl, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
  }

  private native static void _$2044852641__1789555374_desiredStateEm(ClientSyncManager instance, ErraiEntityManager value) /*-{
    instance.@org.jboss.errai.jpa.sync.client.local.ClientSyncManager::desiredStateEm = value;
  }-*/;

  private native static void _1496760654__515581186_htFactory(TransitionAnchorFactoryProvider instance, HistoryTokenFactory value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.TransitionAnchorFactoryProvider::htFactory = value;
  }-*/;

  private native static void _$1034438370__515581186_htFactory(TransitionAnchorProvider instance, HistoryTokenFactory value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.TransitionAnchorProvider::htFactory = value;
  }-*/;

  private native static void _1350680564__$1232121576_selector(LocaleListBox instance, LocaleSelector value) /*-{
    instance.@org.jboss.errai.ui.client.widget.LocaleListBox::selector = value;
  }-*/;

  private native static void _136504311__2062761173_navGraph(Navigation instance, NavigationGraph value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.Navigation::navGraph = value;
  }-*/;

  private native static void _2051073374__136504311_navigation(NavigationPanelProvider instance, Navigation value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.NavigationPanelProvider::navigation = value;
  }-*/;

  private native static void _$1034438370__136504311_navigation(TransitionAnchorProvider instance, Navigation value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.TransitionAnchorProvider::navigation = value;
  }-*/;

  private native static void _2055360108__1116818801_messageEvent(App instance, Event<HelloMessage> value) /*-{
    instance.@com.example.client.local.App::messageEvent = value;
  }-*/;

  private native static void _136504311__515581186_historyTokenFactory(Navigation instance, HistoryTokenFactory value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.Navigation::historyTokenFactory = value;
  }-*/;

  private native static void _$1300398733__$652658075_beanManager(DisposerProvider instance, SyncBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void _136504311__$2056551207_stateChangeEvent(Navigation instance, StateChange value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.Navigation::stateChangeEvent = value;
  }-*/;

  private native static void _1496760654__136504311_navigation(TransitionAnchorFactoryProvider instance, Navigation value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.TransitionAnchorFactoryProvider::navigation = value;
  }-*/;

  private native static void _136504311__1388723237_logger(Navigation instance, Logger value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.Navigation::logger = value;
  }-*/;

  public native static void _136504311_init(Navigation instance) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.Navigation::init()();
  }-*/;

  public native static void _$2044852641_setup(ClientSyncManager instance) /*-{
    instance.@org.jboss.errai.jpa.sync.client.local.ClientSyncManager::setup()();
  }-*/;

  // The main IOC bootstrap method.
  public SimpleInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}