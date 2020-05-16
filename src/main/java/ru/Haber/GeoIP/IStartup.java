package ru.Haber.GeoIP;



import lombok.NonNull;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public interface IStartup<MAIN extends Plugin> {

	
	void onEnable();
	
	void onDisable();
	
	@NonNull
	IStartup<MAIN> registerInstance();
	
	
	default void installEvents(@NonNull Listener ev, @NonNull IStartup<MAIN> plugin){
	
		ProxyServer.getInstance().getPluginManager().registerListener((Plugin) plugin, ev);
		
	}
	
	default void registerCommands(@NonNull Command cmd,@NonNull IStartup<MAIN> plugin) {
		ProxyServer.getInstance().getPluginManager().registerCommand((Plugin) plugin, cmd);
	}
	
	default void unRegisterCommands(@NonNull IStartup<MAIN> plugin) {
		ProxyServer.getInstance().getPluginManager().unregisterCommands((Plugin) plugin);
	}
	
	
}
