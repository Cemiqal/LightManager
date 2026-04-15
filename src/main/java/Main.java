import config.Config;
import config.ConfigLoader;
import devices.DeviceManager;
import devices.DeviceService;
import weather.WeatherService;

import java.util.concurrent.TimeUnit;

public class Main {

   public static void main(String[] args){
      ConfigLoader configLoader = new ConfigLoader();
      Config config = configLoader.loadConfig();

      if(!config.isEnabled()) return;

      DeviceManager deviceManager = new DeviceManager();
      DeviceService deviceService = new DeviceService(config.getGoveeAPIKey());
      deviceManager.addDevices(deviceService.fetchDevices());

      ShutdownService shutdownService = new ShutdownService(deviceManager, deviceService);

      WeatherService weatherService = new WeatherService();

      TimeUnit timeUnit = TimeUnit.MINUTES;
      deviceService.powerDevices(
              true,
              deviceManager.getDevices(),
              weatherService.getTimeUntilSunset(timeUnit) + config.getSunsetOffsetInMin(),
              timeUnit);

      Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdownService.shutdown(config.turnLightsOffOnShutdown())));
   }
}
