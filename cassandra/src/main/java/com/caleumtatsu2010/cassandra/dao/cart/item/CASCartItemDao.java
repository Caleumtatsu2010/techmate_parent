package com.caleumtatsu2010.cassandra.dao.cart.item;

import com.caleumtatsu2010.cassandra.connection.astra.AstraConnector;
import com.caleumtatsu2010.cassandra.models.cart.item.CASCartItemQueries;
import com.caleumtatsu2010.cassandra.dao.CASDao;
import com.caleumtatsu2010.cassandra.models.cart.item.CASCartItem;
import com.caleumtatsu2010.cassandra.models.database.CASPath;
import com.caleumtatsu2010.cassandra.models.database.astra.AstraDatabases;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CASCartItemDao implements CASDao<CASCartItem> {
	
	private AstraConnector astraConnector = null;
	private CqlSession cqlSession = null;
	private String keyspace = "";
	
	public CASCartItemDao(AstraConnector astraConnector, String keyspace) {
		this.astraConnector = astraConnector;
		this.keyspace = keyspace;
	}
	
	@Override
	public List<CASCartItem> getAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<CASCartItem> list = new ArrayList<>();
			String select = CASCartItemQueries.selectAll;
			cqlSession = astraConnector.connect(keyspace);
			rs = cqlSession.execute(select);
			List<Row> rows = rs.all();
			for (Row row : rows) {
				list.add(new CASCartItem(row.getUuid("id")
						, row.getUuid("cart_id")
						, row.getString("product_id")
						, row.getInt("num")
						));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CASCartItem get(UUID id) {
		return null;
	}
	
	@Override
	public void insert(CASCartItem cartItem) {
		PreparedStatement ps = null;
		BoundStatement bound = null;
		try {
			String cqlCartInsert = CASCartItemQueries.insertById;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(cqlCartInsert);
			bound = ps.bind(cartItem.getId(), cartItem.getCartId(), cartItem.getProductId(), cartItem.getNum());
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
	public void update(CASCartItem cartItem, UUID id) {
		PreparedStatement ps = null;
		BoundStatement bound = null;
		try {
			String cqlCartUpdate = CASCartItemQueries.updateById;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(cqlCartUpdate);
			bound = ps.bind(cartItem.getCartId(), cartItem.getProductId(), id);
			cqlSession.execute(bound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void updateList(List<CASCartItem> newCartItemList, UUID id) {
//		PreparedStatement ps = null;
//		BoundStatement bound = null;
//		List<CASCartItem> oldCartItemList = getAll();
//		try {
//			cqlSession = astraConnector.connect(keyspace);
//			ps = cqlSession.prepare(CASCartItemQueries.updateById);
//			for (int i = 0; i < oldCartItemList.size(); i++) {
//				for (int j = 0; j < newCartItemList.size(); j++) {
//					if(1 == oldCartItemList.get(i).getId().compareTo(newCartItemList.get(j).getId())){
//						
//					}
//					bound = ps.bind(newCartItemList.get(i).getCartId(), newCartItemList.get(i).getCreatedAt().toInstant(), newCartItemList.get(i).getModifiedAt().toInstant(), newCartItemList.get(i).getName(), newCartItemList.get(i).getPrice(), newCartItemList.get(i).getProductId(), id);
//					cqlSession.execute(bound);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void delete(UUID id) {
		PreparedStatement ps = null;
		BoundStatement bound = null;
		try {
			String cqlCartUpdate = CASCartItemQueries.deleteByID;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(cqlCartUpdate);
			bound = ps.bind(id);
			cqlSession.execute(bound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AstraConnector astraConnector = new AstraConnector(CASPath.astraToken, AstraDatabases.techmate);
		CASCartItemDao casCartItemDao = new CASCartItemDao(astraConnector, "cart");
//		casCartItemDao.getAll();
		
//		CASCartItem casCart = new CASCartItem(UUID.randomUUID(),UUID.randomUUID(), DateUtility.getCurrentTimeStamp(), DateUtility.getCurrentTimeStamp(), "demo product name", 22.2, UUID.randomUUID());
//		casCartItemDao.insert(casCart);
		
//		CASCartItem casCartUpdate = new CASCartItem(null,UUID.fromString("f000aa01-0451-4000-b000-000000000000"), UUID.fromString("f000aa01-0451-4000-b000-000000000000"));
//		casCartItemDao.update(casCartUpdate, UUID.fromString("7e69d032-4bd9-4039-aee7-c45800c90271"));
		
//		casCartItemDao.delete(UUID.fromString("ff695197-d3e8-4c52-8236-94db485363c8"));
		
	}
}
