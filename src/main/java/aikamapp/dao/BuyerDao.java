package aikamapp.dao;

import aikamapp.mapper.BuyerMapper;
import aikamapp.model.Buyer;
import aikamapp.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("buyerDao")
public class BuyerDao  extends JdbcDaoSupport {

    @Autowired
    public BuyerDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Buyer get(Long id){
        String sql = "select * from buyers b\n" +
                "where b.id = ?";

        Object[] params = new Object[] { id };
        BuyerMapper mapper = new BuyerMapper();
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, mapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Buyer> getAll(){
        String sql = "select * from buyers b";

        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Buyer> getByLastname(String lastname){
        String sql =
                "select * from buyers b\n" +
                "where upper(b.lastname) = upper(?)";

        Object[] params = new Object[] { lastname };
        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Buyer> getByGoodAndCountOfPurchases(Good good, int count){
        String sql =
                "select q.id, q.firstname, q.lastname\n" +
                "from (select b.*,\n" +
                "             coalesce(count(p.good_id), 0) number_of_purchases\n" +
                "      from buyers b\n" +
                "      left join purchases p on b.id = p.buyer_id\n" +
                "          and p.good_id = (select g.id from goods g\n" +
                "                           where upper(g.title) = upper(?))\n" +
                "      group by b.id) q\n" +
                "where q.number_of_purchases >= ?";

        Object[] params = new Object[] { good.getTitle(), count };
        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Buyer> getByCostOfPurchases(int minCost, int maxCost){
        String sql =
                "select q.id, q.firstname, q.lastname\n" +
                "from (select b.*,\n" +
                "       coalesce(sum(g.price), 0) cost_of_purchases\n" +
                "       from buyers b\n" +
                "       left join purchases p on b.id = p.buyer_id\n" +
                "       left join goods g on p.good_id = g.id\n" +
                "       group by b.id) q\n" +
                "where q.cost_of_purchases >=?\n" +
                "  and q.cost_of_purchases <= ?";

        Object[] params = new Object[] { minCost, maxCost };
        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<Buyer> getPassiveBuyers(int limit){
        String sql =
                "select b.id, b.firstname, b.lastname,\n" +
                "       coalesce(count(p.good_id), 0) number_of_purchases\n" +
                "from buyers b\n" +
                "    left join purchases p on b.id = p.buyer_id\n" +
                "group by b.id\n" +
                "order by number_of_purchases\n" +
                "limit ?";

        Object[] params = new Object[] { limit };
        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

}
