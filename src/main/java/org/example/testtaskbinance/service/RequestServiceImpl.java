package org.example.testtaskbinance.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.testtaskbinance.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    private static final String BINANCE_API_URL = "https://fapi.binance.com/fapi/v1/premiumIndex?symbol=";

    @Override
    public Object sendGetRequestToGetCryptoPrice(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(BINANCE_API_URL + (symbol != null ? symbol : ""), String.class);

        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Response.class);
        try {
            if (symbol != null) {
                Response cryptoResponse = mapper.readValue(response.getBody(), Response.class);
                return cryptoResponse.getMarkPrice();
            } else {
                List<Response> cryptoResponses = mapper.readValue(response.getBody(), type);
                return cryptoResponses.stream().collect(Collectors.toMap(Response::getSymbol, Response::getMarkPrice));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
