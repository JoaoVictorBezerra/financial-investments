package tech.projects.financialinvestments.service;

import org.springframework.stereotype.Service;
import tech.projects.financialinvestments.entity.BillingAddress;
import tech.projects.financialinvestments.repository.BillingAddressRepository;

@Service
public class BillingAddressService {
    private final BillingAddressRepository billingAddressRepository;

    public BillingAddressService(BillingAddressRepository billingAddressRepository) {
        this.billingAddressRepository = billingAddressRepository;
    }

    public void createBillingAddress(BillingAddress billingAddress) {
        billingAddressRepository.save(billingAddress);
    }
}
