package br.com.gamemods.bukkitmod.command;

import br.com.gamemods.bukkitmod.server.PlugModServer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.*;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public abstract class BasicCommandSender implements CommandSender
{
    protected final PlugModServer server;
    private PermissibleBase permissible;
    private boolean operator;

    public BasicCommandSender(PlugModServer server)
    {
        this.server = server;
        this.permissible = new PermissibleBase(this);
    }

    public BasicCommandSender(PlugModServer server, ServerOperator operator)
    {
        this.server = server;
        this.permissible = new PermissibleBase(operator);
    }

    @Override
    public void sendMessage(String[] messages)
    {
        for(String msg: messages)
            sendMessage(msg);
    }

    @Override
    public Server getServer()
    {
        return server;
    }

    @Override
    public boolean isPermissionSet(String name)
    {
        return permissible.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm)
    {
        return permissible.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String name)
    {
        return permissible.hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm)
    {
        return permissible.hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value)
    {
        return permissible.addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin)
    {
        return permissible.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks)
    {
        return permissible.addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks)
    {
        return permissible.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment)
    {
        permissible.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions()
    {
        permissible.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions()
    {
        return permissible.getEffectivePermissions();
    }

    @Override
    public boolean isOp()
    {
        return operator;
    }

    @Override
    public void setOp(boolean value)
    {
        operator = value;
    }
}
