package aikamapp.service;

import aikamapp.controller.stat.PurchaseStat;
import aikamapp.controller.stat.BuyerStat;
import aikamapp.controller.stat.TotalStat;
import aikamapp.dao.BuyerDao;
import aikamapp.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service("buyerService")
@Transactional
public class BuyerService {
    @Autowired
    private BuyerDao buyerDao;

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

    public TotalStat getTotalStat(LocalDate startDate, LocalDate endDate){
        List<PurchaseStat> purchases = buyerDao.getPurchaseStats(startDate, endDate);
        Map<Buyer, BuyerStat> buyerStats = new HashMap<>();
        BigDecimal totalCost = new BigDecimal(0);
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
        BigDecimal avgCost = new BigDecimal(0);
        int countBuyer = buyerStats.keySet().size();
        if ( countBuyer > 0){
            avgCost = totalCost.divide(BigDecimal.valueOf(countBuyer), 2, RoundingMode.HALF_UP);
        }

        return new TotalStat(totalDays, new HashSet<>(buyerStats.values()), totalCost, avgCost);
    }

}
