package com.caleumtatsu2010.techmate_session.cassandra.astra.syncer;

import com.caleumtatsu2010.cassandra.connection.astra.AstraConnector;
import com.caleumtatsu2010.cassandra.dao.account.CASAccountDao;
import com.caleumtatsu2010.cassandra.dao.cart.CASCartDao;
import com.caleumtatsu2010.cassandra.dao.cart.item.CASCartItemDao;
import com.caleumtatsu2010.cassandra.models.account.CASAccount;
import com.caleumtatsu2010.cassandra.models.cart.CASCart;
import com.caleumtatsu2010.cassandra.models.cart.item.CASCartItem;
import com.caleumtatsu2010.techmate_session.cassandra.astra.model.AstraSession;
import com.caleumtatsu2010.techmate_session.cassandra.astra.util.AstraCrudUlti;
import com.caleumtatsu2010.techmate_session.http.sync.HttpSessionSyncronizer;

import java.util.List;

public abstract class AstraHttpSessionSyncronizer implements HttpSessionSyncronizer {
	
	private AstraConnector astraConnector;
	private AstraSession astraSession;
	private String astraKeySpace;
	
	public void HttpSessionAstraSyncronizer(AstraConnector astraConnector, AstraSession casSession, String astraKeyspace) {
		this.astraConnector = astraConnector;
	}
	
	public void syncAstraHttpSession() {
		
		CASCartDao casCartDao = new CASCartDao(this.astraConnector, this.astraKeySpace);
		CASCart casCart = astraSession.getCasCart();
		AstraCrudUlti.syncAstraCrud(casCartDao, casCart, astraSession.getCasCart().getId());
		
		CASAccountDao casAccountDao = new CASAccountDao(this.astraConnector, this.astraKeySpace);
		CASAccount casAccount = astraSession.getCasAccount();
		AstraCrudUlti.syncAstraCrud(casAccountDao, casAccount, astraSession.getCasAccount().getId());
		
		CASCartItemDao casCartItemDao = new CASCartItemDao(this.astraConnector, this.astraKeySpace);
		List<CASCartItem> casCartItemList = astraSession.getCasCartItemList();
		for (int i = 0; i < casCartItemList.size(); i++) {
			AstraCrudUlti.syncAstraCrud(casCartItemDao, casCartItemList.get(i), casCartItemList.get(i).getId());
		}
	}
	
	@Override
	public void syncHttpSession(Object o) {
		this.astraSession = (AstraSession) o;
		syncAstraHttpSession();
	}
	
	
}
