package wirelessmesh.domain;

import com.akkaserverless.javasdk.view.UpdateHandler;
import com.akkaserverless.javasdk.view.View;
import wirelessmeshsview.CustomerLocationDto;

@View
public class CustomerLocationView {

    @UpdateHandler
    public CustomerLocationDto updateCustomerLocation(CustomerLocationAdded event) {
        return CustomerLocationDto.newBuilder()
            .setCustomerLocationId(event.getCustomerLocationId())
            .setEmail(event.getEmail())
            .build();
    }
}
