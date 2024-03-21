package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class BoardDaoImpl implements BoardDao {

  private final Log log = LogFactory.getLog(this.getClass());

  DBConnectionPool connectionPool;
//  Connection con;

//  public BoardDaoImpl(Connection con, int category) {
//    this.con = con;
//    this.category = category;
//  }

//  public BoardDaoImpl(DBConnectionPool connectionPool, int category) {
//    this.connectionPool = connectionPool;
//    this.category = category;
//  }

  public BoardDaoImpl(DBConnectionPool connectionPool) {
    log.debug("BoardDaoImpl() 호출됨");

    this.connectionPool = connectionPool;
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
  public void add(Board board) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
          "insert into boards(title,content,writer,category) values(?, ?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {

        pstmt.setString(1, board.getTitle());
        pstmt.setString(2, board.getContent());
        pstmt.setInt(3, board.getWriter().getNo());
        pstmt.setInt(4, board.getCategory());

        pstmt.executeUpdate();
//      con.commit();

      // 자동 생성된 PK 값을 가져와서 Board 객체에 저장한다.
      try (ResultSet keyRs = pstmt.getGeneratedKeys()) {
        keyRs.next();
        board.setNo(keyRs.getInt(1));
      }

    } catch (Exception e) {
//      try {
////        con.rollback();
//      } catch (Exception e2) {
//      }
      throw new DaoException("데이터 입력 오류!", e);
//    } finally {
//      try {
//        con.setAutoCommit(true);
//      } catch (Exception e) {
//      }
//      try {
//        con.close();
//      } catch (Exception e) {
//      }
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
          "delete from boards where board_no=?")) {
        pstmt.setInt(1, no);

        return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류!", e);
//    } finally {
//      try {
//        con.close();
//      } catch (Exception e) {
//      }
    }
  }

  @Override
  public List<Board> findAll(int category) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
//          "select board_no, title, writer, created_date"
//              + " from boards where category=? order by board_no desc")) {
            "select\n"
                + " b.board_no,\n"
                + " b.title,\n"
//                + " b.writer,\n"
                + " b.created_date,\n"
                + " count(file_no) file_count,\n"
                + " m.member_no\n,"
                + " m.name\n"
                + " from\n"
                + " boards b left outer join board_files bf on b.board_no=bf.board_no\n"
                + " inner join members m on b.writer=m.member_no\n"
                + " where\n"
                + " b.category=?\n"
                + " group by\n"
                + " b.board_no\n"
                + " order by\n"
                + " board_no desc")) {
/*
    select
      b.board_no,
      b.title,
      b.writer,
      b.created_date,
      count(file_no) file_count
--      bf.file_no,
--      bf.file_path
    from
      boards b left outer join board_files bf on b.board_no=bf.board_no
    where
      b.category=1
    group by
      b.board_no
    order by
      board_no desc
    ;
  */
        pstmt.setInt(1, category);

        try (ResultSet rs = pstmt.executeQuery()) {

          ArrayList<Board> list = new ArrayList<>();

          while (rs.next()) {
            Board board = new Board();
            board.setNo(rs.getInt("board_no"));
            board.setTitle(rs.getString("title"));
            board.setCreatedDate(rs.getDate("created_date"));
            board.setFileCount(rs.getInt("file_count"));

            Member writer = new Member();
            writer.setNo(rs.getInt("member_no"));
            writer.setName(rs.getString("name"));

            board.setWriter(writer);

            list.add(board);
          }
          return list;
        }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류!", e);
//    } finally {
//      try {
//        con.close();
//      } catch (Exception e) {
//      }
    }
  }

  @Override
  public Board findBy(int no) {
    try (Connection con = connectionPool.getConnection();PreparedStatement pstmt = con.prepareStatement(
          "select\n"
              + " b.board_no,\n"
              + " b.title,\n"
              + " b.content,\n"
              + " b.created_date,\n"
              + " m.member_no\n,"
              + " m.name\n"
              + " from\n"
              + " boards b inner join members m on b.writer=m.member_no\n"
        + " where board_no =?")) {

        pstmt.setInt(1, no);

        try (ResultSet rs = pstmt.executeQuery()) {

          if (rs.next()) {
            Board board = new Board();
            board.setNo(rs.getInt("board_no"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));
//            board.setWriter(rs.getString("writer"));
            board.setCreatedDate(rs.getDate("created_date"));

            Member writer = new Member();
            writer.setNo(rs.getInt("member_no"));
            writer.setName(rs.getString("name"));

            board.setWriter(writer);

            return board;
          }
          return null;
        }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류!", e);
//    } finally {
//      try {
//        con.close();
//      } catch (Exception e) {
//      }
    }
  }

  @Override
  public int update(Board board) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
//          "update boards set title=?, content=?, writer=? where board_no=?")
            "update boards set title=?, content=? where board_no=?")
      ) {
        pstmt.setString(1, board.getTitle());
        pstmt.setString(2, board.getContent());
//        pstmt.setString(3, board.getWriter());
        pstmt.setInt(3, board.getNo());

        return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류!", e);
//    } finally {
//      try {
//        con.close();
//      } catch (Exception e) {
//      }
    }
  }
}
