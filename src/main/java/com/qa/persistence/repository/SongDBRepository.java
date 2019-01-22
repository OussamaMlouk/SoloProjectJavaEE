package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional(SUPPORTS)
@Default
public class SongDBRepository implements SongRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
}
