package playplane.dao.impl;

import org.springframework.stereotype.Repository;

import playplane.dao.UserDaoI;
import playplane.entity.Tuser;

@Repository
public class UserDaoImpl extends BaseDaoImpl<Tuser> implements UserDaoI {
}

