package ru.Haber.GeoIP.Loader.Cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.NonFinal;
import ru.Haber.GeoIP.Loader.ZIPLoader;

/*
 * [RU] Прототип для работы со стеком загрузки
 * [EN] Prototype for working with the loading stack
 * 
 */
public abstract class AbstractCacheHandle<T extends ZIPLoader> implements Cloneable{
	@NotNull
	@NonFinal
	private T loader;
	@Getter
	@Setter
	private Optional<T> cacheElementsOptional;
	
	public AbstractCacheHandle(@NotNull T loader) {
		
		
		this.loader = loader;
		
	}
	
	public AbstractCacheHandle<T> ifNotNull() {
		
		cache.forEach(cache -> {
			
			this.cacheElementsOptional = Optional.ofNullable(cache);
			
		});
		
		return this;
	}
	
	public abstract void write(@NotNull T element, @Nullable int index);
	
	public abstract void write(@NotNull T element);
	
	public abstract T read(@NotNull int index);
	
	public abstract ArrayList<T> readAll();
	
	
	public AbstractCacheHandle<T> actionLoader(ActionProvider provider) {
		
		provider.action(loader);
		
		return this;
	}
	
	public ZIPLoader getZIPLoader() {
		
		return this.loader;
		
	}
	
	public AbstractCacheHandle<T> clone(){
		try {
			return (AbstractCacheHandle<T>) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	
	@Getter
	private final Collection<T> cache = Collections.synchronizedCollection(new LinkedList<>());
	
	@FunctionalInterface
	interface ActionProvider {
		
	void action(ZIPLoader loader);
		
	}
	

}
