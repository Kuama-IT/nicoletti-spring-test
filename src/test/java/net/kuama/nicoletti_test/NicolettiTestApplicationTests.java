package net.kuama.nicoletti_test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.kuama.nicoletti_test.modules.dtos.ItemDto;
import net.kuama.nicoletti_test.modules.dtos.ItemsToPackageAndMaxWeightDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests
 */
@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
class NicolettiTestApplicationTests {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc restAuditMockMvc;

  @Test
  void givesBadRequestWhenEmptyResultTest() throws Exception {
    ItemDto itemDto1 = new ItemDto();
    itemDto1.setId(1L);
    itemDto1.setWeight(BigDecimal.valueOf(15.3));
    itemDto1.setPrice(BigDecimal.valueOf(34));

    List<ItemDto> itemDtos = new ArrayList<>();
    itemDtos.add(itemDto1);

    ItemsToPackageAndMaxWeightDto dto = new ItemsToPackageAndMaxWeightDto();
    dto.setMaximumPackageWeight(BigDecimal.valueOf(8));
    dto.setItems(itemDtos);

    String payload = convertToJson(dto);

    restAuditMockMvc.perform(post("/build-package")
            .contentType(MediaType.APPLICATION_JSON).content(payload))
        .andExpect(status().isBadRequest());
  }

  @Test
  void correctInputShouldReturnCorrectResult() throws Exception {
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

    ItemsToPackageAndMaxWeightDto dto = new ItemsToPackageAndMaxWeightDto();
    dto.setMaximumPackageWeight(BigDecimal.valueOf(75));
    dto.setItems(itemDtos);

    String payload = convertToJson(dto);

    MvcResult response = restAuditMockMvc.perform(post("/build-package")
            .contentType(MediaType.APPLICATION_JSON).content(payload))
        .andExpect(status().isOk()).andReturn();

    List<Long> result = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<Long>>() {
    });

    assertTrue("Result has 1 item", result.size() == 1);
    assertTrue("Result has Id 4", result.get(0) == 4L);



  }

  private String convertToJson(ItemsToPackageAndMaxWeightDto dto) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer();
    return ow.writeValueAsString(dto);
  }

}
