package com.gmail.lynx7478.remotestop.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class RemoteStop extends JavaPlugin implements PluginMessageListener {
	
	@Override
	public void onEnable()
	{
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
	}

	@Override
	public void onPluginMessageReceived(String channel, Player p, byte[] data) 
	{
		if(!channel.equals("BungeeCord"))
		{
			return;
		}
		
		ByteArrayDataInput in = ByteStreams.newDataInput(data);
		if(in.readUTF().equals("Stop"))
		{
			this.getServer().shutdown();
		}
	}

}
