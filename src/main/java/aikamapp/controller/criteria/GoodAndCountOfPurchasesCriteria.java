package aikamapp.controller.criteria;

import aikamapp.model.Buyer;
import aikamapp.service.BuyerService;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodAndCountOfPurchasesCriteria implements Criteria {
    @SerializedName("productName")
    private final String goodsTitle;
    @SerializedName("minTimes")
    private final int count;

    public GoodAndCountOfPurchasesCriteria(String goodsTitle, int count) {
        super();
        this.goodsTitle = goodsTitle;
        this.count = count;
        if (count < 0){
            throw new RuntimeException("Число покупок товара не может быть отрицательным");
        }
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
