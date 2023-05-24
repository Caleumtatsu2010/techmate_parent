package com.caleumtatsu2010.cassandra.connection.astra;

import com.caleumtatsu2010.cassandra.models.database.CASConnectInfo;
import com.caleumtatsu2010.cassandra.models.database.CASPath;
import com.caleumtatsu2010.utility.file.properties.Utils;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


public class AstraConnector {
	
	private CASConnectInfo casConnectInfo = null;
	
	public AstraConnector() {
		this.casConnectInfo = readCASConnectInfo();
	}
	
	public static CASConnectInfo readCASConnectInfo() {
		Properties prop = Utils.loadProp(CASPath.astraToken);
		String clientId = prop.getProperty(CASPath.clientId);
		String clientSecret = prop.getProperty(CASPath.clientSecret);
		String role = prop.getProperty(CASPath.role);
		String token = prop.getProperty(CASPath.token);
		return new CASConnectInfo(clientId, clientSecret, role, token);
	}
	
	public CqlSession connect(String keyspace)  {
		// Create the CqlSession object:
		CqlSession session = CqlSession.builder()
				.withCloudSecureConnectBundle(Paths.get(CASPath.secureConnectBundle))
				.withAuthCredentials(casConnectInfo.getClientId(), casConnectInfo.getClientSecret())
				.withKeyspace(keyspace)
				.build();
		System.out.println("New session is oppened! ");
		return session;
	}
	
	public boolean disconnect(CqlSession session) {
		if (session != null) {
			session.close();
			System.out.println("Session closed!. All operation exsiting! ");
			System.exit(0);
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
//		CqlSession session = AstraConnector.connect("cart");
//		System.out.println("Connect successful: " + session.toString());
		CASConnectInfo casConnectInfo = readCASConnectInfo();
		// Create the CqlSession object:
		try (CqlSession session = CqlSession.builder()
				.withCloudSecureConnectBundle(Paths.get(CASPath.secureConnectBundle))
				.withAuthCredentials(casConnectInfo.getClientId(), casConnectInfo.getClientSecret())
				.withKeyspace("cart")
				.build()) {
			// Select the release_version from the system.local table:
			ResultSet rs = session.execute("select * from cart.cart");
			List<Row> rowList = rs.all();
			//Print the results of the CQL query to the console:
			for (int i=0;i<rowList.size();i++) {
				System.out.println(rowList.get(i).getUuid("id"));
				System.out.println(rowList.get(i).getBoolean("cart_is_active"));
				System.out.println(rowList.get(i).getInt("quantity"));
				System.out.println(rowList.get(i).getInstant("created_at"));
				System.out.println(rowList.get(i).getDouble("total_price"));
				System.out.println(rowList.get(i).getUuid("user_id"));
			}
		}
	}
}
