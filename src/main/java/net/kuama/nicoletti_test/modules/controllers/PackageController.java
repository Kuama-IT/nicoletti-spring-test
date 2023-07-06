package net.kuama.nicoletti_test.modules.controllers;

import jakarta.validation.Valid;
import net.kuama.nicoletti_test.exceptions.EmptyResultinPackageException;
import net.kuama.nicoletti_test.modules.dtos.ItemsToPackageAndMaxWeightDto;
import net.kuama.nicoletti_test.modules.services.PackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PackageController {
  private final String buildPackageRoute = "/build-package";

  private final PackageService packageService;

  public PackageController(PackageService packageService) {
    this.packageService = packageService;
  }

  @PostMapping(buildPackageRoute)
  public ResponseEntity<List<Long>> buildPackage(@Valid @RequestBody ItemsToPackageAndMaxWeightDto item) {
    try {
      List<Long> result = packageService.buildPackageOrThrow(item);
      return ResponseEntity
          .status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(result);

    } catch (EmptyResultinPackageException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
}
