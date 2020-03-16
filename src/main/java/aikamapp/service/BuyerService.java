package aikamapp.service;

import aikamapp.dao.BuyerDao;
import aikamapp.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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

}
