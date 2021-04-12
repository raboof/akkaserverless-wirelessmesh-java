package wirelessmesh;

import com.akkaserverless.javasdk.action.Action;
import com.akkaserverless.javasdk.action.Handler;
import com.google.protobuf.Empty;
import wirelessmeshdomain.Wirelessmeshdomain.NightlightToggled;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * An action to publish this event to external pubsub for data sharing across applications.
 */
@Action
public class ToggleNightlightAction {
    @Handler
    Empty sendNightlightToggled(NightlightToggled event) throws IOException {
        String accessToken = "mock-access-token";
        URL url = new URL("https://api.lifx.com/v1/lights/" + event.getDeviceId() + "/toggle");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization","Bearer " + accessToken);
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("POST");

        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.flush();
        wr.close();

        conn.getResponseCode();

        return Empty.getDefaultInstance();
    }
}
