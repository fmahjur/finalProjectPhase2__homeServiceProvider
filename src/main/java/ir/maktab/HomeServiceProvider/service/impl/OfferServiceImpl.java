package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Orders;
import ir.maktab.HomeServiceProvider.data.repository.OfferRepository;
import ir.maktab.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.HomeServiceProvider.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Override
    public Offer add(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public void remove(Offer offer) {
        offer.setDeleted(true);
        offerRepository.save(offer);
    }

    @Override
    public Offer update(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer findById(Long id) {
        return offerRepository.findById(id).orElseThrow(()->new NotFoundException("not found"));
    }

    @Override
    public List<Offer> selectAll() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> selectAllAvailableService() {
        return offerRepository.findAllByDeletedIs(false);
    }

    @Override
    public List<Offer> selectAllByOrder(Orders orders) {
        List<Offer> allOfferForOrder = offerRepository.findAllByOrdersIs(orders);
        Collections.sort(allOfferForOrder, new Comparator() {
            public int compare(Object o1, Object o2) {
                Double x1 = ((Offer) o1).getOfferPrice();
                Double x2 = ((Offer) o2).getOfferPrice();
                int compare = x1.compareTo(x2);
                if (compare != 0) {
                    return compare;
                }

                Double y1 = ((Offer) o1).getExpert().getRate();
                Double y2 = ((Offer) o2).getExpert().getRate();
                return y1.compareTo(y2);
            }
        });
        return allOfferForOrder;
    }

    public List<Offer> selectAllByExpert(Expert expert) {
        return offerRepository.findAllByExpert(expert);
    }
}
