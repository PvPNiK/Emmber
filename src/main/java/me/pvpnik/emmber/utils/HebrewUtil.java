package me.pvpnik.emmber.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HebrewUtil {


    public static String getArgs(String[] args) {
        String a = "";
        if (!isHebrew(args[0])) {
            for (String str : args) {
                str = ChatColor.translateAlternateColorCodes('&', str).trim();
                if (!a.equalsIgnoreCase("")) {
                    a += " ";
                }
                a += str;
            }
            return a.trim();
        }
        for (int i = args.length - 1; i > -1; i--) {
            args[i] = ChatColor.translateAlternateColorCodes('&', args[i]).trim();
            if (!a.equalsIgnoreCase("") && (i != -1)) {
                if (i < args.length - 1 && ChatColor.stripColor(args[i + 1]).length() != 0) {
                    a = a + " ";
                }
            }
            if (isHebrew(args[i])) {
                for (int j = args[i].length() - 1; j > -1; j--) {
                    a = a + args[i].charAt(j);
                }
            } else {
                for (int j = 0; j < args[i].length(); j++) {
                    a = a + args[i].charAt(j);
                }
            }
        }
        return a.trim();
    }

    public static String removeHebrewLetters(String str) {
        while (isHebrew(str))
            for (int i = 0; i < str.length(); i++)
                if (isHebrew(str.charAt(i) + ""))
                    str = str.replace(str.charAt(i) + "", "");
        return str;
    }

    public static String reverseHebrew(String str) {
        if (!isHebrew(str))
            return str;
        if (str.contains(" ")) {
            return getArgs(str.split(" "));
        }
        String ret = "";
        for (int j = str.length() - 1; j > -1; j--) {
            ret = ret + str.charAt(j);
        }
        return ret.equalsIgnoreCase("") ? str.trim() : ret.trim();
    }

    public static boolean isHebrew(String str) {
        //if (str == null) return false;
        //return ComponentOrientation.getOrientation(new Locale(System.getProperty(str))).isLeftToRight();
        Pattern p = Pattern.compile("\\p{InHebrew}");
        Matcher m = p.matcher(str);
        return m.find();
    }

	/*public static boolean isHebrew(String str) {
		if (str == null) return false;
		if (str.isEmpty())
			return false;
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) >= 'א') && (str.charAt(i) <= 'ת')) {
				return true;
			}
		}
		return false;
	}*/

    public static String upperFirst(String str) {
        return str.substring(0, 1).toUpperCase().concat(str.substring(1).toLowerCase());
    }

}
