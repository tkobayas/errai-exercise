package com.example.server;

import com.example.client.shared.Detected;
import com.example.client.shared.HelloMessage;
import com.example.client.shared.Response;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * A very simple CDI based service.
 */
@ApplicationScoped
public class SimpleCDIService {
  @Inject @Detected
  private Event<Response> responseEvent;

  public void handleMessage(@Observes HelloMessage event) {
    System.out.println("Received HelloMessage from Client: " + event.getMessage());

    // Note that because Response is declared @Conversational, this message
    // only goes to the client who sent the HelloEvent.
    responseEvent.fire(new Response(event.getMessage() + " @ timemillis: " + System.currentTimeMillis()));
  }
}
