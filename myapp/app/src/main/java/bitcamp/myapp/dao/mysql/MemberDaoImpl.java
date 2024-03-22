package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
//import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

@Component
public class MemberDaoImpl implements MemberDao {

  private final Log log = LogFactory.getLog(this.getClass());

SqlSessionFactory sqlSessionFactory;

//  Connection con;

//  public MemberDaoImpl(Connection con) {
//    this.con = con;
//  }
  public MemberDaoImpl(SqlSessionFactory sqlSessionFactory) {
    log.debug("MemberDaoImpl() 호출됨");

    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void add(Member member) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("MemberDao.add", member);
    }
  }

  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("MemberDao.delete", no);
    }
  }

  @Override
  public List<Member> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("MemberDao.findAll");
    }
  }

  @Override
  public Member findBy(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("MemberDao.findBy", no);

    }
  }

  @Override
  public int update(Member member) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("MemberDao.update", member);
    }
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      HashMap<String,Object> data = new HashMap<>();
      data.put("email", email);
      data.put("password", password);
      return sqlSession.selectOne("MemberDao.findByEmailAndPassword", data);
    }
  }
}
