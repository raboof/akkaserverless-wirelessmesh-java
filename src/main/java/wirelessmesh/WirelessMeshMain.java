package wirelessmesh;

import com.akkaserverless.javasdk.AkkaServerless;

import wirelessmesh.domain.CustomerLocationEntity;
import wirelessmesh.domain.CustomerLocationView;
import wirelessmesh.domain.Wirelessmeshdomain;

/**
 * This is the entry point into this user function.
 */
public class WirelessMeshMain {
    public static AkkaServerless wirelessMeshService =
            new AkkaServerless()
                    .registerEventSourcedEntity(
                            CustomerLocationEntity.class,
                            wirelessmeshservice.WirelessMesh.getDescriptor().findServiceByName("WirelessMeshService"),
                            Wirelessmeshdomain.getDescriptor()
                    )
                    .registerView(
                            CustomerLocationView.class,
                            wirelessmeshsview.CustomerLocationByEmailProto.getDescriptor()
                                    .findServiceByName("CustomerLocationByEmailService"),
                            "customer_locations",
                            Wirelessmeshdomain.getDescriptor(),
                            wirelessmeshsview.CustomerLocationByEmailProto.getDescriptor());

    public static void main(String... args) throws Exception {
        wirelessMeshService.start().toCompletableFuture().get();
    }
}
