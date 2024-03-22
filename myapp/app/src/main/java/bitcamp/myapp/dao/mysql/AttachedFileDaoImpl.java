package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.AttachedFile;
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
public class AttachedFileDaoImpl implements AttachedFileDao {

  private final Log log = LogFactory.getLog(this.getClass());

  SqlSessionFactory sqlSessionFactory;

//  Connection con;

//  public BoardDaoImpl(Connection con, int category) {
//    this.con = con;
//    this.category = category;
//  }

  public AttachedFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
    log.debug("AttachedFileDaoImpl() 호출됨");
    this.sqlSessionFactory = sqlSessionFactory;

  }

//  @Override
//  public void add(Board board) {
//    try (PreparedStatement pstmt = con.prepareStatement(
//        "insert into boards(title,content,writer,category) values(?, ?, ?, ?)")) {
//
//      pstmt.setString(1, board.getTitle());
//      pstmt.setString(2, board.getContent());
//      pstmt.setString(3, board.getWriter());
//      pstmt.setInt(4, category);
//
//      pstmt.executeUpdate();
//
//    } catch (Exception e) {
//      throw new DaoException("데이터 입력 오류!", e);
//    }
//  }

  @Override
  public void add(AttachedFile file) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("AttachedFileDao.add", file);
    }
  }

  @Override
  public int addAll(List<AttachedFile> files) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("AttachedFileDao.addAll", files);
    }
//      for (AttachedFile file : files) {
//        pstmt.setString(1, file.getFilePath());
//        pstmt.setInt(2, file.getBoardNo());
//
//        pstmt.executeUpdate();
////      con.commit();
//      }
  }

  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("AttachedFileDao.delete", no);
    }
  }

  @Override
  public int deleteAll(int boardNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("AttachedFileDao.deleteAll", boardNo);
    }
  }

  @Override
  public List<AttachedFile> findAllByBoardNo(int boardNo) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("AttachedFileDao.findAllByBoardNo");
    }
  }

  @Override
  public AttachedFile findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("AttachedFileDao.findByNo", no);
    }
  }
}
