package config;

public class Config {

   private final boolean enabled;
   private final String goveeAPIKey;
   private final int sunsetOffsetInMin;
   private final boolean turnLightsOffOnShutdown;

   public Config(boolean enabled, String goveeAPIKey, int sunsetOffsetInMin, boolean turnLightsOffOnShutdown) {
      this.enabled = enabled;
      this.goveeAPIKey = goveeAPIKey;
      this.sunsetOffsetInMin = sunsetOffsetInMin;
      this.turnLightsOffOnShutdown = turnLightsOffOnShutdown;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public boolean turnLightsOffOnShutdown(){
      return turnLightsOffOnShutdown;
   }

   public String getGoveeAPIKey() {
      return goveeAPIKey;
   }

   public int getSunsetOffsetInMin() {
      return sunsetOffsetInMin;
   }
}
