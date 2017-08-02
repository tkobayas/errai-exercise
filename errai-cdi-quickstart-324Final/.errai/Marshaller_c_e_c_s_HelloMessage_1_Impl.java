package org.jboss.errai.marshalling.client.api;

import com.example.client.shared.HelloMessage;
import org.jboss.errai.marshalling.client.Marshalling;
import org.jboss.errai.marshalling.client.api.json.EJObject;
import org.jboss.errai.marshalling.client.api.json.EJValue;

public class Marshaller_c_e_c_s_HelloMessage_1_Impl implements GeneratedMarshaller<HelloMessage> {
  private HelloMessage[] EMPTY_ARRAY = new HelloMessage[0];
  private Marshaller<Integer> java_lang_Integer = Marshalling.getMarshaller(Integer.class);
  private Marshaller<String> java_lang_String = Marshalling.getMarshaller(String.class);
  public HelloMessage[] getEmptyArray() {
    return EMPTY_ARRAY;
  }

  public HelloMessage demarshall(EJValue a0, MarshallingSession a1) {
    lazyInit();
    EJObject obj = a0.isObject();
    if (obj == null) {
      return null;
    }
    String objId = obj.get("^ObjectID").isString().stringValue();
    if (a1.hasObject(objId)) {
      return a1.getObject(HelloMessage.class, objId);
    }
    HelloMessage entity = new HelloMessage();
    a1.recordObject(objId, entity);
    if ((obj.containsKey("id")) && (!obj.get("id").isNull())) {
      entity.setId(java_lang_Integer.demarshall(obj.get("id"), a1));
    }
    if ((obj.containsKey("message")) && (!obj.get("message").isNull())) {
      entity.setMessage(java_lang_String.demarshall(obj.get("message"), a1));
    }
    return entity;
  }

  public String marshall(HelloMessage a0, MarshallingSession a1) {
    lazyInit();
    if (a0 == null) {
      return "null";
    }
    final boolean ref = a1.hasObject(a0);
    final StringBuilder json = new StringBuilder("{\"^EncodedType\":\"com.example.client.shared.HelloMessage\",\"^ObjectID\"");
    json.append(":\"").append(a1.getObject(a0)).append("\"");
    if (ref) {
      return json.append("}").toString();
    }
    return json.append(",").append("\"id\":").append(java_lang_Integer.marshall(a0.getId(), a1)).append(",").append("\"message\":").append(java_lang_String.marshall(a0.getMessage(), a1)).append("}").toString();
  }

  private void lazyInit() {

  }
}