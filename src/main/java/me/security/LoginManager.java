/**
 * 
 */
package me.security;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.FormatText.Format;
import me.acf.FullPvP.CombatLog;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Util.UtilActionBar;
import me.hub.API.Util.UtilPlayer;
import me.hub.API.Util.UtilTitle;
import me.hub.Bungee.Bungee;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

/**
 * @author adriancf
 *
 */
public class LoginManager extends MiniPlugin {

	public static List<Player> registrar = new ArrayList<>(); 
	public static List<String> Original = new ArrayList<>(); 
	public LoginManager(JavaPlugin plugin)
	{
		super("Sistema de Login",plugin);
	}
	
   @EventHandler
   public void quit(PlayerQuitEvent event)
   {
	   event.setQuitMessage(null);

	   SecurityManager.sj.remove(event.getPlayer());
   }
	
	
   @EventHandler
   public void Chat(PlayerChatEvent event)
   {
	   event.setCancelled(true);
   }
   
	  @EventHandler
	  public void Comandos(PlayerCommandPreprocessEvent event) {
		  if (CombatLog.EstaEmCombat(event.getPlayer()))
		  {
			Format.Erro("Você esta em pvp com o §a" + CombatLog.combat.get(event.getPlayer()).getName(), event.getPlayer());
			  return;
		  }
	    if ((event.getMessage().toLowerCase().startsWith("/me")) || 
	      (event.getMessage().toLowerCase().startsWith("/bukkit")))
	    {
	      event.getPlayer().sendMessage("§e§lVocê não pode fazer isto..!");
	      event.setCancelled(true);
	    }
	    if (event.getMessage().toLowerCase().startsWith("/ping"))
	    {
	      event.getPlayer().sendMessage("§e§lPong..");
	      event.setCancelled(true);
	    }
	    if (event.getMessage().toLowerCase().startsWith("/pong"))
	    {
	      event.getPlayer().sendMessage("§e§lPing..");
	      event.setCancelled(true);
	    }
	    if (event.getMessage().toLowerCase().startsWith("/ms"))
	    {
	      event.getPlayer().sendMessage("§f§lMS §6§l" + UtilPlayer.Ping(event.getPlayer()));
	      event.setCancelled(true);
	    }
	   
	  }
   
	  @EventHandler
	   public void BlockBurn(BlockBurnEvent event)
	   {
	       event.setCancelled(true);
	   }
	   
	   @EventHandler
	   public void BlockFade(BlockFadeEvent event) {
	       event.setCancelled(true);    
	   }
	   
	   @EventHandler
	   public void BlockSpread(BlockSpreadEvent event) {
	       event.setCancelled(true);
	   }
	   
	   @EventHandler(priority=EventPriority.LOW)
	   public void playerPortalEvent(PlayerPortalEvent event)
	   {
	     event.setCancelled(true);
	   }
	   @EventHandler(priority=EventPriority.LOW)
	   public void entityPortalEvent(EntityPortalEvent event) {
	     event.setCancelled(true);
	   }

	   @EventHandler
	   public void ItemSpawnEvent(final PlayerDropItemEvent event)
	   {
		   event.setCancelled(true);   
	   }
	   @EventHandler
	   public void ItemSpawnEvent(final ItemSpawnEvent event)
	   {
		   event.setCancelled(true);
	   }
	   @EventHandler
	   public void Pickup(PlayerPickupItemEvent event)
	   {
	   
	       event.setCancelled(true);
	     
	   }
	   
	   @EventHandler
	   public void HopperPickup(InventoryPickupItemEvent event) {
     event.setCancelled(true);	     
	   }
	   
	   @EventHandler
	   public void BlockBreak(BlockBreakEvent event) {
	     event.setCancelled(true);
	   }

	   @EventHandler(priority=EventPriority.LOWEST)
	   public void Explosion(EntityExplodeEvent event) {
	     event.setCancelled(true);
	   }

	   @EventHandler
	   public void VineGrow(BlockSpreadEvent event) {
	     event.setCancelled(true);
	   }

	   @EventHandler
	   public void BlockForm(BlockFormEvent event) {
	     event.setCancelled(true);
	   }

	   @EventHandler
	   public void LeaveDecay(LeavesDecayEvent event) {
	     event.setCancelled(true);
	   }

