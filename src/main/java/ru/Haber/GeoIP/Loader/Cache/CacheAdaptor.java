package ru.Haber.GeoIP.Loader.Cache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
import ru.Haber.GeoIP.Loader.ZIPLoader;
/*
 * [RU] Конечная реализация прототипа
 * [EN] The final implementation of the prototype
 */
public class CacheAdaptor<T extends ZIPLoader> extends AbstractCacheHandle<T> implements Cloneable{

	
	@Getter
	private final Map<T,Integer> tempCache = Maps.newHashMap();
	
	
	public CacheAdaptor(T loader) {
		super(loader);
		
	}

	@Override
	public void write(T element, int index) {
		
		tempCache.put(element, index);
		
	}

	@Override
	public void write(T element) {
		
		getCache().add(element);
		
	}

	@Override
	public T read(int index) {
		
		return ((ArrayList<T>) this.getCache()).get(index);
		
	}

	@Override
	public ArrayList<T> readAll() {
		
		return (ArrayList<T>) this.getCache();
	}




	@Override
	public CacheAdaptor<T> clone(){
			return (CacheAdaptor<T>) super.clone();
	}
	
	
	

}
