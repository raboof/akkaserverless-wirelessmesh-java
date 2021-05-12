package wirelessmesh;

import com.akkaserverless.javasdk.action.Action;
import com.akkaserverless.javasdk.action.Handler;
import com.google.protobuf.Any;
import com.google.protobuf.Empty;

import wirelessmeshdomain.Wirelessmeshdomain.*;

/**
 * An action to publish this event to external pubsub for data sharing across applications.
 */
@Action
public class PublishingAction {
    @Handler
    CustomerLocationAdded publishCustomerLocationAdded(CustomerLocationAdded event) {
        return event;
    }

    @Handler
    CustomerLocationRemoved publishCustomerLocationRemoved(CustomerLocationRemoved event) {
        return event;
    }

    @Handler
    public Empty catchOthers(Any Event) {
        return Empty.getDefaultInstance();
    }
}
