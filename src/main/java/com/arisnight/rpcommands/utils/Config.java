package com.arisnight.rpcommands.utils;

import com.arisnight.rpcommands.RPCommands;
import com.arisnight.rpcommands.commands.TryCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public enum Config {

    Message_Radius("Message-radius",50,"radius in which rp actions in chat will be visible / радиус в котором будут видны рп действия в чате"),
    TryCommandSuccessful("Try-Successful","&aУдачно&7","Successful output of the result of the /try command / Вывод результата команды /try"),
    TryCommandUnsuccessful("Try-Unsuccessful","&cНеудачно&7","Unsuccessful output of the result of the /try command / Вывод результата команды /try"),
    TryCommandFormat("Try-Format","&d* &7%s %s [%s]","Try command output format / Формат вывода команды /try"),
    DoCommandFormat("Do-Format", "&e* &7%s &8(%s)","Do command output format / Формат вывода команды /do"),
    MeCommandFormat("Me-Format", "&6* &7%s %s","Me command output format / Формат вывода команды /me");


    private static final File f = new File(RPCommands.getInstance().getDataFolder(), "config.yml");
    private static YamlConfiguration cfg;
    private final Object value;
    private final String path;
    private final String description;

    Config(String path, Object val, String description) {
        this.path = path;
        this.value = val;
        this.description = description;
    }

    public static void load() {
        RPCommands.getInstance().getDataFolder().mkdirs();
        reload(false);
        List<String> header = new ArrayList<>();
        header.add("");
        header.add("Спасибо за загрузку плагина RPCommandsExpanded / Thank you for downloading the RPCommandsExpanded plugin.");
        header.add("Этот плагин является форком плагина RPCommands автора ArisNight");
        header.add("Создан, чтобы закрыть недочёты автора оригинала по мнению VVrongg");
        header.add("");
        for (Config c : values()) {
            header.add(c.getPath() + ": " + c.getDescription());
            if (!cfg.contains(c.getPath())) {
                c.set(c.getDefaultValue(), false);
            }
        }
        try {
            cfg.options().setHeader(header);
        } catch (NoSuchMethodError e) {
            String headerString = "";
            for (String s : header) {
                headerString += s + System.lineSeparator();
            }
            cfg.options().header(headerString);
        }
        try {
            cfg.save(f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void reload(boolean complete) {
        if (!complete) {
            cfg = YamlConfiguration.loadConfiguration(f);
            return;
        }
        load();
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public Object getDefaultValue() {
        return value;
    }

    public boolean getBoolean() {
        return cfg.getBoolean(path);
    }

    public double getDouble() {
        return cfg.getDouble(path);
    }

    public int getInt() {
        return cfg.getInt(path);
    }

    public String getString() {
        return cfg.getString(path);
    }

    public List<String> getStringList() {
        return cfg.getStringList(path);
    }

    public ConfigurationSection getConfigurationSection() {
        return cfg.getConfigurationSection(path);
    }

    public void set(Object value, boolean save) {
        cfg.set(path, value);
        if (save) {
            try {
                cfg.save(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            reload(false);
        }
    }
}
