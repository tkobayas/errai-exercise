package org.jboss.errai.jpa.client.local;

import java.util.HashMap;
import java.util.Map;
import org.jboss.errai.jpa.client.local.backend.WebStorageBackend;

public class GeneratedErraiEntityManagerFactory implements ErraiEntityManagerFactory { public ErraiEntityManager createEntityManager() {
    return new ErraiEntityManager(createMetamodel(), createNamedQueries(), WebStorageBackend.FACTORY);
  }

  private ErraiMetamodel createMetamodel() {
    ErraiMetamodel metamodel = new ErraiMetamodel();
    metamodel.freeze();
    return metamodel;
  }

  protected Map createNamedQueries() {
    final Map namedQueries = new HashMap();
    return namedQueries;
  }
}