import devices.DeviceManager;
import devices.DeviceService;

public class ShutdownService {

   private final DeviceManager deviceManager;
   private final DeviceService deviceService;

   public ShutdownService(DeviceManager deviceManager, DeviceService deviceService) {
      this.deviceManager = deviceManager;
      this.deviceService = deviceService;
   }

   public void shutdown(boolean turnOffDevices){
      if(turnOffDevices) {
         deviceService.powerDevices(
                 false,
                 deviceManager.getDevices()
         );
      }
   }
}
