package ca.noae.kapingaddons.Customs;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FakeLeaveEvent extends Event {
  private final Player player;
  private String leaveMessage;
  private static final HandlerList HANDLERS = new HandlerList();

  public FakeLeaveEvent(Player player) {
      this.player = player;
  }

  public Player getPlayer() {
      return player;
  }

  public String getLeaveMessage() {
      return leaveMessage;
  }

  public void setLeaveMessage(String leaveMessage) {
      this.leaveMessage = leaveMessage;
  }

  @Override
  public HandlerList getHandlers() {
      return HANDLERS;
  }

  public static HandlerList getHandlerList() {
      return HANDLERS;
  }
}
