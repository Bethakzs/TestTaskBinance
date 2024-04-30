package org.example.testtaskbinance.controller;

import lombok.RequiredArgsConstructor;
import org.example.testtaskbinance.service.RequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/crypto")
    public Object getCryptoPrice(@RequestParam(required = false) String symbol) {
        return requestService.sendGetRequestToGetCryptoPrice(symbol);
    }
}
