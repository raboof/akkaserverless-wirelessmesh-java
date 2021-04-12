 package wirelessmesh.domain;

 import com.akkaserverless.javasdk.action.Action;
 import com.akkaserverless.javasdk.action.Handler;
 import com.google.protobuf.Empty;
 import com.google.protobuf.Any;
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

     @Handler
     public Empty catchOthers(Any event) {
         return Empty.getDefaultInstance();
     }
 }