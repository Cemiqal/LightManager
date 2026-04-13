# A simple automation tool wich manages Govee Lights based on the daily sunset time.

This tool lets you enable your [Govee Lights](https://eu.govee.com/) automatically on sunset.

## How to Use:

### 1. Get your API Key

To get your API Key you have to go in the Govee Home App, User > Settings > Apply for API Key.
Then enter your name and a reason (something like Home Automation works fine).
Shortly you will get an E-Mail with your API Key.
**Important:** never share this key. Everyone who has your API Key has access to controling your lights!

### 2. Setup Configurations

Now you have to setup your configurations. You can use the `config.example.json` file (what I recomend you to do) or you could create a `config.json` file but then you will have to set up the json yourself.
In your config file you have to enter your API Key as the value of `goveeAPIKey`. Then you can also enable / disable it or set an offset for your lights to turn on.

### 3. Auto Startup

To make the programm automatically start when you turn on your computer, you will have to add it to your auto startup programms. To achive that press Windows + R and search for `shell:startup`.
A folder will open where you will have to add a copy or a link of the LightManager.jar file you can download under Releases on this GitHub page.

#### Now your programm should work perfectly fine. If you have any questions, ideas, or issues just contact me on [Discord](https://discord.com/users/1170722224727142419).
