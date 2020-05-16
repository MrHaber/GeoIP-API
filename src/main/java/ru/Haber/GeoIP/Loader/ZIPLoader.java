package ru.Haber.GeoIP.Loader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ZIPLoader {

	Runnable load(@NotNull String url, @NotNull String path, @Nullable String name);
	
	void start(Runnable loader);
	
	String getURL();
	
	String getPath();
	
	String getName();
	
}
