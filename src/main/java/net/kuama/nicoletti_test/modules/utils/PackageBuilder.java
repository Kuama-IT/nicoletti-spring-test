package net.kuama.nicoletti_test.modules.utils;

import net.kuama.nicoletti_test.modules.dtos.ItemDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PackageBuilder {

  public static List<ItemDto> build(List<ItemDto> items, BigDecimal maxWeight) {
    // Firstly we sort the items by price and if necessary by weight
    items.sort((previous, next) -> {
      // Sorted Descending
      int priceCompare = next.getPrice().compareTo(previous.getPrice());

      if (priceCompare == 0) {
        // Sorted Ascending
        return previous.getWeight().compareTo(next.getWeight());
      }

      return priceCompare;
    });

    List<ItemDto> resultingItems = new ArrayList<>();
    BigDecimal currentWeight = BigDecimal.ZERO;

    for (ItemDto currentItem : items) {
      BigDecimal nextCurrentWeight = currentWeight.add(currentItem.getWeight());

      if (nextCurrentWeight.compareTo(maxWeight) <= 0) {
        resultingItems.add(currentItem);
        currentWeight = currentWeight.add(currentItem.getWeight());
      }
    }

    return resultingItems;
  }
}
