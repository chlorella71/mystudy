package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AssignmentDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Assignment;
//import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

@Component
public class AssignmentDaoImpl implements AssignmentDao {

  private final Log log = LogFactory.getLog(this.getClass());


  SqlSessionFactory sqlSessionFactory;

  public AssignmentDaoImpl(SqlSessionFactory sqlSessionFactory) {
    log.debug("AssingmentDaoImpl() 호출됨");
    this.sqlSessionFactory = sqlSessionFactory;
  }

  //  Connection con;

//  public AssignmentDaoImpl(Connection con) {
//    this.con = con;
//  }

//  @Override
//  public void add(Assignment assignment) {
//    try (PreparedStatement pstmt = con.prepareStatement(
//          "insert into assignments(title,content,deadline) values(?,?,?)")) {
//
//      pstmt.setString(1, assignment.getTitle());
//      pstmt.setString(2, assignment.getContent());
//      pstmt.setDate(3, assignment.getDeadline());
//
//      pstmt.executeUpdate();
//
//    } catch (Exception e) {
//      throw new DaoException("데이터 입력 오류", e);
//    }
//  }

  @Override
  public void add(Assignment assignment) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("AssignmentDao.add", assignment);
    }
  }

  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("AssignmentDao.delete", no);
    }
  }

  @Override
  public List<Assignment> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("AssignmentDao.findAll");
    }
  }

  @Override
  public Assignment findBy(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("AssignmentDao.findBy", no);
    }
  }

  @Override
  public int update(Assignment assignment) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("Assignment.update", assignment);
    }
  }
}
