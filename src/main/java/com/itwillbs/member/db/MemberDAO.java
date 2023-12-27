package com.itwillbs.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * MemberDAO : DB관련 처리를 모두 수행하는 객체 (Data Access Object)
 *
 */
public class MemberDAO {

	// 공통변수
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 공통메서드(기능)

	/////////////////////////// 디비 연결 메서드////////////////////////////////
	private Connection getCon() throws Exception {
		/*
		 * // 디비연결정보 final String DRIVER = "com.mysql.cj.jdbc.Driver"; final String
		 * DBURL = "jdbc:mysql://localhost:3306/jspdb"; final String DBID = "root";
		 * final String DBPW = "1234";
		 * 
		 * // 1. 드라이버 로드 Class.forName(DRIVER);
		 * System.out.println(" DAO : 드라이버 로드 성공!"); // 2. 디비 연결 con =
		 * DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" DAO : 디비 연결 성공! "); System.out.println(" DAO : " + con);
		 */
		
		// Connection Pool을 사용한 디비 연결
		
		// 프로젝트의 정보를 확인(JNDI)
		Context initCTX = new InitialContext();
		// 프로젝트안에 작성된 디비 연결정보(context.xml)를 불러오기
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/mvc");
		// 디비 연결 수행
		con = ds.getConnection();
		System.out.println(" DAO : 디비연결 성공! (커넥션풀) ");
		System.out.println(" DAO : "+con);		

		return con;
	}
	/////////////////////////// 디비 연결 메서드////////////////////////////////

	/////////////////////////// 디비 연결(자원) 해제 메서드////////////////////////
	public void CloseDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
			System.out.println(" DAO : 디비 자원해제 완료! ");			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/////////////////////////// 디비 연결(자원) 해제 메서드/////////////////////////
	
	
	// 회원가입 메서드 - insertMember(dto)
	public void insertMember(MemberDTO dto) {
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성 & pstmt 객체
			sql = "insert into itwill_member (id,pw,name,gender,age,email,regdate) "
					+ "values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setInt(5, dto.getAge());
			pstmt.setString(6, dto.getEmail());
			pstmt.setTimestamp(7, dto.getRegdate());
			//4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 회원가입 성공! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// 회원가입 메서드 - insertMember(dto)
	
	
	// 로그인 체크 메서드 - loginMember(dto)
	public int loginMember(MemberDTO dto) {
		int result = -1; // -1 0 1
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select pw from itwill_member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) { // 회원정보가 있음
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 비밀번호 동일
					result = 1;
				}else {
					// 비밀번호 다름
					result = 0;
				}
			}else {// 회원정보 없음
				result = -1;
			}
			
			System.out.println(" DAO : 로그인 처리 완료 ("+result+")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return result;
	}
	// 로그인 체크 메서드 - loginMember(dto)
	
	// 회원정보 조회 메서드 - getMember(id)
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성(select) & pstmt 객체
			sql = "select * from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, id);
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터 처리 (DB에 저장된 정보(rs)를 DTO로 저장)
			if(rs.next()) {
				dto = new MemberDTO();
				// rs => dto 저장
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getTimestamp("regdate"));
			}
			
			System.out.println(" DAO : 회원정보 조회 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return dto;
	}
	// 회원정보 조회 메서드 - getMember(id)
	
	
	// 회원정보 수정 메서드 - updateMember(dto)
	public int updateMember(MemberDTO dto) {
		int result = -1;  // -1  0  1
		
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성(select) & pstmt객체
			sql = "select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("pw"))) {
					//3. sql 작성(update) & pstmt객체
					sql = "update itwill_member set name=?,gender=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getGender());
					pstmt.setString(3, dto.getId());
					//4. sql 실행
					result = pstmt.executeUpdate();
					// result = 1;
				}else {
					result = 0; // 사용자의 비밀번호 오류
				}
			}else {
				result = -1; // 회원정보X,에러발생
			}
			
			System.out.println(" DAO : 회원정보 수정완료! ("+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}
	// 회원정보 수정 메서드 - updateMember(dto)
	
	// 회원정보 삭제 메서드 - deleteMember(dto)
	public int deleteMember(MemberDTO dto) {
		int result = -1; // -1  0  1
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			// 4. sql 실행			
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 3. sql 작성(delete) & pstmt 객체
					sql = "delete from itwill_member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getId());
					// 4. sql 실행
					result = pstmt.executeUpdate(); // 삭제완료
				}else {
					result = 0; // 비밀번호 오류
				}
			}else {
			    result = -1; // 회원정보 없음	
			}
			System.out.println(" DAO : 회원정보 삭제 ("+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}	
	// 회원정보 삭제 메서드 - deleteMember(dto)
	
	// 회원정보 목록조회 메서드 - getMemberList()
	public ArrayList getMemberList() {
		ArrayList memberList = new ArrayList();
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 (select) & pstmt객체
			sql = "select * from itwill_member";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			while(rs.next()) {
				// rs -> dto -> list
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				// rs -> dto 저장완료
				memberList.add(dto);
				// dto -> list 저장완료
			} //while
			
			System.out.println(" DAO : 회원목록 조회 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return memberList;
	}
	// 회원정보 목록조회 메서드 - getMemberList()
	
	

}// DAO
