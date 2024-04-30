package org.example.testtaskbinance.service;

import org.springframework.stereotype.Service;

@Service
public interface RequestService {
    Object sendGetRequestToGetCryptoPrice(String symbol);
}
