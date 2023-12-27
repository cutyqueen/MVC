package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// ctrl + shift + O   import 정리

public class BoardDAO {

	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 공통) 디비 연결하기(CP)
	private Connection getCon() throws Exception {

		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc");
		con = ds.getConnection();

		System.out.println(" DAO : 디비연결 성공!! ");
		System.out.println(" DAO : " + con);
		return con;
	}

	// 공통) 디비 자원해제
	public void CloseDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 글쓰기 메서드 - insertBoard(BoardDTO)
	public void insertBoard(BoardDTO dto) {
		int bno = 0;

		try {
			// 1.2. 디비연결
			con = getCon();

			// * bno 계산하기 => 1부터 1씩 증가
			// 3. sql구문(select) & pstmt 객체
			sql = "select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
				// bno = rs.getInt("max(bno)")+1;
				bno = rs.getInt(1) + 1;
			}

			System.out.println(" DAO : 글번호 :" + bno);

			// 3. sql 구문(insert) & pstmt 객체
			sql = "insert into itwill_board(bno,name,pass,subject,content,readcount,"
					+ "re_ref,re_lev,re_seq,date,ip,file) values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());

			pstmt.setInt(6, 0); // 조회수 0
			pstmt.setInt(7, bno); // 그룹번호는 글번호와 동일(일반글)
			pstmt.setInt(8, 0); // re_lev 0
			pstmt.setInt(9, 0); // re_seq 0

			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());

			// 4. sql 실행
			pstmt.executeUpdate();

			System.out.println(" DAO :" + bno + "번 글쓰기 완료! ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

	}
	// 글쓰기 메서드 - insertBoard(BoardDTO)

	// 글 개수 계산 메서드 - getBoardCount()
	public int getBoardCount() {
		int result = 0;

		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();

			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from itwill_board";
			pstmt = con.prepareStatement(sql);

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 - 개수를 저장
			if (rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)");
			}
			System.out.println(" DAO : 개수 " + result + "개");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return result;
	}
	// 글 개수 계산 메서드 - getBoardCount()
	
	// 글 개수 계산 메서드 - getBoardCount(search)
	public int getBoardCount(String search) {
		int result = 0;
		
		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from itwill_board "
					+ "where subject like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 - 개수를 저장
			if (rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)");
			}
			System.out.println(" DAO : 개수 " + result + "개");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}
	// 글 개수 계산 메서드 - getBoardCount(search)

	// 글 정보 목록을 가져오는 메서드 - getBoardList(int startRow,int pageSize)
	public ArrayList getBoardList(int startRow, int pageSize) {
		// 글정보를 저장하는 배열
		ArrayList boardList = new ArrayList();
		try {
			// 디비연결정보
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();
			// 3. SQL 작성(select) & pstmt 객체
			// sql = "select * from itwill_board order by bno desc limit ?,?";
			sql = "select * from itwill_board order by re_ref desc,re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			// ????
			pstmt.setInt(1, startRow - 1); // 시작행번호-1
			pstmt.setInt(2, pageSize); // 개수
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// BoardBean 객체 여러개 => ArrayList 저장
			while (rs.next()) {
				// 글 하나의 정보 => BoardBean저장
				BoardDTO bb = new BoardDTO();

				bb.setBno(rs.getInt("bno"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));

				// 글 하나의 정보를 배열의 한칸에 저장
				boardList.add(bb);

			} // while

			System.out.println(" DAO : 게시판 글 목록 조회성공! ");
			System.out.println(" DAO : " + boardList.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return boardList;
	}
	// 글 정보 목록을 가져오는 메서드 -getBoardList(int startRow,int pageSize)
	
	// 글 정보 목록을 가져오는 메서드 - getBoardList(int startRow,int pageSize,String search)
	public ArrayList getBoardList(int startRow, int pageSize,String search) {
		// 글정보를 저장하는 배열
		ArrayList boardList = new ArrayList();
		try {
			// 디비연결정보
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();
			// 3. SQL 작성(select) & pstmt 객체
			// sql = "select * from itwill_board order by bno desc limit ?,?";
			sql = "select * from itwill_board "
					+ "where subject like ? "
					+ "order by re_ref desc,re_seq asc "
					+ "limit ?,?";
			pstmt = con.prepareStatement(sql);
			// ????
			pstmt.setString(1, "%"+search+"%"); // %검색어%
			pstmt.setInt(2, startRow - 1); // 시작행번호-1
			pstmt.setInt(3, pageSize); // 개수
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// BoardBean 객체 여러개 => ArrayList 저장
			while (rs.next()) {
				// 글 하나의 정보 => BoardBean저장
				BoardDTO bb = new BoardDTO();
				
				bb.setBno(rs.getInt("bno"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));
				
				// 글 하나의 정보를 배열의 한칸에 저장
				boardList.add(bb);
				
			} // while
			
			System.out.println(" DAO : 게시판 글 목록 조회성공! ");
			System.out.println(" DAO : " + boardList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return boardList;
	}
	// 글 정보 목록을 가져오는 메서드 -getBoardList(int startRow,int pageSize,String search)

	// 특정 글 조회수 1증가() - updateReadcount(bno);
	public void updateReadcount(int bno) {
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 구문작성(update) & pstmt 객체
			sql = "update itwill_board set readcount = readcount + 1 "
					+ "where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			//4. sql 실행
			pstmt.executeUpdate();
			System.out.println(" DAO : 글 조회수 1증가 완료! ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}		
	}
	// 특정 글 조회수 1증가() - updateReadcount(bno);
	
	// 특정 글의 정보를 가져오기() - getBoard(bno)
	public BoardDTO getBoard(int bno) {
		BoardDTO dto = null;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 구문 작성(select) & pstmt 객체
			sql = "select * from itwill_board "
					+ "where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> dto)
			if(rs.next()) {
				dto = new BoardDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
			}// if
			
			System.out.println(" DAO : 글정보 조회성공!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return dto;
	}
	// 특정 글의 정보를 가져오기() - getBoard(bno)
	
	// 특정 글정보 수정하는 메서드 -updateBoard(bdto)
	public int updateBoard(BoardDTO bdto) {
		int result = -1; // -1(글정보없음,에러)  0(비밀번호 오류)  1(정상처리)
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select pass from itwill_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getBno());
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				if(bdto.getPass().equals(rs.getString("pass"))) {
					// 게시판 정보 수정
					// 3.sql 작성 & pstmt 객체
					sql = "update itwill_board set name=?,subject=?,content=? where bno = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, bdto.getName());
					pstmt.setString(2, bdto.getSubject());
					pstmt.setString(3, bdto.getContent());
					pstmt.setInt(4, bdto.getBno());
					
					// 4. sql 구문 실행
					result = pstmt.executeUpdate();
					// result = 1;
				}else {
					// 게시판 비밀번호 오류
					result = 0;
				}				
			}else {
				// 게시판 글 없음
				result = -1;
			}
			
			System.out.println(" DAO : 게시판 정보를 수정완료! ("+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}
	// 특정 글정보 수정하는 메서드 -updateBoard(bdto)

	// 특정 글 삭제 메서드-deleteBoard(BoardDTO bdto) 
	public int deleteBoard(BoardDTO bdto) {
		int result = -1;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 구문 & pstmt 객체 
			sql = "select pass from itwill_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getBno());
			//4. sql 실행
			rs = pstmt.executeQuery();
			// 5.데이터 처리 
			if(rs.next()) {
				if(bdto.getPass().equals(rs.getString("pass"))) {
					// 삭제
					// 3. sql 작성
					sql = "delete from itwill_board where bno=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, bdto.getBno());
					// 4. sql 실행
					result = pstmt.executeUpdate();
				}else {
					result = 0;
				}
			}else {
				result = -1;
			}
			System.out.println(" DAO : 글 삭제 완료! ("+result+")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}
	// 특정 글 삭제 메서드-deleteBoard(BoardDTO bdto) 
	
	// 답글 쓰기 - reInsertBoard(dto)
	public void reInsertBoard(BoardDTO dto) {
		int bno = 0;
		try {
			// 1. 글번호 계산
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성 & pstmt 객체
			sql = "select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터 처리
			if(rs.next()) {
				//bno = rs.getInt(1) + 1;
				bno = rs.getInt("max(bno)") + 1;
			}
			
			System.out.println(" DAO : 답글번호 : "+bno);
			
			// 2. 답글 순서 재배치
			// 3. sql 작성(update) & pstmt 객체
			sql = "update itwill_board set re_seq = re_seq + 1 "
					+ "where re_ref = ? and re_seq > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getRe_ref());
			pstmt.setInt(2, dto.getRe_seq());
			
			// 4. sql 실행(update)
			int tmp = pstmt.executeUpdate();
			
			if(tmp != 0) {
				System.out.println(" DAO : 답글 순서 재배치 완료! ");
			}
			
			// 3. 답글 작성 (insert)
			// 3.sql 작성 & pstmt 객체
			sql = "insert into itwill_board "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, 0);  //글 작성시 항상 0
			pstmt.setInt(7, dto.getRe_ref());  // 원글ref == 답글ref
			pstmt.setInt(8, dto.getRe_lev()+1);  // 원글lev + 1  
			pstmt.setInt(9, dto.getRe_seq()+1);  // 원글seq + 1
			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());
			
			//4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 답글 작성 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// 답글 쓰기 - reInsertBoard(dto)
	
	
}// DAO

