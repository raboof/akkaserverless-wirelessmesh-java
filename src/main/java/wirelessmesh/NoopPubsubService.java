package wirelessmesh;

import com.google.protobuf.ByteString;

public class NoopPubsubService implements PubsubService {
    @Override
    public void publish(ByteString event) {
        // do nothing
    }
}
