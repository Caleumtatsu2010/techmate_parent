package com.caleumtatsu2010.cassandra.connection.astra;

import com.caleumtatsu2010.cassandra.models.database.astra.CASToken;
import com.caleumtatsu2010.cassandra.models.database.CASPath;
import com.caleumtatsu2010.cassandra.models.database.astra.CASTokenName;
import com.caleumtatsu2010.cassandra.models.database.astra.AstraDatabases;
import com.caleumtatsu2010.cassandra.models.database.astra.KeySpace;
import com.caleumtatsu2010.utility.file.properties.PropsUtil;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


public class AstraConnector {
	
	private CASToken casToken = null;
	
	public AstraConnector(String astraTokenPath, String databaseName) {
		this.casToken = readCASToken(astraTokenPath,databaseName);
	}
	
	public static CASToken readCASToken(String astraTokenPath,String databaseName) {
		Properties prop = PropsUtil.loadProp(astraTokenPath);
		String clientId = prop.getProperty(databaseName + "." + CASTokenName.clientId);
		String clientSecret = prop.getProperty(databaseName + "." + CASTokenName.clientSecret);
		String role = prop.getProperty(databaseName + "." + CASTokenName.role);
		String token = prop.getProperty(databaseName + "." + CASTokenName.token);
		return new CASToken(clientId, clientSecret, role, token);
	}
	
	public CqlSession connect(String keyspace)  {
		// Create the CqlSession object:
		CqlSession session = CqlSession.builder()
				.withCloudSecureConnectBundle(Paths.get(CASPath.secureConnectBundle))
				.withAuthCredentials(casToken.getClientId(), casToken.getClientSecret())
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
//		AstraConnector astraConnector = new AstraConnector("techmate");
		CASToken casConnectInfo = readCASToken(CASPath.astraToken, AstraDatabases.techmate);
		// Create the CqlSession object:
		try (CqlSession session = CqlSession.builder()
				.withCloudSecureConnectBundle(Paths.get(CASPath.secureConnectBundle))
				.withAuthCredentials(casConnectInfo.getClientId(), casConnectInfo.getClientSecret())
				.withKeyspace(KeySpace.techmate)
				.build()) {
			// Select the release_version from the system.local table:
			ResultSet rs = session.execute("select * from techmate.cart");
			List<Row> rowList = rs.all();
			//Print the results of the CQL query to the console:
			for (int i=0;i<rowList.size();i++) {
				System.out.println(rowList.get(i).getUuid("id"));
				System.out.println(rowList.get(i).getBoolean("cart_is_active"));
				System.out.println(rowList.get(i).getInt("quantity"));
				System.out.println(rowList.get(i).getInstant("created_at"));
				System.out.println(rowList.get(i).getDouble("total_price"));
				System.out.println(rowList.get(i).getUuid("account_id"));
			}
		}
	}
}
