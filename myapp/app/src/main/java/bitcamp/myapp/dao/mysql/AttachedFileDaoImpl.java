package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.AttachedFileDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AttachedFileDaoImpl implements AttachedFileDao {

  DBConnectionPool connectionPool;

//  Connection con;

//  public BoardDaoImpl(Connection con, int category) {
//    this.con = con;
//    this.category = category;
//  }

  public AttachedFileDaoImpl(DBConnectionPool connectionPool) {
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
  public void add(AttachedFile file) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
          "insert into board_files(file_path,board_no) values(?, ?)")) {

        pstmt.setString(1, file.getFilePath());
        pstmt.setInt(2, file.getBoardNo());

        pstmt.executeUpdate();
//      con.commit();

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
  public int addAll(List<AttachedFile> files) {

//    //필드가 아닌 로컬에서 사용하기 때문에 Buffer가 아닌 Builder사용
//    StringBuilder stringBuilder = new StringBuilder();
//    for (AttachedFile file : files) {
//      stringBuilder.append(String.format("('%s',%d),", file.getFilePath(), file.getBoardNo()));
//    } // sql 공격을 당할 수 있기 때문에 다르게 작동하도록 하겠음.
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into board_files(file_path,board_no) values(?, ?)")) {

      for (AttachedFile file : files) {
        pstmt.setString(1, file.getFilePath());
        pstmt.setInt(2, file.getBoardNo());

        pstmt.executeUpdate();
//      con.commit();
      }

      return files.size();

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
          "delete from board_files where file_no=?")) {
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
  public int deleteAll(int boardNo) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from board_files where board_no=?")) {
      pstmt.setInt(1, boardNo);

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
  public List<AttachedFile> findAllByBoardNo(int boardNo) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
          "select file_no, file_path, board_no"
              + " from board_files where board_no=? order by file_no asc")) {
// sql문을 +로 나눌때 사이에 공백을 신경써야 함!

        pstmt.setInt(1, boardNo);

        try (ResultSet rs = pstmt.executeQuery()) {

          ArrayList<AttachedFile> list = new ArrayList<>();

          while (rs.next()) {
            AttachedFile file = new AttachedFile();
            file.setNo(rs.getInt("file_no"));
            file.setFilePath(rs.getString("file_path"));
            file.setBoardNo(rs.getInt("board_no"));

            list.add(file);
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
  public AttachedFile findByNo(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
          "select file_no, file_path, board_no"
              + " from board_files where file_no=?")) {

      pstmt.setInt(1, no);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          AttachedFile file = new AttachedFile();
          file.setNo(rs.getInt("file_no"));
          file.setFilePath(rs.getString("file_path"));
          file.setBoardNo(rs.getInt("board_no"));
          return file;
        }
        return null;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류!", e);
    }
  }
}
