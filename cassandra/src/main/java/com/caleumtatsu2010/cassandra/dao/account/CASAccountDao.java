package com.caleumtatsu2010.cassandra.dao.account;

import com.caleumtatsu2010.cassandra.connection.astra.AstraConnector;

import com.caleumtatsu2010.cassandra.models.account.CASAccountQueries;
import com.caleumtatsu2010.cassandra.dao.CASDao;
import com.caleumtatsu2010.cassandra.models.account.CASAccount;
import com.caleumtatsu2010.cassandra.models.database.CASPath;
import com.caleumtatsu2010.cassandra.models.database.astra.AstraDatabases;
import com.caleumtatsu2010.cassandra.models.database.astra.KeySpace;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CASAccountDao implements CASDao<CASAccount> {
	
	private AstraConnector astraConnector = null;
	private CqlSession cqlSession = null;
	private String keyspace = "";
	
	public CASAccountDao(AstraConnector astraConnector, String keyspace) {
		this.astraConnector = astraConnector;
		this.keyspace = keyspace;
	}
	
	@Override
	public List<CASAccount> getAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<CASAccount> list = new ArrayList<>();
			String select = CASAccountQueries.selectAll;
			this.cqlSession = astraConnector.connect(keyspace);
			rs = this.cqlSession.execute(select);
			List<Row> rows = rs.all();
			for (Row row : rows) {
				list.add(new CASAccount(
						row.getUuid("id")
						, row.getString("username")
						, row.getString("password")
						, row.getInt("account_type_id")
						, row.getString("account_status")));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CASAccount get(UUID id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		BoundStatement bound = null;
		try {
			String select = CASAccountQueries.selectById;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(select);
			bound = ps.bind(id);
			rs = this.cqlSession.execute(bound);
			Row row = rs.one();
			return new CASAccount(
					row.getUuid("id")
					, row.getString("username")
					, row.getString("password")
					, row.getInt("account_type_id")
					, row.getString("account_status"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void insert(CASAccount account) {
		PreparedStatement ps = null;
		BoundStatement bound = null;
		try {
			String insertById = CASAccountQueries.insertById;
			this.cqlSession = astraConnector.connect(keyspace);
			ps = this.cqlSession.prepare(insertById);
			bound = ps.bind(account.getId(), account.getUsername(), account.getPassword(), account.getAccountTypeId(), account.getAccountStatus());
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
	public void update(CASAccount casAccount, UUID id) {
		PreparedStatement ps = null;
		BoundStatement bound = null;
		try {
			String cqlCartUpdate = CASAccountQueries.updateById;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(cqlCartUpdate);
			bound = ps.bind(casAccount.getUsername(), casAccount.getPassword(), casAccount.getAccountTypeId(), casAccount.getAccountStatus(), id);
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
			String deleteByID = CASAccountQueries.deleteByID;
			cqlSession = astraConnector.connect(keyspace);
			ps = cqlSession.prepare(deleteByID);
			bound = ps.bind(id);
			cqlSession.execute(bound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AstraConnector astraConnector = new AstraConnector(CASPath.astraToken, AstraDatabases.techmate);
		CASAccountDao CASAccountDao = new CASAccountDao(astraConnector, KeySpace.techmate);
		
//		System.out.println(CASAccountDao.getClass());
//		CASAccount casAccount = new CASAccount(UUID.randomUUID(), null, null, 0, null);
//		ObjectUtilityInvoker.invokeSetMethod(CASAccountDao, "update", casAccount, casAccount.getId());
		
//		CASAccountDao.getAll();
		astraConnector.disconnect();
//		CASAccount CASAccount = new CASAccount(UUID.randomUUID(), "test username 2", "test password 2", 20, "active");
//		CASAccountDao.insert(CASAccount);
		
//		CASAccount CASAccountUpdate = new CASAccount(null, "client",
//				"90779f752c9a610d3121763650a7f9c1d43dccad9faafe3a34d86f28d0bae25b32335b24a7c31a026f2f6061181e16a0ef7ef18d178e31a4c15e0b2a06c58c1b",
//				2, "active");
//		CASAccountDao.update(CASAccountUpdate, UUID.fromString("f07fd891-7e1a-441a-9885-0df9cbbdce22"));
//		CASAccountDao.delete(UUID.fromString("b77b3b66-94de-11ed-a1eb-0242ac120002"));
		
	}
}
