package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Order;
import ir.maktab.HomeServiceProvider.data.repository.OfferRepository;
import ir.maktab.HomeServiceProvider.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Override
    public void add(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public void delete(Offer offer) {
        offer.setDeleted(true);
        offerRepository.save(offer);
    }

    @Override
    public void update(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public List<Offer> selectAll() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> selectAllByOrder(Order order) {
        return offerRepository.findAllByOrder(order);
    }

    public List<Offer> selectAllByExpert(Expert expert) {
        return offerRepository.findAllByExpert(expert);
    }
}
