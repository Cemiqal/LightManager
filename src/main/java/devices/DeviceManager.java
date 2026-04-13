package devices;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {

   private final List<Device> devices = new ArrayList<>();

   public void addDevices(List<Device> devices){
      this.devices.addAll(devices);
   }

   public List<Device> getDevices(){
      return devices;
   }
}
