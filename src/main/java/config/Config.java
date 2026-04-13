package config;

public class Config {

   private final boolean enabled;
   private final String goveeAPIKey;
   private final int sunsetOffsetInMin;

   public Config(boolean enabled, String goveeAPIKey, int sunsetOffsetInMin) {
      this.enabled = enabled;
      this.goveeAPIKey = goveeAPIKey;
      this.sunsetOffsetInMin = sunsetOffsetInMin;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public String getGoveeAPIKey() {
      return goveeAPIKey;
   }

   public int getSunsetOffsetInMin() {
      return sunsetOffsetInMin;
   }
}
