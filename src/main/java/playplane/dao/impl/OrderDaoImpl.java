package playplane.dao.impl;

import org.springframework.stereotype.Repository;

import playplane.dao.OrderDaoI;
import playplane.entity.Torder;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Torder> implements OrderDaoI {
}

