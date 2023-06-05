package com.caleumtatsu2010.techmate_session.cassandra.astra.model;

import com.caleumtatsu2010.cassandra.models.account.CASAccount;
import com.caleumtatsu2010.cassandra.models.cart.CASCart;
import com.caleumtatsu2010.cassandra.models.cart.item.CASCartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AstraSession {
	private CASAccount casAccount;
	private CASCart casCart;
	private List<CASCartItem> casCartItemList;
}
