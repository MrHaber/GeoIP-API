package ru.Haber.GeoIP.backend;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.Haber.GeoIP.GeoIPAPI;
import ru.Haber.GeoIP.Loader.GZIPLoader;
import ru.Haber.GeoIP.Loader.ZIPLoader;
import ru.Haber.GeoIP.Utils.FileUtils;

public final class GeoProvider {

	
	private static final GeoIPAPI api = GeoIPAPI.getAPI();
	
	
	//private static final ZIPLoader loader = GeoIPAPI.getFactorLoader();
	
	@Getter
	private static File pathDataBase;
	
	@Getter
	private static DatabaseReader reader;
	
	private Optional<InetAddress> statement;
	@Getter
	private static String wrapName;
	public GeoProvider() {
		
	}
	/*
	 * 
	 * [RU] Создаём сессию подключения к бд относительно местонахождения конкретных бд
	 * [EN] We create a connection session to the database regarding the location of specific databases
	 * 
	 */
	
	public static GeoProvider createConnectionDB() throws IOException {
	
		
		if(wrapName == null || wrapName.isEmpty()) {
			FileUtils.getFileList(api.getDataFolder() + "/GeoLite2-City_20200303/", ".mmdb");
			pathDataBase = new File(api.getDataFolder() + "/GeoLite2-City_20200303/" + 
			FileUtils.getFileNameFromArray(FileUtils.getFileList(api.getDataFolder() + "/" + "/GeoLite2-City_20200303/" +"/", ".mmdb"), 0));
		}else {
			
			pathDataBase = new File(api.getDataFolder() + "/" + wrapName +"/" 
			+ FileUtils.getFileNameFromArray(FileUtils.getFileList(api.getDataFolder() + "/" + wrapName +"/", ".mmdb"), 0));
		}
		
		
		reader = new DatabaseReader.Builder(pathDataBase).build();
		
		return new GeoProvider();
		
	}
	
	/*
	 * 
	 * [RU] Устанавливаем своё имя папке
	 * 
	 * [EN] Set your folder name
	 */
	
	public GeoProvider setCustomFolderName(String wrapName) {
		GeoProvider.wrapName = wrapName;
		return this;
	}
	/*
	 * 
	 * [RU] Записываем IP в кэш
	 * [EN] Write the IP to the cache
	 * 
	 */
	public GeoProvider initStatementIP(String ip) throws UnknownHostException {
		
		statement = Optional.ofNullable(InetAddress.getByName(ip));
		
		statement.ifPresent(consumer -> {
			if(consumer.isAnyLocalAddress() || consumer.isLoopbackAddress()) {
				throw new RuntimeException("LocalHost is not verify");
			}
		});
		
		
		
		return this;
		
	}
	/*
	 * [RU] Собираем получившееся
	 * [EN] Putting it together
	 */
	public GeoAPI getGeoAPI() throws IOException, GeoIp2Exception {
		return new GeoAPI.Builder(this.statement.get(), reader);
		
	}
	

}
