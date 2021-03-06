package org.appledash.saneeconomysignshop.util;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by appledash on 8/3/16.
 * Blackjack is still best pony.
 */
public class ItemDatabase {
    private static Map<String, Pair<Integer, Short>> itemMap = new HashMap<>();

    public static void initItemDB() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ItemDatabase.class.getResourceAsStream("/items.csv")))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || !line.contains(",")) {
                    continue;
                }

                String[] split = line.split(",");
                String name = split[0];
                int id = Integer.valueOf(split[1]);
                short damage = Short.valueOf(split[2]);

                itemMap.put(name.toLowerCase(), Pair.of(id, damage));
            }

            itemMap = ImmutableMap.copyOf(itemMap);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static Optional<Pair<Integer, Short>> getIDAndDamageForName(String name) {
        if (Material.getMaterial(name) != null) {
            return Optional.of(Pair.of(Material.getMaterial(name).getId(), (short) 0));
        }
        return Optional.ofNullable(itemMap.get(name.toLowerCase()));
    }

    public static ItemStack parseGive(String rawItemName) throws InvalidItemException {
        String itemName;
        short damage;

        if (rawItemName.contains(":")) {
            String[] splitItemName = rawItemName.split(":");
            itemName = splitItemName[0];
            if (splitItemName.length == 1) { // They just typed 'tnt:'
                damage = 0;
            } else { // They typed 'tnt:something'
                try {
                    damage = Short.valueOf(splitItemName[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidItemException("Damage value must be a number.");
                }
            }
        } else { // No damage value
            itemName = rawItemName;
            damage = 0;
        }

        Optional<Material> materialOptional = parseMaterialFromName(itemName);

        if (!materialOptional.isPresent()) {
            Optional<Pair<Integer, Short>> parsedItem = getIDAndDamageForName(normalizeItemName(itemName));
            if (!parsedItem.isPresent()) {
                throw new InvalidItemException("Item by that name does not exist.");
            }

            if (damage == 0) {
                damage = parsedItem.get().getRight();
            }

            return new ItemStack(parsedItem.get().getLeft(), 1, damage);
        }

        return new ItemStack(materialOptional.get(), 1, damage);

    }

    private static Optional<Material> parseMaterialFromName(String materialName) {
        // Try to parse an integral item ID first, for legacy reasons.
        try {
            return Optional.ofNullable(Material.getMaterial(Integer.valueOf(materialName)));
        } catch (NumberFormatException ignored) { }

        for (Material mat : Material.values()) {
            if (normalizeItemName(mat.name()).equals(normalizeItemName(materialName))) {
                return Optional.of(mat);
            }
        }

        return Optional.empty();
    }

    private static String normalizeItemName(String itemName) {
        return itemName.toLowerCase().replace("_", "").replace(" ", "");
    }

    public static class InvalidItemException extends Exception {
        public InvalidItemException(String message) {
            super(message);
        }
    }
}
