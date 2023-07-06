package net.kuama.nicoletti_test;


import net.kuama.nicoletti_test.modules.dtos.ItemDto;
import net.kuama.nicoletti_test.modules.utils.PackageBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Unit tests
 */
public class PackageBuilderTest {
  @Test
  public void example1Test() {
    BigDecimal maxWeight = BigDecimal.valueOf(75);

    ItemDto itemDto1 = new ItemDto();
    itemDto1.setId(1L);
    itemDto1.setWeight(BigDecimal.valueOf(85.31));
    itemDto1.setPrice(BigDecimal.valueOf(29));

    ItemDto itemDto2 = new ItemDto();
    itemDto2.setId(2L);
    itemDto2.setWeight(BigDecimal.valueOf(14.55));
    itemDto2.setPrice(BigDecimal.valueOf(74));

    ItemDto itemDto3 = new ItemDto();
    itemDto3.setId(3L);
    itemDto3.setWeight(BigDecimal.valueOf(3.98));
    itemDto3.setPrice(BigDecimal.valueOf(16));

    ItemDto itemDto4 = new ItemDto();
    itemDto4.setId(4L);
    itemDto4.setWeight(BigDecimal.valueOf(26.24));
    itemDto4.setPrice(BigDecimal.valueOf(55));

    ItemDto itemDto5 = new ItemDto();
    itemDto5.setId(5L);
    itemDto5.setWeight(BigDecimal.valueOf(63.69));
    itemDto5.setPrice(BigDecimal.valueOf(52));

    ItemDto itemDto6 = new ItemDto();
    itemDto6.setId(6L);
    itemDto6.setWeight(BigDecimal.valueOf(76.25));
    itemDto6.setPrice(BigDecimal.valueOf(75));

    ItemDto itemDto7 = new ItemDto();
    itemDto7.setId(7L);
    itemDto7.setWeight(BigDecimal.valueOf(60.02));
    itemDto7.setPrice(BigDecimal.valueOf(74));

    ItemDto itemDto8 = new ItemDto();
    itemDto8.setId(8L);
    itemDto8.setWeight(BigDecimal.valueOf(93.18));
    itemDto8.setPrice(BigDecimal.valueOf(35));

    ItemDto itemDto9 = new ItemDto();
    itemDto9.setId(9L);
    itemDto9.setWeight(BigDecimal.valueOf(89.95));
    itemDto9.setPrice(BigDecimal.valueOf(78));

    //arrange
    List<ItemDto> itemDtos = new ArrayList<>();
    itemDtos.add(itemDto1);
    itemDtos.add(itemDto2);
    itemDtos.add(itemDto3);
    itemDtos.add(itemDto4);
    itemDtos.add(itemDto5);
    itemDtos.add(itemDto6);
    itemDtos.add(itemDto7);
    itemDtos.add(itemDto8);
    itemDtos.add(itemDto9);


    //act
    List<ItemDto> result = PackageBuilder.build(itemDtos, maxWeight);
    BigDecimal combinedWeight = result.stream().map(ItemDto::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal combinedPrice = result.stream().map(ItemDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);


    //assert
    assertTrue("Items size match", result.size() == 2);
    assertTrue("Item 2 is present", result.contains(itemDto2));
    assertTrue("Item 7 is present", result.contains(itemDto7));

    assertTrue("Price match", combinedPrice.equals(BigDecimal.valueOf(148)));
    assertTrue("Weight match", combinedWeight.equals(BigDecimal.valueOf(74.57)));
  }

  @Test
  public void example2Test() {
    BigDecimal maxWeight = BigDecimal.valueOf(75);

    ItemDto itemDto1 = new ItemDto();
    itemDto1.setId(1L);
    itemDto1.setWeight(BigDecimal.valueOf(53.38));
    itemDto1.setPrice(BigDecimal.valueOf(45));

    ItemDto itemDto2 = new ItemDto();
    itemDto2.setId(2L);
    itemDto2.setWeight(BigDecimal.valueOf(88.62));
    itemDto2.setPrice(BigDecimal.valueOf(98));


    ItemDto itemDto3 = new ItemDto();
    itemDto3.setId(3L);
    itemDto3.setWeight(BigDecimal.valueOf(78.48));
    itemDto3.setPrice(BigDecimal.valueOf(3));

    ItemDto itemDto4 = new ItemDto();
    itemDto4.setId(4L);
    itemDto4.setWeight(BigDecimal.valueOf(72.30));
    itemDto4.setPrice(BigDecimal.valueOf(76));

    ItemDto itemDto5 = new ItemDto();
    itemDto5.setId(5L);
    itemDto5.setWeight(BigDecimal.valueOf(30.18));
    itemDto5.setPrice(BigDecimal.valueOf(9));

    ItemDto itemDto6 = new ItemDto();
    itemDto6.setId(6L);
    itemDto6.setWeight(BigDecimal.valueOf(76.25));
    itemDto6.setPrice(BigDecimal.valueOf(75));

    ItemDto itemDto7 = new ItemDto();
    itemDto7.setId(7L);
    itemDto7.setWeight(BigDecimal.valueOf(46.34));
    itemDto7.setPrice(BigDecimal.valueOf(48));

    //arrange
    List<ItemDto> itemDtos = new ArrayList<>();
    itemDtos.add(itemDto1);
    itemDtos.add(itemDto2);
    itemDtos.add(itemDto3);
    itemDtos.add(itemDto4);
    itemDtos.add(itemDto5);
    itemDtos.add(itemDto6);
    itemDtos.add(itemDto7);

    //act
    List<ItemDto> result = PackageBuilder.build(itemDtos, maxWeight);
    BigDecimal combinedWeight = result.stream().map(ItemDto::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal combinedPrice = result.stream().map(ItemDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);


    //assert
    assertTrue("Items size match", result.size() == 1);
    assertTrue("Item 4 is present", result.contains(itemDto4));

    assertTrue("Price match", combinedPrice.equals(itemDto4.getPrice()));
    assertTrue("Weight match", combinedWeight.equals(itemDto4.getWeight()));
  }

  @Test
  public void example3Test() {
    BigDecimal maxWeight = BigDecimal.valueOf(8);

    ItemDto itemDto1 = new ItemDto();
    itemDto1.setId(1L);
    itemDto1.setWeight(BigDecimal.valueOf(15.3));
    itemDto1.setPrice(BigDecimal.valueOf(34));

    List<ItemDto> itemDtos = new ArrayList<>();
    itemDtos.add(itemDto1);

    //act
    List<ItemDto> result = PackageBuilder.build(itemDtos, maxWeight);

    //assert
    assertTrue("Items size match", result.size() == 0);
  }

  @Test
  public void example4Test() {
    BigDecimal maxWeight = BigDecimal.valueOf(56);

    ItemDto itemDto1 = new ItemDto();
    itemDto1.setId(1L);
    itemDto1.setWeight(BigDecimal.valueOf(90.72));
    itemDto1.setPrice(BigDecimal.valueOf(13));

    ItemDto itemDto2 = new ItemDto();
    itemDto2.setId(2L);
    itemDto2.setWeight(BigDecimal.valueOf(33.80));
    itemDto2.setPrice(BigDecimal.valueOf(40));

    ItemDto itemDto3 = new ItemDto();
    itemDto3.setId(3L);
    itemDto3.setWeight(BigDecimal.valueOf(43.15));
    itemDto3.setPrice(BigDecimal.valueOf(10));

    ItemDto itemDto4 = new ItemDto();
    itemDto4.setId(4L);
    itemDto4.setWeight(BigDecimal.valueOf(37.97));
    itemDto4.setPrice(BigDecimal.valueOf(16));

    ItemDto itemDto5 = new ItemDto();
    itemDto5.setId(5L);
    itemDto5.setWeight(BigDecimal.valueOf(46.81));
    itemDto5.setPrice(BigDecimal.valueOf(36));

    ItemDto itemDto6 = new ItemDto();
    itemDto6.setId(6L);
    itemDto6.setWeight(BigDecimal.valueOf(48.77));
    itemDto6.setPrice(BigDecimal.valueOf(79));

    ItemDto itemDto7 = new ItemDto();
    itemDto7.setId(7L);
    itemDto7.setWeight(BigDecimal.valueOf(81.80));
    itemDto7.setPrice(BigDecimal.valueOf(45));

    ItemDto itemDto8 = new ItemDto();
    itemDto8.setId(8L);
    itemDto8.setWeight(BigDecimal.valueOf(19.36));
    itemDto8.setPrice(BigDecimal.valueOf(79));

    ItemDto itemDto9 = new ItemDto();
    itemDto9.setId(9L);
    itemDto9.setWeight(BigDecimal.valueOf(6.76));
    itemDto9.setPrice(BigDecimal.valueOf(64));

    //arrange
    List<ItemDto> itemDtos = new ArrayList<>();
    itemDtos.add(itemDto1);
    itemDtos.add(itemDto2);
    itemDtos.add(itemDto3);
    itemDtos.add(itemDto4);
    itemDtos.add(itemDto5);
    itemDtos.add(itemDto6);
    itemDtos.add(itemDto7);
    itemDtos.add(itemDto8);
    itemDtos.add(itemDto9);

    //act
    List<ItemDto> result = PackageBuilder.build(itemDtos, maxWeight);
    BigDecimal combinedWeight = result.stream().map(ItemDto::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal combinedPrice = result.stream().map(ItemDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);


    //assert
    assertTrue("Items size match", result.size() == 2);
    assertTrue("Item 8 is present", result.contains(itemDto8));
    assertTrue("Item 9 is present", result.contains(itemDto9));

    assertTrue("Price match", combinedPrice.equals(BigDecimal.valueOf(143)));
    assertTrue("Weight match", combinedWeight.equals(BigDecimal.valueOf(26.12)));
  }
}
