package br.com.gamemods.bukkitmod.command;

import br.com.gamemods.bukkitmod.server.PlugModServer;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;

public class PlugModConsoleCommandSender extends BasicCommandSender implements ConsoleCommandSender
{
    public PlugModConsoleCommandSender(PlugModServer server)
    {
        super(server);
    }

    @Override
    public void sendMessage(String s)
    {
        sendRawMessage(ChatColor.stripColor(s));
    }

    @Override
    public void sendRawMessage(String s)
    {
        System.out.println(s);
    }

    @Override
    public String getName()
    {
        return "CONSOLE";
    }

    @Override
    public boolean isConversing()
    {
        return false;
    }

    @Override
    public void acceptConversationInput(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean beginConversation(Conversation conversation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void abandonConversation(Conversation conversation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent)
    {
        throw new UnsupportedOperationException();
    }
}
