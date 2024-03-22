package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
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
public class BoardDaoImpl implements BoardDao {

  private final Log log = LogFactory.getLog(this.getClass());

  SqlSessionFactory sqlSessionFactory;
//  Connection con;

//  public BoardDaoImpl(Connection con, int category) {
//    this.con = con;
//    this.category = category;
//  }

//  public BoardDaoImpl(DBConnectionPool connectionPool, int category) {
//    this.connectionPool = connectionPool;
//    this.category = category;
//  }

  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    log.debug("BoardDaoImpl() 호출됨");

    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void add(Board board) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("BoardDao.add", board);
    }
  }

  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("BoardDao.delete", no);
    }
  }

  @Override
  public List<Board> findAll(int category) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("BoardDao.findAll", category);
    }
//        try (ResultSet rs = pstmt.executeQuery()) {
//
//          ArrayList<Board> list = new ArrayList<>();
//
//          while (rs.next()) {
//            Board board = new Board();
//            board.setNo(rs.getInt("board_no"));
//            board.setTitle(rs.getString("title"));
//            board.setCreatedDate(rs.getDate("created_date"));
//            board.setFileCount(rs.getInt("file_count"));
//
//            Member writer = new Member();
//            writer.setNo(rs.getInt("member_no"));
//            writer.setName(rs.getString("name"));
//
//            board.setWriter(writer);
//
//            list.add(board);
//          }
//          return list;
//        }
  }

  @Override
  public Board findBy(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("BoardDao.findBy", no);
    }

//          if (rs.next()) {
//            Board board = new Board();
//            board.setNo(rs.getInt("board_no"));
//            board.setTitle(rs.getString("title"));
//            board.setContent(rs.getString("content"));
////            board.setWriter(rs.getString("writer"));
//            board.setCreatedDate(rs.getDate("created_date"));
//
//            Member writer = new Member();
//            writer.setNo(rs.getInt("member_no"));
//            writer.setName(rs.getString("name"));
//
//            board.setWriter(writer);
//
//            return board;
//          }
  }

  @Override
  public int update(Board board) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("BoardDao.update", board);
    }
  }
}
