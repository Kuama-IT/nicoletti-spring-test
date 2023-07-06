package net.kuama.nicoletti_test.modules.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDto {
  @NotNull(message = "Item Id cannot be null")
  private Long id;

  @NotNull(message = "Item weight cannot be null")
  @DecimalMin(value = "0.0")
  @DecimalMax(value = "100.0")
  private BigDecimal weight;

  @DecimalMin(value = "0.0")
  @DecimalMax(value = "100.0")
  @NotNull(message = "Item price cannot be null")
  private BigDecimal price;
}
