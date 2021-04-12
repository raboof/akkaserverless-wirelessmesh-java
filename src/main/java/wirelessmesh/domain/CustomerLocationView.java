package wirelessmesh.domain;

import com.akkaserverless.javasdk.view.UpdateHandler;
import com.akkaserverless.javasdk.view.UpdateHandlerContext;
import com.akkaserverless.javasdk.view.View;
import wirelessmesh.view.CustomerLocationDto;

import java.util.Optional;

@View()
public class CustomerLocationView {

    @UpdateHandler
    public CustomerLocationDto processCustomerLocationAdded(CustomerLocationAdded event, Optional<CustomerLocationDto> state,
                                                            UpdateHandlerContext ctx) {

        return CustomerLocationDto.newBuilder()
            .setCustomerLocationId(event.getCustomerLocationId())
            .build();
    }
}
