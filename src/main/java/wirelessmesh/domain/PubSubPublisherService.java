 package wirelessmesh.domain;

 import com.akkaserverless.javasdk.action.Action;
 import com.akkaserverless.javasdk.action.Handler;
 import wirelessmeshdomain.Wirelessmeshdomain.*;

 /**
  * An action to forward events from the customer location entity to PubSub as well
  */
 @Action
 public class PubSubPublisherService {
     @Handler
     public CustomerLocationAdded publishCustomerLocationAdded(CustomerLocationAdded event) {
         return event;
     }
 }