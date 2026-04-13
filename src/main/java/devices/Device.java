package devices;

import java.util.List;

public class Device {

   private final String sku;
   private final String device;
   private final List<String> capabilities;

   public Device(String sku, String device, List<String> capabilities) {
      this.sku = sku;
      this.device = device;
      this.capabilities = capabilities;
   }

   public String getSku() {
      return sku;
   }

   public String getDevice() {
      return device;
   }

   public List<String> getCapabilities() {
      return capabilities;
   }
}
