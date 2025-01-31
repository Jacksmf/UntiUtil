package com.untistore.utils.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String profanityFilter(String message) {
        List<String> profanitiesWords = List.of("fuck");

        String messageToLowerCase = message;

        for (String word : profanitiesWords) {
            // Use regex to find the profane word and replace only the matched part
            messageToLowerCase = messageToLowerCase.replaceAll("(?i)" + word, "*".repeat(word.length()));
        }

        return messageToLowerCase;
    }

    public static String filterLinks(String message) {
        List<String> allowedLinks = List.of("discord.com");

        String urlRegex = "(https?:\\/\\/|www\\.)[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}[^\\s]*";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(message);

        StringBuffer filteredMessage = new StringBuffer();

        while (matcher.find()) {
            String foundUrl = matcher.group();

            boolean isAllowed = allowedLinks.stream().anyMatch(foundUrl::contains);

            if (!isAllowed) {
                matcher.appendReplacement(filteredMessage, "***");
            }
        }

        matcher.appendTail(filteredMessage);
        return filteredMessage.toString();
    }

    public static String filterColorCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String filterPlaceholders(String message, Player player) {
        message = message.replace("%player_name%", player.getName());
        message = message.replace("%online%", String.valueOf(player.getServer().getOnlinePlayers().size()));
        message = message.replace("%max%", String.valueOf(player.getServer().getMaxPlayers()));
        return message;
    }

    public static String filterAll(String message) {
        String profanityFiltered = profanityFilter(message);
        String linksFiltered = filterLinks(profanityFiltered);
        return filterColorCodes(linksFiltered);
    }
}
