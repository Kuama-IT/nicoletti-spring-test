package net.kuama.nicoletti_test.modules.services;

import net.kuama.nicoletti_test.modules.utils.PackageBuilder;
import net.kuama.nicoletti_test.exceptions.EmptyResultinPackageException;
import net.kuama.nicoletti_test.modules.dtos.ItemDto;
import net.kuama.nicoletti_test.modules.dtos.ItemsToPackageAndMaxWeightDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService {

  public List<Long> buildPackageOrThrow(ItemsToPackageAndMaxWeightDto item) throws EmptyResultinPackageException {
    List<ItemDto> result = PackageBuilder.build(item.getItems(), item.getMaximumPackageWeight());

    if (result.isEmpty()) {
      throw new EmptyResultinPackageException();
    }

    return result.stream().map(ItemDto::getId).collect(Collectors.toList());
  }
}
