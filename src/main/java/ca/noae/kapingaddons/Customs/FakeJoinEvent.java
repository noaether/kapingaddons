package ca.noae.kapingaddons.Customs;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FakeJoinEvent extends Event {
  private final Player player;
  private String joinMessage;
  private static final HandlerList HANDLERS = new HandlerList();

  public FakeJoinEvent(Player player) {
      this.player = player;
  }

  public Player getPlayer() {
      return player;
  }

  public String getJoinMessage() {
      return joinMessage;
  }

  public void setJoinMessage(String joinMessage) {
      this.joinMessage = joinMessage;
  }

  @Override
  public HandlerList getHandlers() {
      return HANDLERS;
  }

  public static HandlerList getHandlerList() {
      return HANDLERS;
  }
}
