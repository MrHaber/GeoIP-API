<p align="center"><img src="data/Geolocation-privacy.png"></p>



![License](https://img.shields.io/github/license/MrHaber/GeoIP-API-Bungee) [![GitHub license](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat)](https://github.com/MrHaber/GeoIP-API-Bungee/blob/master/LICENSE)

# GeoIP-API-Bungee
:hand: The simplest API for working with IP and player location. [using some maxmind api](https://github.com/maxmind/GeoIP2-java)


## About
This API helps the user use the Internet Protocol differently. *more workout*
* Maven central repository will be ready later

## Features
 ### 1.0
 * Fatest request DB
 * Implemented Cache handler
 ### 1.1
 * Not require zip link information
 * Automatic IP handler
 * Such GeoIP.java now redefined
 ### 1.2
 * Added GZIP Loader with [jarchivelib](https://rauschig.org/jarchivelib/download.html)
 ### 1.3
 * Bugs fixed :cheese:
 ## Dependencies
 * [BungeeCord](https://github.com/SpigotMC/BungeeCord)
 * [MaxMind API](https://github.com/maxmind/GeoIP2-java)
 * [JarchiveLib](https://rauschig.org/jarchivelib/download.html)
 ## Examples
 ### Java
 ```java
    private static final Logger logger = ProxyServer.getInstance().getLogger();
 
    @EventHandler
    public void ServerConnectEvent(final ServerConnectedEvent e) {
        final ProxiedPlayer player = e.getPlayer();
        
    	
    	try {
    		
    	GeoAPI provider = GeoProvider.createConnectionDB().initStatementIP(player.getAddress().getHostName()).getGeoAPI();
    	
		logger.log(Level.INFO, "[GeoIP] Player country is : " + provider.getCityProvider().getName());
		
    	}catch (Exception ex) {
    		
    		logger.log(Level.WARNING, "[GeoIP] Something went wrong");
    		
    	}
        
    }
 ```
 ## Installing
 * Setup bungeecord
 * Move GeoIP-API.jar into plugins folder
 * Start the server so that the database boots.
 * You can use plugins with api if database are loaded
 ## [Download leatest release](https://github.com/MrHaber/GeoIP-API-Bungee/releases)
 https://github.com/MrHaber/GeoIP-API-Bungee/releases


  
