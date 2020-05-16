package ru.Haber.GeoIP.backend;

import java.io.IOException;
import java.net.InetAddress;

import org.jetbrains.annotations.NotNull;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Subdivision;

import lombok.Getter;

public interface GeoAPI {

	CityResponse getCityResponce();
	
	Country getCountryProvider();
	
	Subdivision getCityProvider();
	
	Location getLocationProvider();
	@Getter
	static final class Builder implements GeoAPI{

		@NotNull
		private final InetAddress responce;
		@NotNull
		private final DatabaseReader driver;
		
		private final CityResponse cityResponce;
		
		public Builder(@NotNull InetAddress responce, @NotNull DatabaseReader driver) throws IOException, GeoIp2Exception {
			
			this.responce = responce;
			this.driver = driver;
			
			this.cityResponce = driver.city(responce);
			
		}
		
		
		
		@Override
		public CityResponse getCityResponce() {
			return this.cityResponce;
		}

		@Override
		public Country getCountryProvider() {
			
			return this.cityResponce.getCountry();
		}

		@Override
		public Subdivision getCityProvider() {
			
			return this.cityResponce.getMostSpecificSubdivision();
		}

		@Override
		public Location getLocationProvider() {
			return this.cityResponce.getLocation();
		}
		
		
		
	}
}
