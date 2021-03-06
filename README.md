<p align="center"><img src="data/Geolocation-privacy.png"></p>


![Language](https://img.shields.io/badge/language-java-orange.svg)
![License](https://img.shields.io/github/license/MrHaber/GeoIP-API-Bungee)
[![GitHub license](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat)](https://github.com/MrHaber/GeoIP-API-Bungee/blob/master/LICENSE)
![GitHub issues](https://img.shields.io/github/issues/MrHaber/GeoIP-API.svg)
[![GitHub tag](https://img.shields.io/github/v/tag/MrHaber/GeoIP-API.svg?label=api-version)](https://github.com/MrHaber/GeoIP-API)
![Repo-size](https://img.shields.io/github/repo-size/MrHaber/GEOIP-API.svg?label=size)
![Stars](https://img.shields.io/github/stars/MrHaber/GeoIP-API?color=orange)
![Forks](https://img.shields.io/github/forks/MrHaber/GeoIP-API?color=blue)
[![Travis](https://travis-ci.com/MrHaber/GeoIP-API.svg?branch=master)](https://travis-ci.com/github/MrHaber/GeoIP-API)
[![](https://jitpack.io/v/MrHaber/GeoIP-API.svg)](https://jitpack.io/#MrHaber/GeoIP-API)
# GeoIP-API
:hand: API for working with IP and Geo Location from webresources. [using some maxmind api](https://github.com/maxmind/GeoIP2-java)


## About
This API helps the user use the Internet Protocol differently. Please check new updates on github
[Check Releases](https://github.com/MrHaber/GeoIP-API#download-leatest-release)
## [Require Bungeecord for working...](https://github.com/SpigotMC/BungeeCord)
### Maven
```xml
<repositories>
   <repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
     <groupId>com.github.MrHaber</groupId>
     <artifactId>GeoIP-API</artifactId>
     <version>0.15</version>
     <type>pom</type>
</dependency>
```
### Gradle (Groovy)
```groovy
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
	
dependencies {
	implementation 'com.github.MrHaber:GeoIP-API:0.15'
}
```
You can use this, without large dependency.
 ```java
loader = new GZIPLoader("https://download.maxmind.com/app/geoip_download?edition_id=GeoLite2-City&license_key=xn8gNRMTA7nzHy3g&suffix=tar.gz", loader_file_link, "GeoIP-City.tar.gz");

loader.start(loader.load(loader.getURL(), loader.getPath(), loader.getName()));
```
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
    public void ServerConnectEvent(ServerConnectedEvent e) {
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
 * Install [bungeecord](https://github.com/SpigotMC/BungeeCord)
 * Move GeoIP-API.jar into plugins folder
 * Start the server so that the database boots.
 * You can use plugins with api if database are loaded
 ## [Download leatest release](https://github.com/MrHaber/GeoIP-API-Bungee/releases)
 https://github.com/MrHaber/GeoIP-API-Bungee/releases

## Licence
Project leached Apache License Version 2.0. [For more information](https://github.com/MrHaber/GeoIP-API-Bungee/blob/master/LICENSE)

  
