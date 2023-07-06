package net.kuama.nicoletti_test.modules.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemsToPackageAndMaxWeightDto {
  @NotNull(message = "Item weight cannot be null")
  @DecimalMin(value = "0.0", message = "should be greater than 0.0")
  @DecimalMax(value = "100.0", message = "should be smaller than 100.0")
  @Valid
  @JsonProperty
  private BigDecimal maximumPackageWeight;

  @NotNull
  @Size(min = 1, max = 15, message = "Expected items between 1 and 15 (inclusive)")
  @Valid
  @JsonProperty
  private List<ItemDto> items;
}
