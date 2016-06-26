package me.hub.comandos.geral;




import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import me.acf.FormatText.Format;
import me.hub.Main;
import me.hub.API.Util.UtilNumber;
import me.hub.API.Util.message.Message;
import me.hub.comandos.ComandosAPI;
import me.site.account.Account;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

public class Coins implements CommandExecutor {

public String[] atalhos = new String[] { "c","planets","money" };
    public String desc = "Ver os coins";
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		   if (ComandosAPI.VerConsole(sender).equals("sim"))
		   {
			   sender.sendMessage(Message.Console_Não);
  			   return true;
		   }
		  
		   Player jogador = (Player) sender;
		   
	

		   if ((Account.getRank(jogador)).Has(jogador, Rank.DONO, false))
		   {
			   if (args.length  == 2)
				{
				   if (args[0].contains("reset"))
		            {
					  	try
		    		    {
		            		String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=RESET&nick=" + args[1], "OK");
		            		jogador.sendMessage("§6§lCOINS §7" + buscar);
		            		return true;
		    		    }
		                catch (Exception exception)
		    		    {

		                	String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=RESET&nick=" + args[1] + "&quantidade=" + args[2], "ERRO");
		                	  jogador.sendMessage("§c§lERRO §7" + buscar);
		                	  return true;
		    		    }
		            }
				}
		   }
			if (args.length  <= 2)
			{
				
				Format.Comando("coins", "Seus planets: " + UtilNumber.getNumber(Account.getCoins(jogador)), jogador);
				return true;
			}
			 if ((Account.getRank(jogador)).Has(jogador, Rank.DONO, false))
			   {
			if (args.length >= 3) {
	            if (args[0].contains("add"))
	            {
	            	try
	    		    {
	            		String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=ADD&nick=" + args[1] + "&quantidade=" + args[2], "OK");
	            		jogador.sendMessage("§6§lCOINS §7" + buscar);
	    		    }
	                catch (Exception exception)
	    		    {

	                	String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=ADD&nick=" + args[1] + "&quantidade=" + args[2], "ERRO");
	                	  jogador.sendMessage("§c§lERRO §7" + buscar);
	    		    }
	            }
	            else
	            if (args[0].contains("remove"))
	            {
	            	try
	    		    {
	            		String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=REMOVE&nick=" + args[1] + "&quantidade=" + args[2], "OK");
	            		jogador.sendMessage("§6§lCOINS §7" + buscar);
	    		    }
	                catch (Exception exception)
	    		    {

	                	String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=REMOVE&nick=" + args[1] + "&quantidade=" + args[2], "ERRO");
	                	  jogador.sendMessage("§c§lERRO §7" + buscar);
	    		    }
	    		    }
	            else
	            {
	            	jogador.sendMessage("§6COINS §aadd §7| §aremove §7 | §areset");
	            }
			
		   
			
		   }}
		
		return false;
	  }
	

	

}

