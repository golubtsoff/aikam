package aikamapp.controller.criteria;

import aikamapp.model.Buyer;
import aikamapp.service.BuyerService;

import java.util.List;

public class GoodAndCountOfPurchasesCriteria implements Criteria {
    private final String goodsTitle;
    private final int count;

    public GoodAndCountOfPurchasesCriteria(String goodsTitle, int count) {
        super();
        this.goodsTitle = goodsTitle;
        this.count = count;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public int getCount() {
        return count;
    }

    @Override
    public List<Buyer> get(BuyerService buyerService) {
        return buyerService.getByGoodAndCountOfPurchases(goodsTitle, count);
    }
}
