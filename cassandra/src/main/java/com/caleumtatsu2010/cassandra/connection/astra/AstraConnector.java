package com.caleumtatsu2010.cassandra.connection.astra;

import com.caleumtatsu2010.cassandra.models.CASConnectInfo;
import com.caleumtatsu2010.cassandra.path.CASPath;
import com.caleumtatsu2010.utility.file.properties.Utils;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.nio.file.Paths;
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
	
	public CqlSession connect(String keyspace) {
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
			Row row = rs.one();
			//Print the results of the CQL query to the console:
			if (row != null) {
				System.out.println(row.getUuid("id"));
				System.out.println(row.getInstant("created_at"));
			} else {
				System.out.println("An error occurred.");
			}
		}
	}
}