	   @EventHandler
	   public void Fome(FoodLevelChangeEvent event)
	   {
	 	  Player jogador = (Player)event.getEntity();
	 	  jogador.setFoodLevel(2000);
	 	  event.setCancelled(true);  
	   }
	   
	   @EventHandler(priority=EventPriority.HIGH)
	   public void SemDano(EntityDamageEvent event) {
		   event.setCancelled(true);
	   }
	   
	   @EventHandler
		public void removeBlock(EntityChangeBlockEvent e) {
				e.setCancelled(true);	
		}
	   @EventHandler
		public void interagir(PlayerInteractEvent e) {
		   e.setCancelled(true);
	   }
	@EventHandler
	public void Join (final PlayerJoinEvent event)
	{
	 String resultado = AccountWeb.Conectar(Main.site + "/API/uuid.php?nick=" + event.getPlayer().getName());
	event.setJoinMessage(null);
	
	String Servidor = AccountWeb.Conectar(Main.site + "/API/sala.php?modo=CONSUTAR_ON&nome=lobby", "status");
	if (Servidor == null)
	{
		 if (!Account.getRank(event.getPlayer()).Has(event.getPlayer(), Rank.STAFF, false))
	      {
	       UtilPlayer.Kick(event.getPlayer(), "Lobby não esta online");
		return;
	      }
	}
	
	for (Player p : Bukkit.getOnlinePlayers())
	{
		event.getPlayer().hidePlayer(p);
		p.hidePlayer(event.getPlayer());
	}
	event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
	if (resultado.contains("PIRATA"))
	     {
		 SecurityManager.Add(event.getPlayer());
		 String verificar = AccountWeb.Conectar(Main.site + "/API/registrado.php?nick=" + event.getPlayer().getName(), "registrado");
		 if (!verificar.contains("sim"))
		 {
	    UtilTitle.sendTitle(event.getPlayer(),20,20,20,"&f&lSeja Bem-Vindo ao servidor de espera!","&6&lVocê deve se registrar");
		 registrar.add(event.getPlayer()); 
		 }
		 else
		 {
			 UtilTitle.sendTitle(event.getPlayer(),20,20,20,"&f&lSeja Bem-Vindo ao servidor de espera!","&6&lVocê deve se logar");
		 }
		 SecurityManager.Add(event.getPlayer());
	 }
	 else
	 {
		    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
		     {
		       public void run()
		       {
		    	   if (event.getPlayer() == null)
		    		   return;
		    	   if (!event.getPlayer().isOnline())
		    		   return;
		    	   if (Original.contains(event.getPlayer().getUniqueId().toString()))
		    		   return;
				   UtilActionBar.ActionBar(event.getPlayer(), "§c§lSeja Bem-Vindo Original!");
				   event.getPlayer().sendMessage("§c§lSeja Bem-Vindo original!");
				Bungee.SendPlayerToServer(event.getPlayer(), "lobby"); 
				Original.add(event.getPlayer().getUniqueId().toString());
		       }
		       }, 50L);
	 }
	}
	
	@EventHandler
	public void RemoveLimbo(Atualizar event)
	{
		if (event.getType() == ModosUpdate.MIN_02)
		{
			
				Original.clear();
			}
		
	}
	
	@EventHandler
	public void update(Atualizar event)
	{
		if (event.getType() == ModosUpdate.FASTEST)
		{
			for (final Player p : Bukkit.getOnlinePlayers())
			{
				p.hidePlayer(p);
				p.setGameMode(GameMode.ADVENTURE);
				if (Original.contains(p.getUniqueId().toString()))
				{
					   UtilActionBar.ActionBar(p, "§e§lVOCÊ SAIU ??? ENTÃO USE §c§l/limbo");
					   Bungee.SendPlayerToServer(p, "lobby");
				}
			}
			for (Player p : SecurityManager.sj)
			{
		
				if (registrar.contains(p)) {
			   
			    UtilActionBar.ActionBar(p, "§e§lPARA SE REGISTRAR NO SERVIDOR USE §6§l/registrar (Senha) (E-Mail)");
				}
			    else {
			    
					UtilActionBar.ActionBar(p, "§e§lPARA SE LOGAR NO SERVIDOR USE §6§l/logar (Senha) (E-Mail)");
			
			    }
			    }
		}
	}
	
	
	
}
