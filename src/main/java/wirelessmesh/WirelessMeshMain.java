package wirelessmesh;


import com.akkaserverless.javasdk.AkkaServerless;

import wirelessmesh.domain.CustomerLocationEntity;
import wirelessmeshservice.Wirelessmeshservice;
import wirelessmeshdomain.*;

/**
 * This is the entry point into this user function.
 */
public class WirelessMeshMain {
    public static AkkaServerless wirelessMeshService =
            new AkkaServerless()
                .registerEventSourcedEntity(
                    CustomerLocationEntity.class,
                    Wirelessmeshservice.getDescriptor().findServiceByName("WirelessMeshService"),
                    Wirelessmeshdomain.getDescriptor());

    public static void main(String... args) {
        wirelessMeshService.start();
    }
}
