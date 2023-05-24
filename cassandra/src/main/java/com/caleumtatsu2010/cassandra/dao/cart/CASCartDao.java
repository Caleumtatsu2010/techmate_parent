package com.caleumtatsu2010.cassandra.dao.cart;

import com.caleumtatsu2010.cassandra.connection.astra.AstraConnector;
import com.caleumtatsu2010.cassandra.connection.cqlquery.cart.CASCartQueries;
import com.caleumtatsu2010.cassandra.dao.CASDao;
import com.caleumtatsu2010.cassandra.models.cart.CASCart;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CASCartDao implements CASDao<CASCart> {
	
	private AstraConnector astraConnector = null;
	private CqlSession cqlSession = null;
	private String keyspace = "";
	
	public CASCartDao(AstraConnector astraConnector, String keyspace) {
		this.astraConnector = astraConnector;
		this.keyspace = keyspace;
	}
	
	@Override
	public List<CASCart> getAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<CASCart> list = new ArrayList<>();
			String select = CASCartQueries.selectAll;
			cqlSession = astraConnector.connect(keyspace);
			rs = cqlSession.execute(select);
			List<Row> rows = rs.all();
			for (Row row : rows) {
				list.add(new CASCart(row.getUuid("id")
						, row.getInt("quantity")
						, row.getDouble("total_price")
						, row.getBoolean("cart_is_active")
						, row.getUuid("account_id")
						, Timestamp.from(row.getInstant("created_at"))
						, Timestamp.from(row.getInstant("modified_at"))));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CASCart get(UUID id) {
		return null;
	}
	
	@Override
	public void insert(CASCart cart) {
		PreparedStatement ps = null;
		BoundStatement bound = null;
		try {
			String cqlCartInsert = CASCartQueries.insertById;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(cqlCartInsert);
			bound = ps.bind(cart.getId(), cart.isCartIsActive(), cart.getCreatedAt().toInstant(), cart.getModifiedAt().toInstant(), cart.getQuantity(), cart.getTotalPrice(), cart.getAccountId());
			cqlSession.execute(bound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//	    @Override
//    public void insert(Product product) {
//
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            connection = connectionUtil.getConn();
//            ps = connection.prepareStatement(ProductQueries.insertProduct);
//            String[] productAttr = new String[]{"id", "name", "price", "currency", "discountId",
//             "quantity", "temp1", "temp2", "temp3", "temp4",
//              "star", "ratings", "image", "subCategoryId"};
//            StatementUtil.mapParams(ps, product, productAttr);
//            ps.executeUpdate();
//            System.out.println("Data Added Successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ConnectionUtil.closeAll(connection, ps, rs);
//        }
	
	
	@Override
	public void update(CASCart cart, UUID id) {
		PreparedStatement ps = null;
		BoundStatement bound = null;
		try {
			String cqlCartUpdate = CASCartQueries.updateById;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(cqlCartUpdate);
			bound = ps.bind(cart.isCartIsActive(), cart.getCreatedAt().toInstant(), cart.getModifiedAt().toInstant(), cart.getQuantity(), cart.getTotalPrice(), cart.getAccountId(), id);
			cqlSession.execute(bound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(UUID id) {
		PreparedStatement ps = null;
		BoundStatement bound = null;
		try {
			String cqlCartUpdate = CASCartQueries.deleteByID;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(cqlCartUpdate);
			bound = ps.bind(id);
			cqlSession.execute(bound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AstraConnector astraConnector = new AstraConnector();
		CASCartDao casCartDao = new CASCartDao(astraConnector, "cart");
		casCartDao.getAll();
		
//		CASCart casCart = new CASCart(UUID.randomUUID(),false, DateUtility.getCurrentTimeStamp(), DateUtility.getCurrentTimeStamp(), 10, 1.2, UUID.randomUUID());
//		casCartDao.insert(casCart);
		
//		CASCart casCartUpdate = new CASCart(null,true, DateUtility.getCurrentTimeStamp(), DateUtility.getCurrentTimeStamp(), 55, 5.5, UUID.fromString("f000aa01-0451-4000-b000-000000000000"));
//		casCartDao.update(casCartUpdate, UUID.fromString("ff695197-d3e8-4c52-8236-94db485363c8"));
		
//		casCartDao.delete(UUID.fromString("ff695197-d3e8-4c52-8236-94db485363c8"));
		
	}
}
