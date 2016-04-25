package playplane.dao.impl;

import org.springframework.stereotype.Repository;

import playplane.dao.CustomerDaoI;
import playplane.entity.Tcustomer;

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Tcustomer> implements CustomerDaoI {
}

