package org.appledash.saneeconomy.vault;

import com.google.common.collect.ImmutableList;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.appledash.saneeconomy.SaneEconomy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;

/**
 * Created by AppleDash on 6/14/2016.
 * Blackjack is still best pony.
 */
public class EconomySaneEconomy implements Economy {
    @Override
    public boolean isEnabled() {
        return SaneEconomy.getInstance().isEnabled();
    }

    @Override
    public String getName() {
        return "SaneEconomy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return SaneEconomy.getInstance().getEconomyManager().getCurrency().getFormat().getMaximumFractionDigits();
    }

    @Override
    public String format(double v) {
        return SaneEconomy.getInstance().getEconomyManager().getCurrency().formatAmount(v);
    }

    @Override
    public String currencyNamePlural() {
        return SaneEconomy.getInstance().getEconomyManager().getCurrency().getPluralName();
    }

    @Override
    public String currencyNameSingular() {
        return SaneEconomy.getInstance().getEconomyManager().getCurrency().getSingularName();
    }

    @Override
    public boolean hasAccount(String playerName) {
        return SaneEconomy.getInstance().getEconomyManager().accountExists(Bukkit.getServer().getPlayer(playerName));
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return SaneEconomy.getInstance().getEconomyManager().accountExists(offlinePlayer);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String worldName) {
        return hasAccount(offlinePlayer);
    }

    @Override
    public double getBalance(String s) {
        return SaneEconomy.getInstance().getEconomyManager().getBalance(Bukkit.getPlayer(s));
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return SaneEconomy.getInstance().getEconomyManager().getBalance(offlinePlayer);
    }

    @Override
    public double getBalance(String playerName, String worldName) {
        return getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String worldName) {
        return getBalance(offlinePlayer);
    }

    @Override
    public boolean has(String playerName, double v) {
        return SaneEconomy.getInstance().getEconomyManager().hasBalance(Bukkit.getPlayer(playerName), v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return SaneEconomy.getInstance().getEconomyManager().hasBalance(offlinePlayer, v);
    }

    @Override
    public boolean has(String playerName, String worldName, double v) {
        return has(playerName, v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String worldName, double v) {
        return has(offlinePlayer, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double v) {
        return new EconomyResponse(v, SaneEconomy.getInstance().getEconomyManager().subtractBalance(Bukkit.getPlayer(playerName), v), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        return new EconomyResponse(v, SaneEconomy.getInstance().getEconomyManager().subtractBalance(offlinePlayer, v), EconomyResponse.ResponseType.SUCCESS, null);

    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double v) {
        return withdrawPlayer(playerName, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return withdrawPlayer(offlinePlayer, v);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double v) {
        return new EconomyResponse(v, SaneEconomy.getInstance().getEconomyManager().addBalance(Bukkit.getPlayer(playerName), v), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        return new EconomyResponse(v, SaneEconomy.getInstance().getEconomyManager().addBalance(offlinePlayer, v), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double v) {
        return depositPlayer(playerName, v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String worldName, double v) {
        return depositPlayer(offlinePlayer, v);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
    }

    @Override
    public List<String> getBanks() {
        return ImmutableList.of();
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return true;
    }
}
