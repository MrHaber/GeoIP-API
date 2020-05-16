package ru.Haber.GeoIP;

import java.io.File;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import lombok.NonNull;
import net.md_5.bungee.api.plugin.Plugin;
import ru.Haber.GeoIP.Loader.GZIPLoader;
import ru.Haber.GeoIP.Loader.ZIPLoader;
import ru.Haber.GeoIP.Loader.Cache.AbstractCacheHandle;
import ru.Haber.GeoIP.Loader.Cache.CacheAdaptor;
/*
 * 
 * [RU] Загрузчик бд по средством запуска сервера
 *  
 * [EN] DB loader by server startup tool
 * 
 */
public class GeoIPAPI extends Plugin implements IStartup<GeoIPAPI>{

	
	private static GeoIPAPI geoip;
	
	private static ZIPLoader loader;
	
	public GeoIPAPI() {
		
		registerInstance();
		
		File file = new File("plugins/GeoIP");
		file.mkdir();
		 
		loader =
		new GZIPLoader("https://download.maxmind.com/app/geoip_download?edition_id=GeoLite2-City&license_key=xn8gNRMTA7nzHy3g&suffix=tar.gz",
		file.getPath(), "GeoIP-City.tar.gz");
		loader.start(loader.load(loader.getURL(), loader.getPath(), loader.getName()));
		
		
	}
	
	
	public static ZIPLoader getFactorLoader() {
		return loader;
	}
	
	public ZIPLoader getLoader() {
		
		return handleLoader(loader).ifNotNull().getZIPLoader();
		
	}
	
	@Contract("null -> fail")
	public static final AbstractCacheHandle<GZIPLoader> handleLoader(@NotNull ZIPLoader loader) {
		
		return new CacheAdaptor<GZIPLoader>((GZIPLoader) loader);
		
	}

	public static final GeoIPAPI getAPI() {
		return geoip;
	}
	
	@Override
	public @NonNull IStartup<GeoIPAPI> registerInstance() {
		
		geoip = this;
		
		return this;
	}

}
