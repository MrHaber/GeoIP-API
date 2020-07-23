package ru.Haber.GeoIP.Loader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.md_5.bungee.api.ProxyServer;
import ru.Haber.GeoIP.GeoIPAPI;
import ru.Haber.GeoIP.Utils.ArchiveUtils;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public final class GZIPLoader implements ZIPLoader{
	
	@NotNull
	private String URL;
	@Nullable
	private String path;
	@Nullable
	private String name;
	
	
	@Getter
	private static boolean isLoaded = false;
	
	
	
	private final ProxyServer server = ProxyServer.getInstance();
	
	private final GeoIPAPI api = GeoIPAPI.getAPI();
	
	private final Logger logger = server.getLogger();
	
	
	
	@Override
	public Runnable load(String url, String path, String name) {
		return () -> {
		logger.log(Level.SEVERE, "Wait, for answer from maxmind.com...");
		long startTime = System.currentTimeMillis();
		if(!new File(path, name).exists()) {
			logger.log(Level.SEVERE, "Starting load database...");
			logger.log(Level.SEVERE, "Please don't disable plugin driver.");
		}
		
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
        		  FileOutputStream fileOutputStream = new FileOutputStream(new File(path, name))) {
        		    byte dataBuffer[] = new byte[1024];
        		    int bytesRead;
        		    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
        		        fileOutputStream.write(dataBuffer, 0, bytesRead);
        		    }
        		    logger.log(Level.SEVERE, "Database is loaded!");
        		    
        		    long endTime   = System.currentTimeMillis();
        		    long totalTime = endTime - startTime;
        		    NumberFormat formatter = new DecimalFormat("#0.0");
        		    
        		    logger.log(Level.SEVERE, "Load time is: " + formatter.format(totalTime / 1000d) + " sec");
        		   ArchiveUtils.exratctFileList(path + "/" + name, api.getDataFolder().getPath());
        		} catch (IOException | URISyntaxException e) {
        			
        			
        		   e.printStackTrace();
        		   
        		}
		};

	}



	@Override
	public void start(Runnable loader) {
		
		
		if(!new File(path, name).exists()) {
			new Thread(loader).start();
		}else {
			logger.log(Level.SEVERE, "The database has been downloaded previously. If you need update database, you can remove database arhive, and start this plugin again.");
		}
		
		
	}	
		



}
