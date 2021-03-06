package aikamapp.service;

import aikamapp.controller.stat.PurchaseStat;
import aikamapp.controller.stat.BuyerStat;
import aikamapp.controller.stat.TotalStat;
import aikamapp.dao.BuyerDao;
import aikamapp.model.Buyer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@Service("buyerService")
@Transactional
public class BuyerService {
    private final BuyerDao buyerDao;

    public BuyerService(BuyerDao buyerDao) {
        this.buyerDao = buyerDao;
    }

    public Buyer get(Long id){
        return buyerDao.get(id);
    }

    public List<Buyer> getAll(){
        return buyerDao.getAll();
    }

    public List<Buyer> getByLastname(String lastname){
        return buyerDao.getByLastname(lastname);
    }

    public List<Buyer> getByGoodAndCountOfPurchases(String goodsTitle, int count){
        return buyerDao.getByGoodAndCountOfPurchases(goodsTitle, count);
    }

    public List<Buyer> getByCostOfPurchases(BigDecimal minCost, BigDecimal maxCost){
        return buyerDao.getByCostOfPurchases(minCost, maxCost);
    }
    public List<Buyer> getPassiveBuyers(int limit){
        return buyerDao.getPassiveBuyers(limit);
    }

    public TotalStat getTotalStat(String operation, LocalDate startDate, LocalDate endDate){
        List<PurchaseStat> purchases = buyerDao.getPurchaseStats(startDate, endDate);
        Map<Buyer, BuyerStat> buyerStats = new HashMap<>();
        BigDecimal totalCost = BigDecimal.ZERO;
        for(PurchaseStat purchaseStat : purchases){
            if (buyerStats.containsKey(purchaseStat.getBuyer())){
                BuyerStat buyerStat = buyerStats.get(purchaseStat.getBuyer());
                buyerStat.addSale(purchaseStat.getGoodsSale());
            } else {
                buyerStats.put(purchaseStat.getBuyer(), new BuyerStat(purchaseStat.getBuyer()));
            }
            totalCost = totalCost.add(purchaseStat.getGoodsSale().getCost());
        }
        int totalDays = buyerDao.getNumberOfDays(startDate, endDate);
        BigDecimal avgCost = BigDecimal.ZERO;
        int countBuyer = buyerStats.keySet().size();
        if ( countBuyer > 0){
            avgCost = totalCost.divide(BigDecimal.valueOf(countBuyer), 2, RoundingMode.HALF_UP);
        }

        return new TotalStat(operation, totalDays, new HashSet<>(buyerStats.values()), totalCost, avgCost);
    }
}
