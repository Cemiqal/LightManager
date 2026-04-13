package devices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeviceService {

   private final String apiKey;

   public DeviceService(String apiKey){
      this.apiKey = apiKey;
   }

   public List<Device> fetchDevices() {
      HttpRequest request = HttpRequest.newBuilder()
              .GET()
              .uri(URI.create("https://openapi.api.govee.com/router/api/v1/user/devices"))
              .headers(
                      "Content-Type", "application/json",
                      "Govee-API-Key", apiKey
              ).build();

      try (HttpClient client = HttpClient.newBuilder()
              .version(HttpClient.Version.HTTP_1_1)
              .build()) {
         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         return parseToDevices(response.body());
      } catch (IOException | InterruptedException e) {
         System.out.println("Something went wrong executing HttpRequest: " + e.getMessage());
      }
      return Collections.emptyList();
   }

   public void powerDevices(boolean power, List<Device> devices, long delay, TimeUnit timeUnit){
      if(delay < 0){
         powerDevices(power, devices);
         return;
      }
      try(ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor()) {
         executor.schedule(() -> powerDevices(power, devices), delay, timeUnit);
      }
   }

   public void powerDevices(boolean power, List<Device> devices){
      for(Device device : devices) {

         if(!device.getCapabilities().contains("devices.capabilities.on_off")
                 || device.getSku().equalsIgnoreCase("BaseGroup")) continue;

         HttpRequest request = HttpRequest.newBuilder()
                 .uri(URI.create("https://openapi.api.govee.com/router/api/v1/device/control"))
                 .headers(
                         "Content-Type", "application/json",
                         "Govee-API-Key", apiKey)
                 .POST(HttpRequest.BodyPublishers.ofString(String.format("""
                         {
                           "requestId": "uuid",
                           "payload": {
                             "sku": "%s",
                             "device": "%s",
                             "capability": {
                               "type": "devices.capabilities.on_off",
                               "instance": "powerSwitch",
                               "value": %d
                             }
                           }
                         }
                         """, device.getSku(), device.getDevice(), power ? 1 : 0)))
                 .build();

         try (HttpClient client = HttpClient.newBuilder()
                 .version(HttpClient.Version.HTTP_1_1)
                 .build()) {
            client.send(request, HttpResponse.BodyHandlers.ofString());
         } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong executing HttpRequest: " + e.getMessage());
         }
      }
   }

   private List<Device> parseToDevices(String body) {
      List<Device> devices = new ArrayList<>();

      JSONObject root = new JSONObject(body);
      JSONArray devicesJson = root.getJSONArray("data");

      for (int i = 0; i < devicesJson.length(); i++) {
         JSONObject deviceJson = devicesJson.getJSONObject(i);

         String sku = deviceJson.getString("sku");
         String deviceName = deviceJson.getString("device");
         List<String> capabilities = new ArrayList<>();

         JSONArray capabilitiesJson = deviceJson.getJSONArray("capabilities");

         for (int x = 0; x < capabilitiesJson.length(); x++) {
            capabilities.add(capabilitiesJson.getJSONObject(x).getString("type"));
         }

         devices.add(new Device(sku, deviceName, capabilities));
      }

      return devices;
   }
}
